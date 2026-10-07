// Demo of Playwright browser automation with C#, teaching the locator
// strategies and interaction patterns for Google Maps.
//
// IMPORTANT — DO NOT RUN THIS AGAINST LIVE google.com/maps:
// Google's Terms of Service restrict automated querying of its services.
// This code exists to teach syntax and interaction *patterns* — it is not
// meant to be executed against the live Google Maps. Do not run this program,
// do not build it in order to run it, and do not point it at a real Google
// Maps page. See README.md and AGENTS.md for the full caution.
//
// Please see the file README.md for more information.

using System.Globalization;
using System.Text.RegularExpressions;
using Microsoft.Playwright;

static void Check(bool ok, string message)
{
    if (!ok) throw new Exception(message);
}

// Extract the zoom level from a Google Maps URL.
//
// Google Maps encodes the current view as `@lat,lng,zoomz` in the URL
// path, for example:
//
//     https://www.google.com/maps/place/Cardiff+Castle/@51.4816,-3.1815,17z
//
// The trailing `z` marks the zoom-level number. This helper pulls that
// number out so we can compare it before and after a zoom interaction,
// since we cannot inspect the canvas-rendered map directly.
static double ExtractZoom(string url)
{
    var match = Regex.Match(url, @"@[-0-9.]+,[-0-9.]+,([0-9.]+)z");
    if (!match.Success)
        throw new Exception($"Expected URL to contain an \"@lat,lng,zoomz\" segment, got \"{url}\"");
    return double.Parse(match.Groups[1].Value, CultureInfo.InvariantCulture);
}

using var playwright = await Playwright.CreateAsync();
await using var browser = await playwright.Chromium.LaunchAsync(new()
{
    Headless = false, // Set to true for headless mode.
});
var context = await browser.NewContextAsync();
var page = await context.NewPageAsync();

try
{
    ///
    // Step 1: Connect to Google Maps, then verify the page title.
    ///

    await page.GotoAsync("https://www.google.com/maps");

    var homeTitle = await page.TitleAsync();
    Console.WriteLine($"Maps page title: \"{homeTitle}\"");
    Check(homeTitle.Contains("Google Maps"),
        $"Expected page title to contain \"Google Maps\", got \"{homeTitle}\"");
    Console.WriteLine("✅ Page title contains \"Google Maps\".");

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

    var searchBox = page.Locator("[aria-label=\"Search Google Maps\"]");
    await searchBox.FillAsync("Cardiff Castle");
    await searchBox.PressAsync("Enter");
    await page.WaitForLoadStateAsync();

    var searchedUrl = page.Url;
    Console.WriteLine($"URL after search: \"{searchedUrl}\"");
    Check(searchedUrl.Contains("Cardiff"), $"Expected URL to contain \"Cardiff\", got \"{searchedUrl}\"");
    Console.WriteLine("✅ URL contains \"Cardiff\".");

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

    var zoomBefore = ExtractZoom(page.Url);
    Console.WriteLine($"Zoom level before: {zoomBefore}");

    await page.Locator("[aria-label=\"Zoom in\"]").ClickAsync();
    await page.WaitForTimeoutAsync(1000); // allow the URL to sync after the animation

    var zoomAfter = ExtractZoom(page.Url);
    Console.WriteLine($"Zoom level after: {zoomAfter}");
    Check(zoomAfter > zoomBefore,
        $"Expected zoom level to increase after clicking Zoom in, went from {zoomBefore} to {zoomAfter}");
    Console.WriteLine("✅ Zoom level increased after clicking Zoom in.");

    Console.WriteLine("\nAll checks passed. 🎉");
}
catch (Exception err)
{
    Console.Error.WriteLine($"Fatal error: {err.Message}");
    return 1;
}

return 0;
