package demo;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * Demo of Selenium WebDriver browser automation with Java, using the
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
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications"); // Disable notifications such as popups.
        options.setExperimentalOption("prefs",
            Map.of("profile.default_content_setting_values.cookies", 2)); // Reject cookies.

        // Selenium Manager finds or downloads a matching chromedriver.
        WebDriver driver = new ChromeDriver(options);

        try {
            ///
            // Step 1: Connect to the NHS Wales home page, then verify the
            // page title is what we expect.
            ///

            driver.get("https://www.nhs.wales/");

            String homeTitle = driver.getTitle();
            System.out.println("Home page title: \"" + homeTitle + "\"");
            check(homeTitle.equals("Home - NHS Wales"),
                "Expected home page title to be \"Home - NHS Wales\", got \"" + homeTitle + "\"");
            System.out.println("✅ Home page title is correct.");

            ///
            // Step 2: Click the "About Us" link, then verify the response
            // page has the title and headline we expect.
            ///

            driver.findElement(By.linkText("About Us")).click();

            String aboutTitle = driver.getTitle();
            System.out.println("About Us page title: \"" + aboutTitle + "\"");
            check(aboutTitle.equals("About Us - NHS Wales"),
                "Expected About Us page title to be \"About Us - NHS Wales\", got \"" + aboutTitle + "\"");
            System.out.println("✅ About Us page title is correct.");

            String headline = driver.findElement(By.tagName("h1")).getText().trim();
            System.out.println("About Us headline: \"" + headline + "\"");
            check(headline.equals("About Us"),
                "Expected About Us headline to be \"About Us\", got \"" + headline + "\"");
            System.out.println("✅ About Us headline is correct.");

            ///
            // Step 3: Use the page search box: type "help", click the search
            // button, then verify the response page contains the phrases
            // we expect.
            ///

            driver.get("https://www.nhs.wales/");
            driver.findElement(By.id("navKeywords")).sendKeys("help");
            driver.findElement(By.id("button-addon")).click();

            String bodyText = driver.findElement(By.tagName("body")).getText();
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
            driver.quit();
        }
    }
}
