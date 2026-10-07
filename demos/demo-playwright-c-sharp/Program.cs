// Demo of Playwright browser automation with C#
// Please see the file README.md for more information.

using Microsoft.Playwright;

// `using` / `await using` dispose everything even if an exception is thrown.
using var playwright = await Playwright.CreateAsync();
await using var browser = await playwright.Chromium.LaunchAsync(new()
{
    Headless = false, // Set to true for headless mode.
    Args = ["--disable-notifications"], // Disable notifications such as popups.
});
var context = await browser.NewContextAsync();
var page = await context.NewPageAsync();

await page.GotoAsync("https://testingexamples.github.io/en-001/practice/");

// Playwright locators auto-wait and retry; no explicit waits are needed.

static async Task<string> OuterHtml(ILocator locator) =>
    await locator.EvaluateAsync<string>("el => el.outerHTML");

// Find an element by id.
Console.WriteLine(await OuterHtml(page.Locator("#id-example-1")));

// Find an element by name attribute.
Console.WriteLine(await OuterHtml(page.Locator("[name='name-example-1']")));

// Find an element by class name.
Console.WriteLine(await OuterHtml(page.Locator(".class-example-1")));

// Find a link element by its text.
Console.WriteLine(await OuterHtml(page.Locator("a", new() { HasTextString = "Link Example 1" })));

// Find an element by XPath.
Console.WriteLine(await OuterHtml(page.Locator("xpath=//input[@type='submit']")));

// Fill a text input.
var text = page.Locator("#text-example-1-id");
Console.WriteLine(await OuterHtml(text));
await text.FillAsync("hello");

// Check a checkbox.
var checkbox = page.Locator("#checkbox-example-1-id");
Console.WriteLine(await OuterHtml(checkbox));
await checkbox.CheckAsync();

// Check a radio button.
var radio = page.Locator("#radio-example-1-option-1-id");
Console.WriteLine(await OuterHtml(radio));
await radio.CheckAsync();

// Select an option by index.
var select = page.Locator("#select-example-1-id");
Console.WriteLine(await OuterHtml(select));
await select.SelectOptionAsync(new SelectOptionValue { Index = 0 });
Console.WriteLine($"Selected option value: {await select.InputValueAsync()}");
