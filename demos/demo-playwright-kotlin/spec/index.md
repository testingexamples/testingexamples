# Spec

## Summary

This spec describes the exact browser-automation walkthrough that
`src/main/kotlin/demo/Main.kt` performs: using `com.microsoft.playwright:playwright` to open Chromium, navigating to the public
testing-examples site, exercising five locator strategies, and performing
four form interactions, logging the result of each step instead of asserting it.

## Scope

This spec covers the scenario implemented in `src/main/kotlin/demo/Main.kt`: the target
URL, every locator/selector it uses, and every form interaction it
performs, together with what "this demo still works" means.

This spec does NOT cover: how to install Kotlin or `com.microsoft.playwright:playwright` or how to
run the program (see README.md), CI or build tooling, browsers other than
Chromium, or test-framework assertions — this program is a walkthrough, not a
test suite.

## Principles and rules

- This is a walkthrough program, not a test suite: it demonstrates locator
  strategies and form interactions by printing what it finds, and it does
  not assert expected outcomes with a test framework.
- The code and this spec describe the same scenario. If they ever diverge,
  that is a defect — fix it before making any other change.
- Kotlin runs on the JVM, so this demo uses the official Playwright for Java library (`com.microsoft.playwright:playwright`) directly from Kotlin.

## Detail

Target URL: `https://testingexamples.github.io/en-001/practice/`

Locator strategies demonstrated, in order:

1. By id — `page.locator("#id-example-1")` — locates an element by CSS id selector. Prints its outer HTML.
2. By name attribute — `page.locator("[name='name-example-1']")` — locates an element by attribute selector. Prints its outer HTML.
3. By class name — `page.locator(".class-example-1")` — locates an element by CSS class selector. Prints its outer HTML.
4. By link text — `page.locator("a", Page.LocatorOptions().setHasText("Link Example 1"))` — locates a link by its visible text. Prints its outer HTML.
5. By XPath — `page.locator("xpath=//input[@type='submit']")` — locates an element with an XPath expression. Prints its outer HTML.

Form interactions performed, in order:

1. Text input — `#text-example-1-id` — fills it with `"hello"` via `fill`.
2. Checkbox — `#checkbox-example-1-id` — checks it via `check()`.
3. Radio button — `#radio-example-1-option-1-id` — checks it via `check()`.
4. Select — `#select-example-1-id` — selects the option at index 0 via `selectOption(SelectOption().setIndex(0))`, then prints the value from `inputValue()`.

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
- [https://central.sonatype.com/artifact/com.microsoft.playwright/playwright](https://central.sonatype.com/artifact/com.microsoft.playwright/playwright)
