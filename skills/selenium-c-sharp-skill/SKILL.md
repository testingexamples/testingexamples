---
name: selenium-c-sharp-skill
description: Use when asked to write, explain, debug, or extend Selenium WebDriver browser automation code in C# (.NET) — locating elements, performing actions, explicit waits, and writing real assertions with NUnit.
---

# Selenium C# Skill

## What Selenium is

Selenium (https://www.selenium.dev/) drives real browsers through the W3C WebDriver protocol. The .NET binding is the `Selenium.WebDriver` NuGet package (4.x). **Selenium Manager** (built in since 4.6) downloads and locates the matching `chromedriver` / `geckodriver` automatically, so no manual driver install is needed.

```sh
dotnet add package Selenium.WebDriver
dotnet add package NUnit
dotnet add package NUnit3TestAdapter
dotnet add package Microsoft.NET.Test.Sdk
```

`WebDriverWait` is in the `OpenQA.Selenium.Support.UI` namespace of `Selenium.WebDriver` itself. `SelectElement` (same namespace) ships in the separate **`Selenium.Support`** package — verified missing from `Selenium.WebDriver` 4.50 — so add it when you need `<select>` handling:

```sh
dotnet add package Selenium.Support
```

## Four core concepts: locate, act, wait, assert

### 1. Locate — `driver.FindElement(By...)`

```csharp
driver.FindElement(By.Id("id-example-1"));
driver.FindElement(By.Name("name-example-1"));
driver.FindElement(By.ClassName("class-example-1"));
driver.FindElement(By.LinkText("Link Example 1"));
driver.FindElement(By.XPath("//input[@type='submit']"));
driver.FindElement(By.CssSelector("input[type='submit']"));
```

`FindElement` returns the first match or throws `NoSuchElementException`. `FindElements` returns an `IReadOnlyCollection<IWebElement>` (empty when nothing matches — it never throws).

### 2. Act — `IWebElement` members

```csharp
element.Text;                       // visible text (a property, not a method)
element.GetAttribute("value");      // attribute/property (null if absent)
element.SendKeys("hello");          // type into an input
element.Click();                    // click checkbox, radio, button, link...
new SelectElement(selectElement).SelectByIndex(0);   // <select> dropdowns
```

Note C# convention: methods are PascalCase (`Click`, `SendKeys`) and `Text`, `Selected`, `Displayed` are properties.

### 3. Wait — Selenium does NOT auto-wait

`FindElement` fails immediately if the element is not yet present. Use an **explicit wait**:

```csharp
var wait = new WebDriverWait(driver, TimeSpan.FromSeconds(10));
IWebElement element = wait.Until(d =>
{
    var e = d.FindElement(By.Id("id-example-1"));
    return e.Displayed ? e : null;   // returning null/false means "keep polling"
});
```

`NoSuchElementException` is ignored by default while polling, so a lambda that calls `FindElement` is a valid condition. Avoid mixing implicit waits (`driver.Manage().Timeouts().ImplicitWait`) with explicit waits.

### 4. Assert — see "From walkthrough to real test" below.

## Full worked example

A walkthrough against the free fixture page https://testingexamples.github.io/en-001/practice/ (top-level statements, .NET 6+):

```csharp
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;
using OpenQA.Selenium.Support.UI;

var options = new ChromeOptions();
options.AddArgument("--disable-notifications");

using IWebDriver driver = new ChromeDriver(options);   // Dispose() quits the browser

driver.Navigate().GoToUrl("https://testingexamples.github.io/en-001/practice/");

// Locate by id, name, class name, link text, and XPath.
Console.WriteLine(driver.FindElement(By.Id("id-example-1")).GetAttribute("outerHTML"));
Console.WriteLine(driver.FindElement(By.Name("name-example-1")).GetAttribute("outerHTML"));
Console.WriteLine(driver.FindElement(By.ClassName("class-example-1")).GetAttribute("outerHTML"));
Console.WriteLine(driver.FindElement(By.LinkText("Link Example 1")).GetAttribute("outerHTML"));
Console.WriteLine(driver.FindElement(By.XPath("//input[@type='submit']")).GetAttribute("outerHTML"));

// Fill a text input.
var text = driver.FindElement(By.Id("text-example-1-id"));
text.Clear();
text.SendKeys("hello");

// Click a checkbox and a radio button.
driver.FindElement(By.Id("checkbox-example-1-id")).Click();
driver.FindElement(By.Id("radio-example-1-option-1-id")).Click();

// Choose a select option by index.
var select = new SelectElement(driver.FindElement(By.Id("select-example-1-id")));
select.SelectByIndex(0);
Console.WriteLine($"Selected option value: {select.SelectedOption.GetAttribute("value")}");
```

`ChromeDriver` implements `IDisposable`; `using` guarantees `Quit` runs even if an exception is thrown. The script asserts nothing — it is a walkthrough, not a test.

## From walkthrough to real test

A **real test** uses a test framework (NUnit here) with assertions, and owns the browser lifecycle in `[SetUp]` / `[TearDown]`:

```csharp
using NUnit.Framework;
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;

[TestFixture]
public class FixtureTests
{
    private IWebDriver driver = null!;

    [SetUp]
    public void SetUp()
    {
        driver = new ChromeDriver();
        driver.Navigate().GoToUrl("https://testingexamples.github.io/en-001/practice/");
    }

    [TearDown]
    public void TearDown() => driver.Quit();

    [Test]
    public void IdExampleHasExpectedText()
    {
        Assert.That(driver.FindElement(By.Id("id-example-1")).Text, Is.EqualTo("Id Example 1"));
    }

    [Test]
    public void CheckboxCanBeChecked()
    {
        var checkbox = driver.FindElement(By.Id("checkbox-example-1-id"));
        checkbox.Click();
        Assert.That(checkbox.Selected, Is.True);
    }
}
```

Use NUnit's constraint model, `Assert.That(actual, Is.EqualTo(expected))`; the classic `Assert.AreEqual` is legacy. Run with `dotnet test`. Because Selenium does not retry, assert on state only after an explicit wait when the page is dynamic.

## Common pitfalls

- **Not quitting the driver on every path.** Use `using` or `[TearDown]`; otherwise browser and driver processes leak. `driver.Close()` closes only the current window.
- **Mixing implicit and explicit waits.** Pick explicit waits.
- **`StaleElementReferenceException`.** An `IWebElement` goes stale when the DOM re-renders. Re-locate rather than holding the reference.
- **`Thread.Sleep` for synchronization.** Slow and flaky; use `WebDriverWait`.
- **Sharing one `IWebDriver` across parallel tests.** It is not thread-safe; create one per test (NUnit `[Parallelizable]` fixtures each need their own).
- **`SelectElement` on a non-`<select>`.** It throws `UnexpectedTagNameException`.
- **Old Selenium 3 idioms.** `ExpectedConditions` (`SeleniumExtras.WaitHelpers`) is deprecated and unmaintained; use a lambda condition. Passing an integer to `WebDriverWait` is gone; pass a `TimeSpan`.

## Learn more

- https://github.com/testingexamples/demo-selenium-c-sharp — the runnable locator-strategy walkthrough this skill's examples are drawn from, run against https://testingexamples.github.io/en-001/practice/.
- https://www.selenium.dev/documentation/webdriver/ — official WebDriver documentation.
- https://www.selenium.dev/selenium/docs/api/dotnet/ — .NET API reference.
- https://testingexamples.github.io/en-001/practice/ — the free, stable fixture page used above; safe to run repeatedly.
- Google Search and Google Maps restrict automated querying in their Terms of Service; do not point repeated automation at them.

---

AGENTS.md and spec/index.md in this repo are the source of truth for this skill's own scope — if this file ever disagrees with those, they win.
