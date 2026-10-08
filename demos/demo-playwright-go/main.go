// Demo of Playwright browser automation with Go.
// Please see the file README.md for more information.
package main

import (
	"fmt"
	"log"

	"github.com/playwright-community/playwright-go"
)

// outerHTML returns the outer HTML of the element a locator points at.
func outerHTML(locator playwright.Locator) string {
	value, err := locator.Evaluate("el => el.outerHTML", nil)
	if err != nil {
		log.Fatalf("could not read outerHTML: %v", err)
	}
	return fmt.Sprint(value)
}

func check(err error, what string) {
	if err != nil {
		log.Fatalf("%s: %v", what, err)
	}
}

func main() {
	pw, err := playwright.Run()
	check(err, "could not start Playwright")
	defer pw.Stop()

	browser, err := pw.Chromium.Launch(playwright.BrowserTypeLaunchOptions{
		Headless: playwright.Bool(false),              // Set to true for headless mode.
		Args:     []string{"--disable-notifications"}, // Disable notifications such as popups.
	})
	check(err, "could not launch Chromium")
	defer browser.Close()

	page, err := browser.NewPage()
	check(err, "could not create a page")

	_, err = page.Goto("https://testingexamples.github.io/en-001/practice/")
	check(err, "could not go to the page")

	// Playwright locators auto-wait and retry; no explicit waits are needed.

	// Find an element by id.
	fmt.Println(outerHTML(page.Locator("#id-example-1")))

	// Find an element by name attribute.
	fmt.Println(outerHTML(page.Locator("[name='name-example-1']")))

	// Find an element by class name.
	fmt.Println(outerHTML(page.Locator(".class-example-1")))

	// Find a link element by its text.
	fmt.Println(outerHTML(page.Locator("a", playwright.PageLocatorOptions{
		HasText: "Link Example 1",
	})))

	// Find an element by XPath.
	fmt.Println(outerHTML(page.Locator("xpath=//input[@type='submit']")))

	// Fill a text input.
	text := page.Locator("#text-example-1-id")
	fmt.Println(outerHTML(text))
	check(text.Fill("hello"), "could not fill the text input")

	// Check a checkbox.
	checkbox := page.Locator("#checkbox-example-1-id")
	fmt.Println(outerHTML(checkbox))
	check(checkbox.Check(), "could not check the checkbox")

	// Check a radio button.
	radio := page.Locator("#radio-example-1-option-1-id")
	fmt.Println(outerHTML(radio))
	check(radio.Check(), "could not check the radio button")

	// Select an option by index.
	select_ := page.Locator("#select-example-1-id")
	fmt.Println(outerHTML(select_))
	_, err = select_.SelectOption(playwright.SelectOptionValues{
		Indexes: &[]int{0},
	})
	check(err, "could not select the option")
	value, err := select_.InputValue()
	check(err, "could not read the selected value")
	fmt.Println("Selected option value:", value)
}
