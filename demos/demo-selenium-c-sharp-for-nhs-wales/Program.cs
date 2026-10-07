// Demo of Selenium WebDriver browser automation with C#, using the
// NHS Wales website <https://www.nhs.wales/> as a friendly real-world example.
//
// This is a beginner-friendly walkthrough: each step is commented so you can
// follow along and adapt the pattern to your own site.
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

// Selenium Manager finds or downloads a matching chromedriver.
// `using` calls Dispose, which quits the browser even if an exception is thrown.
using IWebDriver driver = new ChromeDriver(options);

try
{
    ///
    // Step 1: Connect to the NHS Wales home page, then verify the
    // page title is what we expect.
    ///

    driver.Navigate().GoToUrl("https://www.nhs.wales/");

    var homeTitle = driver.Title;
    Console.WriteLine($"Home page title: \"{homeTitle}\"");
    Check(homeTitle == "Home - NHS Wales",
        $"Expected home page title to be \"Home - NHS Wales\", got \"{homeTitle}\"");
    Console.WriteLine("✅ Home page title is correct.");

    ///
    // Step 2: Click the "About Us" link, then verify the response
    // page has the title and headline we expect.
    ///

    driver.FindElement(By.LinkText("About Us")).Click();

    var aboutTitle = driver.Title;
    Console.WriteLine($"About Us page title: \"{aboutTitle}\"");
    Check(aboutTitle == "About Us - NHS Wales",
        $"Expected About Us page title to be \"About Us - NHS Wales\", got \"{aboutTitle}\"");
    Console.WriteLine("✅ About Us page title is correct.");

    var headline = driver.FindElement(By.TagName("h1")).Text.Trim();
    Console.WriteLine($"About Us headline: \"{headline}\"");
    Check(headline == "About Us",
        $"Expected About Us headline to be \"About Us\", got \"{headline}\"");
    Console.WriteLine("✅ About Us headline is correct.");

    ///
    // Step 3: Use the page search box: type "help", click the search
    // button, then verify the response page contains the phrases
    // we expect.
    ///

    driver.Navigate().GoToUrl("https://www.nhs.wales/");
    driver.FindElement(By.Id("navKeywords")).SendKeys("help");
    driver.FindElement(By.Id("button-addon")).Click();

    var bodyText = driver.FindElement(By.TagName("body")).Text;
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
