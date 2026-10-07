// Demo of Playwright browser automation with C#, teaching the locator
// strategies and interaction patterns for Google Search.
//
// IMPORTANT — DO NOT RUN THIS AGAINST LIVE google.com:
// Google's Terms of Service restrict automated querying of its services.
// This code exists to teach syntax and interaction *patterns* — it is not
// meant to be executed against the live Google Search. Do not run this program,
// do not build it in order to run it, and do not point it at a real Google
// page. See README.md and AGENTS.md for the full caution.
//
// Please see the file README.md for more information.

using Microsoft.Playwright;

static void Check(bool ok, string message)
{
    if (!ok) throw new Exception(message);
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
    // Step 1: Connect to the Google home page, then verify the
    // page title is what we expect.
    ///

    await page.GotoAsync("https://www.google.com");

    var homeTitle = await page.TitleAsync();
    Console.WriteLine($"Home page title: \"{homeTitle}\"");
    Check(homeTitle == "Google", $"Expected home page title to be \"Google\", got \"{homeTitle}\"");
    Console.WriteLine("✅ Home page title is correct.");

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

    var searchBox = page.Locator("textarea[name=\"q\"]");
    await searchBox.FillAsync("testing examples");
    await searchBox.PressAsync("Enter");
    await page.WaitForLoadStateAsync();

    var resultsTitle = await page.TitleAsync();
    Console.WriteLine($"Search results page title: \"{resultsTitle}\"");
    Check(resultsTitle.Contains("testing examples"),
        $"Expected search results page title to contain \"testing examples\", got \"{resultsTitle}\"");
    Console.WriteLine("✅ Search results page title contains \"testing examples\".");

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

    await page.Locator("#search a").First.ClickAsync();
    await page.WaitForLoadStateAsync();

    var landedUrl = page.Url;
    var landedHost = new Uri(landedUrl).Host;
    Console.WriteLine($"Landed on URL: \"{landedUrl}\" (host: \"{landedHost}\")");
    Check(landedHost != "www.google.com",
        $"Expected to navigate away from www.google.com, but landed host was \"{landedHost}\"");
    Console.WriteLine("✅ Browser navigated away from google.com.");

    Console.WriteLine("\nAll checks passed. 🎉");
}
catch (Exception err)
{
    Console.Error.WriteLine($"Fatal error: {err.Message}");
    return 1;
}

return 0;
