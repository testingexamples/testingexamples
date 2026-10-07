---
name: demo-playwright-typescript
description: Explains and adapts the demo-playwright-typescript Playwright walkthrough (locating elements by id/name/class/link-text/xpath, filling a text input, checking a checkbox/radio, selecting a dropdown option) against https://testingexamples.github.io/en-001/practice/; invoke when asked to run, explain, extend, or port this demo, or to adapt it to a different site or Playwright version.
---

This skill covers the `demo-playwright-typescript` repo: a small
TypeScript script (`src/demo.ts`) that teaches Playwright's locator
strategies (id, attribute, class, text, and XPath selectors via
`page.locator(...)`) and basic form interactions (filling a text input,
checking a checkbox and radio, selecting a dropdown option) against the
public demo site https://testingexamples.github.io/en-001/practice/. This repo is a
generic locator walkthrough, not NHS-Wales-specific.

To run it: install Node, npm, TypeScript/ts-node, and Playwright (see
README.md's Install section), then run `./src/demo.ts` or
`npx ts-node ./src/demo.ts`. It launches a visible local Chromium window,
prints the outer HTML of each located element, fills in the form, and
closes.

To adapt this demo to a different site: change the URL passed to
`page.goto(...)` and update every locator to match the new site's markup,
then update `spec/index.md` to match — the spec and the code must always
agree. To adapt to a newer Playwright version: update the `playwright`
dependency and re-check the `Locator`/`chromium` APIs used here for
breaking changes; the locator and interaction sequence itself maps to
Playwright's stable core API and is unlikely to need changes.

This skill summarizes the repo. `AGENTS.md` and `spec/index.md` are the
source of truth — if this skill's summary ever disagrees with those, they
win.
