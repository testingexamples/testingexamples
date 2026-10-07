# Spec: demo-playwright-typescript

## Summary

This spec describes the exact Playwright/TypeScript walkthrough that
`src/demo.ts` performs against https://testingexamples.github.io/en-001/practice/.

## Scope

This spec covers the demo script's target site, every locator it uses, and
every form interaction it performs, so that the code and this document can
be checked against each other.

This spec does not cover installing Node, TypeScript, ts-node, or
Playwright, or general Playwright API docs — see `README.md` for that.

## Principles and rules

* This is a walkthrough script, not a test suite. It demonstrates locator
  strategies and form interactions by printing HTML with `console.log`; it
  does not make assertions.
* The script targets exactly one page: https://testingexamples.github.io/en-001/practice/.

## Detail

Target URL: `https://testingexamples.github.io/en-001/practice/`

Locators (verbatim from `src/demo.ts`):

1. `page.locator('#id-example-1')` — finds
   `<p id="id-example-1">Lorem Ipsum</p>`, demonstrates an id selector.
2. `page.locator('[name="name-example-1"]')` — finds
   `<p name="name-example-1">Lorem Ipsum</p>`, demonstrates an attribute
   selector.
3. `page.locator('.class-example-1')` — finds
   `<p class="class-example-1">Lorem Ipsum</p>`, demonstrates a class
   selector.
4. `page.locator('a', { hasText: 'Link Example 1' })` — finds
   `<a href="https://example.com">Link Example 1</a>`, demonstrates a
   text-based locator.
5. `page.locator('xpath=//input[@type="submit"]')` — finds
   `<input type=submit>`, demonstrates an XPath selector.

Form interactions (verbatim from `src/demo.ts`):

* Text input: `page.locator('#text-example-1-id')`, then
  `.fill("hello")`.
* Checkbox: `page.locator('#checkbox-example-1-id')`, then `.check()`.
* Radio button: `page.locator('#radio-example-1-option-1-id')`, then
  `.check()`.
* Select: `page.locator('#select-example-1-id')`, then
  `.selectOption({ index: 0 })`, then reads `.inputValue()` and the
  `option:checked` element's outer HTML.

## Acceptance criteria

This demo still works when: the script completes without throwing, and
every locator resolves — each `.evaluate(...)` call succeeds and every
`console.log` prints real outer HTML rather than an error.

## Related topics

* [../README.md](../README.md)
* [../AGENTS.md](../AGENTS.md)

## Sources

* <https://testingexamples.github.io/en-001/practice/>
