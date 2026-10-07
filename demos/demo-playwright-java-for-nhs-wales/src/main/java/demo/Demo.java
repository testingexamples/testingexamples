package demo;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.AriaRole;

/**
 * Demo of Playwright browser automation with Java, using the
 * NHS Wales website <https://www.nhs.wales/> as a friendly real-world example.
 *
 * This is a beginner-friendly walkthrough: each step is commented so you can
 * follow along and adapt the pattern to your own site.
 *
 * Please see the file README.md for more information.
 */
public class Demo {

    private static void check(boolean ok, String message) {
        if (!ok) throw new AssertionError(message);
    }

    public static void main(String[] args) {
        // Playwright is AutoCloseable: try-with-resources shuts down the driver.
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                .setHeadless(false)); // Set to true for headless mode.
            BrowserContext context = browser.newContext();
            Page page = context.newPage();

            try {
                ///
                // Step 1: Connect to the NHS Wales home page, then verify the
                // page title is what we expect.
                ///

                page.navigate("https://www.nhs.wales/");

                String homeTitle = page.title();
                System.out.println("Home page title: \"" + homeTitle + "\"");
                check(homeTitle.equals("Home - NHS Wales"),
                    "Expected home page title to be \"Home - NHS Wales\", got \"" + homeTitle + "\"");
                System.out.println("✅ Home page title is correct.");

                ///
                // Step 2: Click the "About Us" link, then verify the response
                // page has the title and headline we expect.
                ///

                page.getByRole(AriaRole.MENUITEM, new Page.GetByRoleOptions().setName("About Us").setExact(true))
                    .first().click();
                page.waitForLoadState();

                String aboutTitle = page.title();
                System.out.println("About Us page title: \"" + aboutTitle + "\"");
                check(aboutTitle.equals("About Us - NHS Wales"),
                    "Expected About Us page title to be \"About Us - NHS Wales\", got \"" + aboutTitle + "\"");
                System.out.println("✅ About Us page title is correct.");

                String headline = page.locator("h1").first().innerText().trim();
                System.out.println("About Us headline: \"" + headline + "\"");
                check(headline.equals("About Us"),
                    "Expected About Us headline to be \"About Us\", got \"" + headline + "\"");
                System.out.println("✅ About Us headline is correct.");

                ///
                // Step 3: Use the page search box: type "help", click the search
                // button, then verify the response page contains the phrases
                // we expect.
                ///

                page.navigate("https://www.nhs.wales/");
                page.locator("#navKeywords").fill("help");
                page.locator("#button-addon").click();
                page.waitForLoadState();

                String bodyText = page.locator("body").innerText();
                check(bodyText.contains("Search Results"), "Expected page to contain \"Search Results\"");
                System.out.println("✅ Search results page contains \"Search Results\".");
                check(bodyText.contains("Your search for \"help\""),
                    "Expected page to contain 'Your search for \"help\"'");
                System.out.println("✅ Search results page contains 'Your search for \"help\"'.");

                System.out.println("\nAll checks passed. 🎉");
            } catch (Throwable err) {
                System.err.println("Fatal error: " + err.getMessage());
                System.exit(1);
            } finally {
                browser.close();
            }
        }
    }
}
