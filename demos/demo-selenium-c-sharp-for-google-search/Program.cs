// Demo of Selenium WebDriver browser automation with C#, teaching the
// locator strategies and interaction patterns for a Google Search results page.
//
// IMPORTANT — DO NOT RUN THIS AGAINST LIVE google.com:
// Google's Terms of Service restrict automated querying of its services.
// This code exists to teach syntax and interaction *patterns* — it is not
// meant to be executed against the live Google Search. Do not run this program,
// do not build it in order to run it, and do not point it at a real Google
// page. See README.md and AGENTS.md for the full caution.
//
// Please see the file README.md for more information.

using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;

static void Check(bool ok, string message)
{
    if (!ok) throw new Exception(message);
}

var options = new ChromeOptions();
options.AddArgument("--disable-notifications"); // Disable notifications such as popups.
options.AddUserProfilePreference("profile.default_content_setting_values.cookies", 2); // Reject cookies.

using IWebDriver driver = new ChromeDriver(options);

try
{
    ///
    // Step 1: Load the Google Search home page, then verify the page
    // title is what we expect.
    ///

    driver.Navigate().GoToUrl("https://www.google.com");

    var title = driver.Title;
    Console.WriteLine($"Page title: \"{title}\"");
    Check(title == "Google", $"Expected page title to be \"Google\", got \"{title}\"");
    Console.WriteLine("✅ Page title is correct.");

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

    var query = "testing examples";
    var searchBox = driver.FindElement(By.Name("q"));
    searchBox.SendKeys(query + Keys.Return);

    var resultTitle = driver.Title;
    Console.WriteLine($"Result page title: \"{resultTitle}\"");
    Check(resultTitle.Contains(query),
        $"Expected result page title to contain \"{query}\", got \"{resultTitle}\"");
    Console.WriteLine("✅ Result page title contains the search query.");

    ///
    // Step 3: Click the first result, then verify we navigated away
    // from www.google.com to some other hostname.
    ///

    driver.FindElement(By.CssSelector("#search a")).Click();

    var hostname = new Uri(driver.Url).Host;
    Console.WriteLine($"Result hostname: \"{hostname}\"");
    Check(hostname != "www.google.com",
        $"Expected hostname to change away from www.google.com, got \"{hostname}\"");
    Console.WriteLine("✅ Navigated away from www.google.com.");

    Console.WriteLine("\nAll checks passed. 🎉");
}
catch (Exception err)
{
    Console.Error.WriteLine($"Fatal error: {err.Message}");
    return 1;
}

return 0;
