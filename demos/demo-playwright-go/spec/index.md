# Spec

## Summary

This spec describes the exact browser-automation walkthrough that
`main.go` performs: using `github.com/playwright-community/playwright-go` to open Chromium, navigating to the public
testing-examples site, exercising five locator strategies, and performing
four form interactions, logging the result of each step instead of asserting it.

## Scope

This spec covers the scenario implemented in `main.go`: the target
URL, every locator/selector it uses, and every form interaction it
performs, together with what "this demo still works" means.

This spec does NOT cover: how to install Go or `github.com/playwright-community/playwright-go` or how to
run the program (see README.md), CI or build tooling, browsers other than
Chromium, or test-framework assertions — this program is a walkthrough, not a
test suite.

## Principles and rules

- This is a walkthrough program, not a test suite: it demonstrates locator
  strategies and form interactions by printing what it finds, and it does
  not assert expected outcomes with a test framework.
- The code and this spec describe the same scenario. If they ever diverge,
  that is a defect — fix it before making any other change.
- There is no official Go binding from the Playwright project itself. [`playwright-community/playwright-go`](https://github.com/playwright-community/playwright-go) is the community-maintained Playwright client for Go, and is what this demo uses. It is pinned to `v0.5001.0` (Playwright 1.50) in [go.mod](go.mod), the latest tag whose driver download works at the time of writing.

## Detail

Target URL: `https://testingexamples.github.io/en-001/practice/`

Locator strategies demonstrated, in order:

1. By id — `page.Locator("#id-example-1")` — locates an element by CSS id selector. Prints its outer HTML.
2. By name attribute — `page.Locator("[name='name-example-1']")` — locates an element by attribute selector. Prints its outer HTML.
3. By class name — `page.Locator(".class-example-1")` — locates an element by CSS class selector. Prints its outer HTML.
4. By link text — `page.Locator("a", playwright.PageLocatorOptions{HasText: "Link Example 1"})` — locates a link by its visible text. Prints its outer HTML.
5. By XPath — `page.Locator("xpath=//input[@type='submit']")` — locates an element with an XPath expression. Prints its outer HTML.

Form interactions performed, in order:

1. Text input — `#text-example-1-id` — fills it with `"hello"` via `Fill`.
2. Checkbox — `#checkbox-example-1-id` — checks it via `Check`.
3. Radio button — `#radio-example-1-option-1-id` — checks it via `Check`.
4. Select — `#select-example-1-id` — selects the option at index 0 via `SelectOption` with `Indexes: []int{0}`, then prints the value from `InputValue`.

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
- [https://pkg.go.dev/github.com/playwright-community/playwright-go](https://pkg.go.dev/github.com/playwright-community/playwright-go)
