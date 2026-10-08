---
name: demo-playwright-elixir-skill
description: Use when asked to run, explain, or extend the demo-playwright-elixir locator-strategy walkthrough, or to build a similar Playwright demo in Elixir against a different site.
---

# Demo Playwright Elixir Skill

This repo teaches five Playwright locator strategies and four form
interactions against the public page https://testingexamples.github.io/en-001/practice/,
using `playwright`. There is no official Elixir binding from the Playwright project itself. [`playwright`](https://hex.pm/packages/playwright) (`mechanical-orchard/playwright-elixir`) is the community Playwright client for Elixir, and is what this demo uses. It is in alpha, and its API is not yet at parity with Playwright for other languages.

Locator strategies: by id (`Page.locator(page, "#id-example-1")`), by name attribute (`Page.locator(page, "[name='name-example-1']")`), by class name (`Page.locator(page, ".class-example-1")`), by link text (`Page.locator(page, "a:has-text('Link Example 1')")`), by xpath (`Page.locator(page, "xpath=//input[@type='submit']")`).

Form interactions: text input (`#text-example-1-id`); checkbox (`#checkbox-example-1-id`); radio button (`#radio-example-1-option-1-id`); select (`#select-example-1-id`).

Each step prints the located element's outer HTML or value — this is a
walkthrough that demonstrates locators, not a test suite with assertions.

## Running it

1. Install Elixir per README.md.
2. Run `mix deps.get`, `mix playwright.install`, then `mix demo`. A visible (non-headless) Chromium window opens, walks the page, and closes.

## Adapting it to a different site or selectors

1. Update the target URL passed to `Page.goto(page, ...)`.
2. Update each locator string to match the new page's real ids/classes/attributes/link text/XPath.
3. Update the values used to fill and click to match the new form's real inputs.
4. Update `spec/index.md` to describe the new scenario verbatim, in the same change — not after.

This skill summarizes the repo. `AGENTS.md` and `spec/index.md` are the
source of truth — if this skill's summary ever disagrees with those, they
win.
