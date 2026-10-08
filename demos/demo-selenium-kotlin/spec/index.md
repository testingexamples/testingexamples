# Spec

## Summary

This spec describes the exact browser-automation walkthrough that
`src/main/kotlin/demo/Main.kt` performs: using `selenium-java` to open Chrome, navigating to the public
testing-examples site, exercising five locator strategies, and performing
four form interactions, logging the result of each step instead of asserting it.

## Scope

This spec covers the scenario implemented in `src/main/kotlin/demo/Main.kt`: the target
URL, every locator/selector it uses, and every form interaction it
performs, together with what "this demo still works" means.

This spec does NOT cover: how to install Kotlin or `selenium-java` or how to
run the program (see README.md), CI or build tooling, browsers other than
Chrome, or test-framework assertions — this program is a walkthrough, not a
test suite.

## Principles and rules

- This is a walkthrough program, not a test suite: it demonstrates locator
  strategies and form interactions by printing what it finds, and it does
  not assert expected outcomes with a test framework.
- The code and this spec describe the same scenario. If they ever diverge,
  that is a defect — fix it before making any other change.
- Kotlin runs on the JVM, so this demo uses the official Selenium Java bindings (`selenium-java`) directly from Kotlin.

## Detail

Target URL: `https://testingexamples.github.io/en-001/practice/`

Locator strategies demonstrated, in order:

1. By id — `driver.findElement(By.id("id-example-1"))` — locates an element by its `id` attribute, after waiting for it to be visible. Prints its outer HTML.
2. By name attribute — `driver.findElement(By.name("name-example-1"))` — locates an element by its `name` attribute. Prints its outer HTML.
3. By class name — `driver.findElement(By.className("class-example-1"))` — locates an element by CSS class. Prints its outer HTML.
4. By link text — `driver.findElement(By.linkText("Link Example 1"))` — locates a link by its visible text. Prints its outer HTML.
5. By XPath — `driver.findElement(By.xpath("//input[@type='submit']"))` — locates an element with an XPath expression. Prints its outer HTML.

Form interactions performed, in order:

1. Text input — `By.id("text-example-1-id")` — clears it, then types `"hello"` via `sendKeys`.
2. Checkbox — `By.id("checkbox-example-1-id")` — checks it via `click()`.
3. Radio button — `By.id("radio-example-1-option-1-id")` — checks it via `click()`.
4. Select — `By.id("select-example-1-id")` — selects the option at index 0 via `Select(...).selectByIndex(0)`, then prints the selected option.

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
- [https://central.sonatype.com/artifact/org.seleniumhq.selenium/selenium-java](https://central.sonatype.com/artifact/org.seleniumhq.selenium/selenium-java)
