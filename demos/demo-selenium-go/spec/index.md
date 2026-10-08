# Spec

## Summary

This spec describes the exact browser-automation walkthrough that
`main.go` performs: using `github.com/tebeka/selenium` to open Chrome, navigating to the public
testing-examples site, exercising five locator strategies, and performing
four form interactions, logging the result of each step instead of asserting it.

## Scope

This spec covers the scenario implemented in `main.go`: the target
URL, every locator/selector it uses, and every form interaction it
performs, together with what "this demo still works" means.

This spec does NOT cover: how to install Go or `github.com/tebeka/selenium` or how to
run the program (see README.md), CI or build tooling, browsers other than
Chrome, or test-framework assertions — this program is a walkthrough, not a
test suite.

## Principles and rules

- This is a walkthrough program, not a test suite: it demonstrates locator
  strategies and form interactions by printing what it finds, and it does
  not assert expected outcomes with a test framework.
- The code and this spec describe the same scenario. If they ever diverge,
  that is a defect — fix it before making any other change.
- There is no official Go binding from the Selenium project itself. [`tebeka/selenium`](https://pkg.go.dev/github.com/tebeka/selenium) is the best-known Selenium/WebDriver client for Go, and is what this demo uses.

## Detail

Target URL: `https://testingexamples.github.io/en-001/practice/`

WebDriver server: a local `chromedriver` listening on `http://localhost:9515`.

Locator strategies demonstrated, in order:

1. By id — `driver.FindElement(selenium.ByID, "id-example-1")` — locates an element by its `id` attribute. Prints its outer HTML.
2. By name attribute — `driver.FindElement(selenium.ByName, "name-example-1")` — locates an element by its `name` attribute. Prints its outer HTML.
3. By class name — `driver.FindElement(selenium.ByClassName, "class-example-1")` — locates an element by CSS class. Prints its outer HTML.
4. By link text — `driver.FindElement(selenium.ByLinkText, "Link Example 1")` — locates a link by its visible text. Prints its outer HTML.
5. By XPath — `driver.FindElement(selenium.ByXPATH, "//input[@type='submit']")` — locates an element with an XPath expression. Prints its outer HTML.

Form interactions performed, in order:

1. Text input — selenium.ByID "text-example-1-id" — clears it, then types `"hello"` via `SendKeys`.
2. Checkbox — selenium.ByID "checkbox-example-1-id" — checks it via `Click`.
3. Radio button — selenium.ByID "radio-example-1-option-1-id" — checks it via `Click`.
4. Select — selenium.ByCSSSelector "#select-example-1-id option:first-child" — selects the option at index 0 by locating and clicking that `<option>` directly, then prints its `value` attribute. `tebeka/selenium` has no `Select` helper.

## Acceptance criteria

- The program navigates to `https://testingexamples.github.io/en-001/practice/` without error.
- Each of the five locators above resolves to exactly one element on the
  live page (no timeout or "no such element" error).
- The text input accepts the fill value `"hello"`, the checkbox and radio
  button end up checked, and the select ends up with the option at index 0
  selected.
- The program exits with status code 0 and no unhandled error.

## Related topics

- [../README.md](../README.md)
- [../AGENTS.md](../AGENTS.md)

## Sources

- [https://testingexamples.github.io/en-001/practice/](https://testingexamples.github.io/en-001/practice/)
- [https://pkg.go.dev/github.com/tebeka/selenium](https://pkg.go.dev/github.com/tebeka/selenium)
