// Demo of Selenium WebDriver browser automation with C#, teaching the
// locator strategies and interaction patterns for Google Maps.
//
// IMPORTANT — DO NOT RUN THIS AGAINST LIVE google.com/maps:
// Google's Terms of Service restrict automated querying of its services.
// This code exists to teach syntax and interaction *patterns* — it is not
// meant to be executed against the live Google Maps. Do not run this program,
// do not build it in order to run it, and do not point it at a real Google
// Maps page. See README.md and AGENTS.md for the full caution.
//
// Please see the file README.md for more information.

using System.Text.RegularExpressions;
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;

static void Check(bool ok, string message)
{
    if (!ok) throw new Exception(message);
}

// Google Maps embeds the current view as "@lat,lng,zoomz" in the URL, e.g.
// https://www.google.com/maps/place/Cardiff/@51.4816546,-3.1791934,12z/...
// We use this to read back the zoom level, because the map itself renders
// mostly to a <canvas> (or WebGL) element that ordinary element locators
// cannot inspect.
static double GetZoom(string url)
{
    var match = Regex.Match(url, @"@(-?[\d.]+),(-?[\d.]+),(\d+(?:\.\d+)?)z");
    if (!match.Success) throw new Exception($"Could not find \"@lat,lng,zoomz\" in URL: {url}");
    return double.Parse(match.Groups[3].Value, System.Globalization.CultureInfo.InvariantCulture);
}

var options = new ChromeOptions();
options.AddArgument("--disable-notifications"); // Disable notifications such as popups.
options.AddUserProfilePreference("profile.default_content_setting_values.cookies", 2); // Reject cookies.

using IWebDriver driver = new ChromeDriver(options);

try
{
    ///
    // Step 1: Load Google Maps, then verify the page title mentions
    // Google Maps.
    ///

    driver.Navigate().GoToUrl("https://www.google.com/maps");

    var title = driver.Title;
    Console.WriteLine($"Page title: \"{title}\"");
    Check(title.Contains("Google Maps"), $"Expected page title to contain \"Google Maps\", got \"{title}\"");
    Console.WriteLine("✅ Page title contains \"Google Maps\".");

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

    var query = "Cardiff";
    var searchBox = driver.FindElement(By.CssSelector("[aria-label=\"Search Google Maps\"]"));
    searchBox.SendKeys(query + Keys.Return);

    var urlAfterSearch = driver.Url;
    Console.WriteLine($"URL after search: \"{urlAfterSearch}\"");
    Check(urlAfterSearch.Contains(query),
        $"Expected URL to contain \"{query}\", got \"{urlAfterSearch}\"");
    Console.WriteLine("✅ URL contains the searched place.");

    ///
    // Step 3: Click zoom in, then verify the URL's embedded zoom level
    // increased. This is the honest way to assert a canvas-rendered
    // map actually zoomed: read the state back out of the URL rather
    // than trying to inspect canvas pixels.
    ///

    var zoomBefore = GetZoom(urlAfterSearch);
    driver.FindElement(By.CssSelector("[aria-label=\"Zoom in\"]")).Click();

    var urlAfterZoom = driver.Url;
    Console.WriteLine($"URL after zoom in: \"{urlAfterZoom}\"");
    var zoomAfter = GetZoom(urlAfterZoom);
    Console.WriteLine($"Zoom before: {zoomBefore}, zoom after: {zoomAfter}");
    Check(zoomAfter > zoomBefore, $"Expected zoom to increase from {zoomBefore}, got {zoomAfter}");
    Console.WriteLine("✅ URL zoom parameter increased.");

    Console.WriteLine("\nAll checks passed. 🎉");
}
catch (Exception err)
{
    Console.Error.WriteLine($"Fatal error: {err.Message}");
    return 1;
}

return 0;
