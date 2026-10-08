---
name: demo-selenium-go-skill
description: Use when asked to run, explain, or extend the demo-selenium-go locator-strategy walkthrough, or to build a similar Selenium demo in Go against a different site.
---

# Demo Selenium Go Skill

This repo teaches five Selenium locator strategies and four form
interactions against the public page https://testingexamples.github.io/en-001/practice/,
using `github.com/tebeka/selenium`. There is no official Go binding from the Selenium project itself. [`tebeka/selenium`](https://pkg.go.dev/github.com/tebeka/selenium) is the best-known Selenium/WebDriver client for Go, and is what this demo uses.

Locator strategies: by id (`driver.FindElement(selenium.ByID, "id-example-1")`), by name attribute (`driver.FindElement(selenium.ByName, "name-example-1")`), by class name (`driver.FindElement(selenium.ByClassName, "class-example-1")`), by link text (`driver.FindElement(selenium.ByLinkText, "Link Example 1")`), by xpath (`driver.FindElement(selenium.ByXPATH, "//input[@type='submit']")`).

Form interactions: text input (selenium.ByID "text-example-1-id"); checkbox (selenium.ByID "checkbox-example-1-id"); radio button (selenium.ByID "radio-example-1-option-1-id"); select (selenium.ByCSSSelector "#select-example-1-id option:first-child").

Each step prints the located element's outer HTML or value — this is a
walkthrough that demonstrates locators, not a test suite with assertions.

## Running it

1. Install Go and `chromedriver` per README.md.
2. Start `chromedriver --port=9515` in one terminal.
3. Run `go run .` in another terminal. A visible (non-headless) Chrome window opens, walks the page, and closes.

## Adapting it to a different site or selectors

1. Update the target URL passed to `driver.Get(...)`.
2. Update each locator string to match the new page's real ids/classes/attributes/link text/XPath.
3. Update the values used to fill and click to match the new form's real inputs.
4. Update `spec/index.md` to describe the new scenario verbatim, in the same change — not after.

This skill summarizes the repo. `AGENTS.md` and `spec/index.md` are the
source of truth — if this skill's summary ever disagrees with those, they
win.
