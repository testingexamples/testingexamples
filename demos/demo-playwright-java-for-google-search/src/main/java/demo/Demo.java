package demo;

import java.net.URI;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

/**
 * Demo of Playwright browser automation with Java, teaching the locator
 * strategies and interaction patterns for Google Search.
 *
 * IMPORTANT — DO NOT RUN THIS AGAINST LIVE google.com:
 * Google's Terms of Service restrict automated querying of its services.
 * This code exists to teach syntax and interaction *patterns* — it is not
 * meant to be executed against the live Google Search. Do not run this program,
 * do not build it in order to run it, and do not point it at a real Google
 * page. See README.md and AGENTS.md for the full caution.
 *
 * Please see the file README.md for more information.
 */
public class Demo {

    private static void check(boolean ok, String message) {
        if (!ok) throw new AssertionError(message);
    }

    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                .setHeadless(false)); // Set to true for headless mode.
            BrowserContext context = browser.newContext();
            Page page = context.newPage();

            try {
                ///
                // Step 1: Connect to the Google home page, then verify the
                // page title is what we expect.
                ///

                page.navigate("https://www.google.com");

                String homeTitle = page.title();
                System.out.println("Home page title: \"" + homeTitle + "\"");
                check(homeTitle.equals("Google"),
                    "Expected home page title to be \"Google\", got \"" + homeTitle + "\"");
                System.out.println("✅ Home page title is correct.");

                ///
                // Step 2: Use the search box: type "testing examples", press
                // Enter, then verify the resulting page title contains our
                // search term.
                //
                // Google's search input is reachable via `textarea[name="q"]`
                // as of this writing. Historically it was a plain
                // `<input name="q">`; Google has since swapped it for an
                // auto-growing `<textarea>` with the same name attribute. Noting
                // that drift here honestly, since it is exactly the kind of
                // third-party markup change this demo warns you about.
                ///

                Locator searchBox = page.locator("textarea[name=\"q\"]");
                searchBox.fill("testing examples");
                searchBox.press("Enter");
                page.waitForLoadState();

                String resultsTitle = page.title();
                System.out.println("Search results page title: \"" + resultsTitle + "\"");
                check(resultsTitle.contains("testing examples"),
                    "Expected search results page title to contain \"testing examples\", got \"" + resultsTitle + "\"");
                System.out.println("✅ Search results page title contains \"testing examples\".");

                ///
                // Step 3: Click the first organic result link, then verify the
                // browser navigated away from google.com.
                //
                // `#search a` targets the first link inside Google's results
                // container. The exact structure of a Google results page shifts
                // over time (Google regularly changes wrapper divs, classes, and
                // ids), so treat this selector as illustrative rather than
                // guaranteed to remain accurate.
                ///

                page.locator("#search a").first().click();
                page.waitForLoadState();

                String landedUrl = page.url();
                String landedHost = URI.create(landedUrl).getHost();
                System.out.println("Landed on URL: \"" + landedUrl + "\" (host: \"" + landedHost + "\")");
                check(!"www.google.com".equals(landedHost),
                    "Expected to navigate away from www.google.com, but landed host was \"" + landedHost + "\"");
                System.out.println("✅ Browser navigated away from google.com.");

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
