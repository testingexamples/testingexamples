// Demo of Playwright browser automation with C#, using the
// NHS Wales website <https://www.nhs.wales/> as a friendly real-world example.
//
// This is a beginner-friendly walkthrough: each step is commented so you can
// follow along and adapt the pattern to your own site.
//
// Please see the file README.md for more information.

using Microsoft.Playwright;

static void Check(bool ok, string message)
{
    if (!ok) throw new Exception(message);
}

// `using` / `await using` dispose everything even if an exception is thrown.
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
    // Step 1: Connect to the NHS Wales home page, then verify the
    // page title is what we expect.
    ///

    await page.GotoAsync("https://www.nhs.wales/");

    var homeTitle = await page.TitleAsync();
    Console.WriteLine($"Home page title: \"{homeTitle}\"");
    Check(homeTitle == "Home - NHS Wales",
        $"Expected home page title to be \"Home - NHS Wales\", got \"{homeTitle}\"");
    Console.WriteLine("✅ Home page title is correct.");

    ///
    // Step 2: Click the "About Us" link, then verify the response
    // page has the title and headline we expect.
    ///

    await page.GetByRole(AriaRole.Menuitem, new() { Name = "About Us", Exact = true }).First.ClickAsync();
    await page.WaitForLoadStateAsync();

    var aboutTitle = await page.TitleAsync();
    Console.WriteLine($"About Us page title: \"{aboutTitle}\"");
    Check(aboutTitle == "About Us - NHS Wales",
        $"Expected About Us page title to be \"About Us - NHS Wales\", got \"{aboutTitle}\"");
    Console.WriteLine("✅ About Us page title is correct.");

    var headline = (await page.Locator("h1").First.InnerTextAsync()).Trim();
    Console.WriteLine($"About Us headline: \"{headline}\"");
    Check(headline == "About Us",
        $"Expected About Us headline to be \"About Us\", got \"{headline}\"");
    Console.WriteLine("✅ About Us headline is correct.");

    ///
    // Step 3: Use the page search box: type "help", click the search
    // button, then verify the response page contains the phrases
    // we expect.
    ///

    await page.GotoAsync("https://www.nhs.wales/");
    await page.Locator("#navKeywords").FillAsync("help");
    await page.Locator("#button-addon").ClickAsync();
    await page.WaitForLoadStateAsync();

    var bodyText = await page.Locator("body").InnerTextAsync();
    Check(bodyText.Contains("Search Results"), "Expected page to contain \"Search Results\"");
    Console.WriteLine("✅ Search results page contains \"Search Results\".");
    Check(bodyText.Contains("Your search for \"help\""),
        "Expected page to contain 'Your search for \"help\"'");
    Console.WriteLine("✅ Search results page contains 'Your search for \"help\"'.");

    Console.WriteLine("\nAll checks passed. 🎉");
}
catch (Exception err)
{
    Console.Error.WriteLine($"Fatal error: {err.Message}");
    return 1;
}

return 0;
