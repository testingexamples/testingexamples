# Spec

## Summary

This spec describes the exact browser-automation walkthrough that
`Program.cs` performs: launching Chrome with Playwright for C#, navigating
to the public testing-examples site, exercising five locator strategies,
and performing four form interactions, printing the result of each step
instead of asserting it.

## Scope

This spec covers the scenario implemented in `Program.cs`: the target URL,
every locator/selector it uses, and every form interaction it performs,
together with what "this demo still works" means.

This spec does NOT cover: how to install C#/Playwright or how to invoke the
program (see README.md), CI or build tooling, browsers other than Chrome,
or test-framework assertions — this program is a walkthrough, not a test
suite.

## Principles and rules

- This is a walkthrough program, not a test suite: it demonstrates locator
  strategies and form interactions by printing what it finds, and it does
  not assert expected outcomes with a test framework.
- The code and this spec describe the same scenario. If they ever diverge,
  that is a defect — fix it before making any other change.
- Playwright locators auto-wait and auto-retry; the demo contains no explicit waits.

## Detail

Target URL: `https://testingexamples.github.io/en-001/practice/`

Locator strategies demonstrated, in order:

1. By id — `page.Locator("#id-example-1")`
2. By name attribute — `page.Locator("[name='name-example-1']")`
3. By class name — `page.Locator(".class-example-1")`
4. By link text — `page.Locator("a", new() { HasTextString = "Link Example 1" })`
5. By XPath — `page.Locator("xpath=//input[@type='submit']")`

Each prints the located element's `outerHTML`.

Form interactions performed, in order:

1. Text input `#text-example-1-id` — filled with `"hello"` via `FillAsync`.
2. Checkbox `#checkbox-example-1-id` — checked via `CheckAsync`.
3. Radio `#radio-example-1-option-1-id` — checked via `CheckAsync`.
4. Select `#select-example-1-id` — option at index 0 chosen via `SelectOptionAsync(new SelectOptionValue { Index = 0 })`, then its value printed.

## Acceptance criteria

- The program launches Chrome and navigates to
  `https://testingexamples.github.io/en-001/practice/` without error.
- Each of the five locators above resolves to exactly one element on the
  live page (no timeout or "no such element" error).
- The text input accepts the value `"hello"`, the checkbox and radio
  button end up checked, and the select ends up with the option at index 0
  selected.
- The program exits with status code 0 and no unhandled error.

## Related topics

- [../README.md](../README.md)
- [../AGENTS.md](../AGENTS.md)

## Sources

- [https://testingexamples.github.io/en-001/practice/](https://testingexamples.github.io/en-001/practice/)
- [playwright-c-sharp-skill](https://github.com/testingexamples/playwright-c-sharp-skill)
