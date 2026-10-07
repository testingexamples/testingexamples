package demo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

/**
 * Demo of Playwright browser automation with Java, teaching the locator
 * strategies and interaction patterns for Google Maps.
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

    private static final Pattern ZOOM_PATTERN = Pattern.compile("@[-0-9.]+,[-0-9.]+,([0-9.]+)z");

    /**
     * Extract the zoom level from a Google Maps URL.
     *
     * Google Maps encodes the current view as `@lat,lng,zoomz` in the URL
     * path, for example:
     *
     *     https://www.google.com/maps/place/Cardiff+Castle/@51.4816,-3.1815,17z
     *
     * The trailing `z` marks the zoom-level number. This helper pulls that
     * number out so we can compare it before and after a zoom interaction,
     * since we cannot inspect the canvas-rendered map directly.
     */
    private static double extractZoom(String url) {
        Matcher match = ZOOM_PATTERN.matcher(url);
        if (!match.find()) {
            throw new AssertionError("Expected URL to contain an \"@lat,lng,zoomz\" segment, got \"" + url + "\"");
        }
        return Double.parseDouble(match.group(1));
    }

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
                // Step 1: Connect to Google Maps, then verify the page title.
                ///

                page.navigate("https://www.google.com/maps");

                String homeTitle = page.title();
                System.out.println("Maps page title: \"" + homeTitle + "\"");
                check(homeTitle.contains("Google Maps"),
                    "Expected page title to contain \"Google Maps\", got \"" + homeTitle + "\"");
                System.out.println("✅ Page title contains \"Google Maps\".");

                ///
                // Step 2: Use the search box: type "Cardiff Castle", press
                // Enter, then verify the URL updated to reflect the search.
                //
                // `[aria-label="Search Google Maps"]` targets the search input
                // by its accessible name rather than a generated class name.
                // Google Maps' CSS classes are minified and change between
                // deploys; the `aria-label` is part of the accessible-markup
                // contract the UI exposes to assistive technology, so it is far
                // more stable across Google's front-end changes.
                ///

                Locator searchBox = page.locator("[aria-label=\"Search Google Maps\"]");
                searchBox.fill("Cardiff Castle");
                searchBox.press("Enter");
                page.waitForLoadState();

                String searchedUrl = page.url();
                System.out.println("URL after search: \"" + searchedUrl + "\"");
                check(searchedUrl.contains("Cardiff"),
                    "Expected URL to contain \"Cardiff\", got \"" + searchedUrl + "\"");
                System.out.println("✅ URL contains \"Cardiff\".");

                ///
                // Step 3: Click the zoom-in button, then verify the URL's
                // embedded zoom level increased.
                //
                // Google Maps renders the map itself to canvas/WebGL, so we
                // cannot assert on any pin, label, or street drawn there. What
                // we *can* assert on is the URL: Google Maps keeps the current
                // view synced into the address bar as `@lat,lng,zoomz`, so a
                // successful zoom-in should raise that trailing zoom number.
                ///

                double zoomBefore = extractZoom(page.url());
                System.out.println("Zoom level before: " + zoomBefore);

                page.locator("[aria-label=\"Zoom in\"]").click();
                page.waitForTimeout(1000); // allow the URL to sync after the animation

                double zoomAfter = extractZoom(page.url());
                System.out.println("Zoom level after: " + zoomAfter);
                check(zoomAfter > zoomBefore,
                    "Expected zoom level to increase after clicking Zoom in, went from " + zoomBefore + " to " + zoomAfter);
                System.out.println("✅ Zoom level increased after clicking Zoom in.");

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
