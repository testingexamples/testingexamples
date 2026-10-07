# Spec

## Summary

This repo's one contract: [SKILL.md](../SKILL.md) must accurately describe
how to use Playwright in JavaScript. Its code examples must be real,
working syntax, matching the sibling
[demo-playwright-javascript](https://github.com/joelparkerhenderson/demo-playwright-javascript)
repo. Its sibling-repo links must stay correct.

## Scope

In scope:

- Explaining Playwright's core concepts (locating, acting, waiting,
  asserting) as they apply to the JavaScript `playwright` package and to
  the `@playwright/test` test runner.
- A worked walkthrough example using `page.locator(...)` and real
  Playwright locator syntax.
- A contrast between a plain walkthrough script and a real
  `@playwright/test` test using `expect(...)` web-first assertions.
- Common pitfalls specific to Playwright's JavaScript API.
- Links to real, verified sibling demo repos and official Playwright docs.

Out of scope:

- Playwright in other languages (Python, Java, .NET, etc.).
- Other browser automation tools (Selenium, Cypress, Puppeteer) except as
  brief points of contrast.
- A runnable test project of its own — this repo is a skill/reference, not
  a demo.

## Principles and rules

- Every code sample in SKILL.md must be valid JavaScript using Playwright's
  real, current API (no invented methods or selectors).
- The worked example must match the real `src/demo.js` from
  demo-playwright-javascript, not a rewritten approximation.
- Any mention of Google Search or Google Maps automation must carry the
  caveat that Google's Terms of Service restrict automated querying, and
  that the corresponding sibling repos are illustrative only.
- Sibling-repo and documentation links must point to real, resolvable
  URLs.
- If SKILL.md and this spec ever disagree, this spec wins; SKILL.md should
  be corrected to match.

## Acceptance criteria

- Every code sample in SKILL.md is valid JavaScript using Playwright's
  real API.
- Every linked sibling repo URL resolves.
- The walkthrough-vs-real-test contrast is present and uses
  `@playwright/test`'s `test()` and `expect(...)` (e.g. `toHaveText`).
- The Google Search / Google Maps Terms of Service caveat appears wherever
  those two sibling repos are mentioned.
- The four core concepts (locating, acting, waiting, asserting) are each
  explicitly covered.

## Related topics

- [README.md](../README.md)
- [AGENTS.md](../AGENTS.md)
- [SKILL.md](../SKILL.md)
