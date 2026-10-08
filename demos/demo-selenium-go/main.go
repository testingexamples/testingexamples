// Demo of Selenium browser automation with Go.
// Please see the file README.md for more information.
package main

import (
	"fmt"
	"log"
	"time"

	"github.com/tebeka/selenium"
	"github.com/tebeka/selenium/chrome"
)

// chromedriver must already be running on this port; see README.md.
const chromedriverURL = "http://localhost:9515"

func check(err error, what string) {
	if err != nil {
		log.Fatalf("%s: %v", what, err)
	}
}

// outerHTML returns the outer HTML of an element.
func outerHTML(element selenium.WebElement) string {
	html, err := element.GetAttribute("outerHTML")
	check(err, "could not read outerHTML")
	return html
}

func main() {
	caps := selenium.Capabilities{"browserName": "chrome"}
	caps.AddChrome(chrome.Capabilities{
		Args: []string{"--disable-notifications"}, // Disable notifications such as popups.
	})

	driver, err := selenium.NewRemote(caps, chromedriverURL)
	check(err, "could not start a session")
	defer driver.Quit() // Quits the browser even if a later step exits early with an error.

	check(driver.Get("https://testingexamples.github.io/en-001/practice/"), "could not go to the page")

	// Selenium does not auto-wait, so wait explicitly for the page content.
	check(driver.WaitWithTimeout(func(d selenium.WebDriver) (bool, error) {
		element, err := d.FindElement(selenium.ByID, "id-example-1")
		if err != nil {
			return false, nil
		}
		return element.IsDisplayed()
	}, 10*time.Second), "the page content did not appear")

	// Find an element by id.
	elementByID, err := driver.FindElement(selenium.ByID, "id-example-1")
	check(err, "by id")
	fmt.Println(outerHTML(elementByID))

	// Find an element by name.
	elementByName, err := driver.FindElement(selenium.ByName, "name-example-1")
	check(err, "by name")
	fmt.Println(outerHTML(elementByName))

	// Find an element by class name.
	elementByClassName, err := driver.FindElement(selenium.ByClassName, "class-example-1")
	check(err, "by class name")
	fmt.Println(outerHTML(elementByClassName))

	// Find a link element by its text.
	elementByLinkText, err := driver.FindElement(selenium.ByLinkText, "Link Example 1")
	check(err, "by link text")
	fmt.Println(outerHTML(elementByLinkText))

	// Find an element by XPath query.
	elementByXPath, err := driver.FindElement(selenium.ByXPATH, "//input[@type='submit']")
	check(err, "by XPath")
	fmt.Println(outerHTML(elementByXPath))

	// Form controls: locate, scroll into view, and act together, retrying until
	// the step succeeds. The page re-renders while it hydrates, which can make a
	// located element go stale, and it scrolls smoothly (so scroll with behavior
	// 'instant', or a click lands mid-animation).
	actOn := func(by, value string, action func(selenium.WebElement) error) {
		check(driver.WaitWithTimeout(func(d selenium.WebDriver) (bool, error) {
			element, err := d.FindElement(by, value)
			if err != nil {
				return false, nil
			}
			if _, err := d.ExecuteScript(
				"arguments[0].scrollIntoView({block: 'center', behavior: 'instant'})",
				[]interface{}{element}); err != nil {
				return false, nil
			}
			fmt.Println(outerHTML(element))
			return action(element) == nil, nil
		}, 10*time.Second), "could not act on "+value)
	}

	// Type in a text input.
	actOn(selenium.ByID, "text-example-1-id", func(text selenium.WebElement) error {
		if err := text.Clear(); err != nil {
			return err
		}
		return text.SendKeys("hello")
	})

	// Click a checkbox input.
	actOn(selenium.ByID, "checkbox-example-1-id", func(checkbox selenium.WebElement) error {
		return checkbox.Click()
	})

	// Click a radio input.
	actOn(selenium.ByID, "radio-example-1-option-1-id", func(radio selenium.WebElement) error {
		return radio.Click()
	})

	// Choose a select input option by index: click the first <option>.
	actOn(selenium.ByCSSSelector, "#select-example-1-id option:first-child", func(option selenium.WebElement) error {
		if err := option.Click(); err != nil {
			return err
		}
		value, err := option.GetAttribute("value")
		if err != nil {
			return err
		}
		fmt.Println("Selected option value:", value)
		return nil
	})
}
