# Spec

## Summary

This spec describes the exact browser-automation walkthrough that
`lib/demo.ex` performs: using `wallaby` to open Chrome, navigating to the public
testing-examples site, exercising five locator strategies, and performing
four form interactions, logging the result of each step instead of asserting it.

## Scope

This spec covers the scenario implemented in `lib/demo.ex`: the target
URL, every locator/selector it uses, and every form interaction it
performs, together with what "this demo still works" means.

This spec does NOT cover: how to install Elixir or `wallaby` or how to
run the program (see README.md), CI or build tooling, browsers other than
Chrome, or test-framework assertions — this program is a walkthrough, not a
test suite.

## Principles and rules

- This is a walkthrough program, not a test suite: it demonstrates locator
  strategies and form interactions by printing what it finds, and it does
  not assert expected outcomes with a test framework.
- The code and this spec describe the same scenario. If they ever diverge,
  that is a defect — fix it before making any other change.
- There is no official Elixir binding from the Selenium project itself. [`wallaby`](https://hex.pm/packages/wallaby) is the best-known WebDriver client for Elixir: it starts `chromedriver` and drives Chrome through the WebDriver protocol, which is the same protocol Selenium speaks. It is what this demo uses.

## Detail

Target URL: `https://testingexamples.github.io/en-001/practice/`

Locator strategies demonstrated, in order:

1. By id — `Browser.find(session, Query.css("#id-example-1"))` — locates an element by CSS id selector. Prints its outer HTML.
2. By name attribute — `Browser.find(session, Query.css("[name='name-example-1']"))` — Wallaby has no dedicated name query, so this uses a CSS attribute selector. Prints its outer HTML.
3. By class name — `Browser.find(session, Query.css(".class-example-1"))` — locates an element by CSS class selector. Prints its outer HTML.
4. By link text — `Browser.find(session, Query.link("Link Example 1"))` — locates a link by its visible text. Prints its outer HTML.
5. By XPath — `Browser.find(session, Query.xpath("//input[@type='submit']"))` — locates an element with an XPath expression. Prints its outer HTML.

Form interactions performed, in order:

1. Text input — `Query.css("#text-example-1-id")` — types `"hello"` via `Browser.fill_in`.
2. Checkbox — `Query.css("#checkbox-example-1-id")` — checks it via `Browser.click`.
3. Radio button — `Query.css("#radio-example-1-option-1-id")` — checks it via `Browser.click`.
4. Select — `Query.css("#select-example-1-id option:first-child")` — selects the option at index 0 by clicking that `<option>` directly, then prints the select's value.

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
- [https://hex.pm/packages/wallaby](https://hex.pm/packages/wallaby)
