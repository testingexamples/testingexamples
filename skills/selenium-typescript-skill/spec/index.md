# Spec

## Summary

This repo publishes one Claude Code skill, [SKILL.md](../SKILL.md),
which teaches how to write, explain, debug, and extend Selenium
WebDriver browser automation code in TypeScript. This spec is the
contract for what that skill must accurately cover.

## Scope

- How to install and set up `selenium-webdriver` for TypeScript,
  including browser driver setup (Selenium Manager vs. manual
  `chromedriver`, and the macOS Gatekeeper caveat).
- The four core concepts of browser automation as Selenium implements
  them: locating (`By`/`findElement`), acting (`sendKeys`, `click`,
  `Select`), waiting (explicit waits via `driver.wait(until...)`, and
  the fact that Selenium has no automatic actionability waiting), and
  asserting (pairing `selenium-webdriver` with Node's `assert` module
  and a test framework such as Mocha).
- The contrast between a plain walkthrough script and a real,
  assertion-bearing test.
- Common pitfalls specific to Selenium + TypeScript.
- Links to this project's real sibling demo repos for Selenium +
  TypeScript, and nothing beyond the real family.

Out of scope: other browser automation tools (Playwright, Cypress,
WebdriverIO, Puppeteer, etc.) except where a one-line contrast (e.g.
auto-waiting) sharpens the explanation of Selenium's own behavior.

## Principles and rules

- Every code sample in SKILL.md must be real, valid, well-typed
  TypeScript using `selenium-webdriver`'s actual public API — no
  invented methods, options, or types.
- The full worked example must match the real, verified
  `demo-selenium-typescript` source (trimmed at most, never altered in
  behavior or selectors).
- SKILL.md must state plainly that Selenium does not auto-wait for
  element actionability, since this is the most consequential
  practical difference a reader coming from another tool needs to
  know.
- SKILL.md must link to exactly the four real sibling repos in this
  family (generic, Google Search, Google Maps, and NHS Wales) and must
  not imply a different count. The NHS Wales variant
  (`demo-selenium-typescript-for-nhs-wales`) was added on 2026-09-05,
  filling what was previously the one gap among all eight
  tool+language combos in this project — do not describe that gap as
  still existing.
- Any mention of the Google-Search or Google-Maps sibling repos must
  carry the Google Terms of Service caveat: those repos are
  illustrative only, not meant for repeated automated querying of the
  live sites.
- License, author, and citation metadata must match the facts recorded
  in README.md, LICENSE.md, and CITATION.cff exactly — no invented
  alternatives.

## Acceptance criteria

- Every code sample in SKILL.md is valid TypeScript using
  `selenium-webdriver`'s real API and types.
- Every linked sibling repo URL resolves.
- Exactly four sibling repos are linked, matching the real family
  (generic, Google Search, Google Maps, NHS Wales).
- The Google ToS caveat appears wherever the two Google-targeting
  sibling repos are mentioned.
- SKILL.md explicitly contrasts a walkthrough script with a real Mocha
  + `assert` test.
- SKILL.md explicitly states that Selenium does not auto-wait, and
  shows the `driver.wait(until...)` explicit-wait pattern.

## Related topics

- [README.md](../README.md)
- [AGENTS.md](../AGENTS.md)
- [SKILL.md](../SKILL.md)
