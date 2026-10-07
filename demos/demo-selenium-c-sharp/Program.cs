// Demo of Selenium browser automation with C#
// Please see the file README.md for more information.

using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;
using OpenQA.Selenium.Support.UI;

var options = new ChromeOptions();
options.AddArgument("--disable-notifications"); // Disable notifications such as popups.

// Selenium Manager finds or downloads a matching chromedriver.
// `using` calls Dispose, which quits the browser even if an exception is thrown.
using IWebDriver driver = new ChromeDriver(options);

driver.Navigate().GoToUrl("https://testingexamples.github.io/en-001/practice/");

// Selenium does not auto-wait, so wait explicitly for the page content.
var wait = new WebDriverWait(driver, TimeSpan.FromSeconds(10));

// Find an element by id.
IWebElement elementById = wait.Until(d =>
{
    var e = d.FindElement(By.Id("id-example-1"));
    return e.Displayed ? e : null;
})!;
Console.WriteLine(elementById.GetAttribute("outerHTML"));

// Find an element by name.
IWebElement elementByName = driver.FindElement(By.Name("name-example-1"));
Console.WriteLine(elementByName.GetAttribute("outerHTML"));

// Find an element by class name.
IWebElement elementByClassName = driver.FindElement(By.ClassName("class-example-1"));
Console.WriteLine(elementByClassName.GetAttribute("outerHTML"));

// Find a link element by its text.
IWebElement elementByLinkText = driver.FindElement(By.LinkText("Link Example 1"));
Console.WriteLine(elementByLinkText.GetAttribute("outerHTML"));

// Find an element by XPath query.
IWebElement elementByXPath = driver.FindElement(By.XPath("//input[@type='submit']"));
Console.WriteLine(elementByXPath.GetAttribute("outerHTML"));

// Form controls: locate, scroll into view, and act together inside one explicit
// wait. The page re-renders while it hydrates, which can make a located element go
// stale, and the page scrolls smoothly (so scroll with behavior 'instant', or the
// click lands mid-animation); retrying the whole
// step until it succeeds handles both.
var formWait = new WebDriverWait(driver, TimeSpan.FromSeconds(10));
formWait.IgnoreExceptionTypes(typeof(StaleElementReferenceException), typeof(ElementClickInterceptedException));
var js = (IJavaScriptExecutor)driver;

void ActOn(By by, Action<IWebElement> action) => formWait.Until(d =>
{
    var element = d.FindElement(by);
    js.ExecuteScript("arguments[0].scrollIntoView({block: 'center', behavior: 'instant'})", element);
    Console.WriteLine(element.GetAttribute("outerHTML"));
    action(element);
    return true;
});

// Type in a text input.
ActOn(By.Id("text-example-1-id"), text =>
{
    text.Clear();
    text.SendKeys("hello");
});

// Click a checkbox input.
ActOn(By.Id("checkbox-example-1-id"), checkbox => checkbox.Click());

// Click a radio input.
ActOn(By.Id("radio-example-1-option-1-id"), radio => radio.Click());

// Choose a select input option by index.
ActOn(By.Id("select-example-1-id"), selectElement =>
{
    var select = new SelectElement(selectElement);
    select.SelectByIndex(0);
    Console.WriteLine(select.SelectedOption.GetAttribute("outerHTML"));
});
