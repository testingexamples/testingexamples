package demo;

import java.net.URI;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * Demo of Selenium WebDriver browser automation with Java, teaching the
 * locator strategies and interaction patterns for a Google Search results page.
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
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications"); // Disable notifications such as popups.
        options.setExperimentalOption("prefs",
            Map.of("profile.default_content_setting_values.cookies", 2)); // Reject cookies.

        WebDriver driver = new ChromeDriver(options);

        try {
            ///
            // Step 1: Load the Google Search home page, then verify the page
            // title is what we expect.
            ///

            driver.get("https://www.google.com");

            String title = driver.getTitle();
            System.out.println("Page title: \"" + title + "\"");
            check(title.equals("Google"), "Expected page title to be \"Google\", got \"" + title + "\"");
            System.out.println("✅ Page title is correct.");

            ///
            // Step 2: Use the search box, then verify the results page title
            // contains our query.
            //
            // Note on markup drift: Google's exact markup for the search box
            // has drifted over time, and will likely keep drifting. It has
            // historically been an <input> and is currently often a
            // <textarea> — but both have commonly carried name="q", so we
            // locate it by name rather than by tag or type.
            ///

            String query = "testing examples";
            WebElement searchBox = driver.findElement(By.name("q"));
            searchBox.sendKeys(query + Keys.RETURN);

            String resultTitle = driver.getTitle();
            System.out.println("Result page title: \"" + resultTitle + "\"");
            check(resultTitle.contains(query),
                "Expected result page title to contain \"" + query + "\", got \"" + resultTitle + "\"");
            System.out.println("✅ Result page title contains the search query.");

            ///
            // Step 3: Click the first result, then verify we navigated away
            // from www.google.com to some other hostname.
            ///

            driver.findElement(By.cssSelector("#search a")).click();

            String hostname = URI.create(driver.getCurrentUrl()).getHost();
            System.out.println("Result hostname: \"" + hostname + "\"");
            check(!"www.google.com".equals(hostname),
                "Expected hostname to change away from www.google.com, got \"" + hostname + "\"");
            System.out.println("✅ Navigated away from www.google.com.");

            System.out.println("\nAll checks passed. 🎉");
        } catch (Throwable err) {
            System.err.println("Fatal error: " + err.getMessage());
            System.exit(1);
        } finally {
            driver.quit();
        }
    }
}
