package demo;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * Demo of Selenium WebDriver browser automation with Java, teaching the
 * locator strategies and interaction patterns for Google Maps.
 *
 * IMPORTANT — DO NOT RUN THIS AGAINST LIVE google.com/maps:
 * Google's Terms of Service restrict automated querying of its services.
 * This code exists to teach syntax and interaction *patterns* — it is not
 * meant to be executed against the live Google Maps. Do not run this program,
 * do not build it in order to run it, and do not point it at a real Google
 * Maps page. See README.md and AGENTS.md for the full caution.
 *
 * Please see the file README.md for more information.
 */
public class Demo {

    // Google Maps embeds the current view as "@lat,lng,zoomz" in the URL, e.g.
    // https://www.google.com/maps/place/Cardiff/@51.4816546,-3.1791934,12z/...
    // We use this to read back the zoom level, because the map itself renders
    // mostly to a <canvas> (or WebGL) element that ordinary element locators
    // cannot inspect.
    private static final Pattern ZOOM_PATTERN =
        Pattern.compile("@(-?[\\d.]+),(-?[\\d.]+),(\\d+(?:\\.\\d+)?)z");

    private static double getZoom(String url) {
        Matcher match = ZOOM_PATTERN.matcher(url);
        if (!match.find()) {
            throw new IllegalStateException("Could not find \"@lat,lng,zoomz\" in URL: " + url);
        }
        return Double.parseDouble(match.group(3));
    }

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
            // Step 1: Load Google Maps, then verify the page title mentions
            // Google Maps.
            ///

            driver.get("https://www.google.com/maps");

            String title = driver.getTitle();
            System.out.println("Page title: \"" + title + "\"");
            check(title.contains("Google Maps"),
                "Expected page title to contain \"Google Maps\", got \"" + title + "\"");
            System.out.println("✅ Page title contains \"Google Maps\".");

            ///
            // Step 2: Search for a place, then verify the URL contains it.
            //
            // Most of the map itself renders to a <canvas> element (or WebGL),
            // so you generally cannot "find" a street or a pin the way you find
            // a paragraph of text. But the UI chrome around the canvas —
            // search box, zoom buttons, layers menu — uses stable accessible
            // aria-label attributes, which change far less often than
            // generated or hashed CSS class names do. So we locate the search
            // box by its accessible name.
            ///

            String query = "Cardiff";
            WebElement searchBox = driver.findElement(By.cssSelector("[aria-label=\"Search Google Maps\"]"));
            searchBox.sendKeys(query + Keys.RETURN);

            String urlAfterSearch = driver.getCurrentUrl();
            System.out.println("URL after search: \"" + urlAfterSearch + "\"");
            check(urlAfterSearch.contains(query),
                "Expected URL to contain \"" + query + "\", got \"" + urlAfterSearch + "\"");
            System.out.println("✅ URL contains the searched place.");

            ///
            // Step 3: Click zoom in, then verify the URL's embedded zoom level
            // increased. This is the honest way to assert a canvas-rendered
            // map actually zoomed: read the state back out of the URL rather
            // than trying to inspect canvas pixels.
            ///

            double zoomBefore = getZoom(urlAfterSearch);
            driver.findElement(By.cssSelector("[aria-label=\"Zoom in\"]")).click();

            String urlAfterZoom = driver.getCurrentUrl();
            System.out.println("URL after zoom in: \"" + urlAfterZoom + "\"");
            double zoomAfter = getZoom(urlAfterZoom);
            System.out.println("Zoom before: " + zoomBefore + ", zoom after: " + zoomAfter);
            check(zoomAfter > zoomBefore,
                "Expected zoom to increase from " + zoomBefore + ", got " + zoomAfter);
            System.out.println("✅ URL zoom parameter increased.");

            System.out.println("\nAll checks passed. 🎉");
        } catch (Throwable err) {
            System.err.println("Fatal error: " + err.getMessage());
            System.exit(1);
        } finally {
            driver.quit();
        }
    }
}
