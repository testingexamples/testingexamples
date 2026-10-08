---
name: demo-playwright-kotlin-skill
description: Use when asked to run, explain, or extend the demo-playwright-kotlin locator-strategy walkthrough, or to build a similar Playwright demo in Kotlin against a different site.
---

# Demo Playwright Kotlin Skill

This repo teaches five Playwright locator strategies and four form
interactions against the public page https://testingexamples.github.io/en-001/practice/,
using `com.microsoft.playwright:playwright`. Kotlin runs on the JVM, so this demo uses the official Playwright for Java library (`com.microsoft.playwright:playwright`) directly from Kotlin.

Locator strategies: by id (`page.locator("#id-example-1")`), by name attribute (`page.locator("[name='name-example-1']")`), by class name (`page.locator(".class-example-1")`), by link text (`page.locator("a", Page.LocatorOptions().setHasText("Link Example 1"))`), by xpath (`page.locator("xpath=//input[@type='submit']")`).

Form interactions: text input (`#text-example-1-id`); checkbox (`#checkbox-example-1-id`); radio button (`#radio-example-1-option-1-id`); select (`#select-example-1-id`).

Each step prints the located element's outer HTML or value — this is a
walkthrough that demonstrates locators, not a test suite with assertions.

## Running it

1. Install a JDK and Maven per README.md.
2. Run `mvn compile exec:java`. The first run downloads the Playwright browsers. A visible (non-headless) Chromium window opens, walks the page, and closes.

## Adapting it to a different site or selectors

1. Update the target URL passed to `page.navigate(...)`.
2. Update each locator string to match the new page's real ids/classes/attributes/link text/XPath.
3. Update the values used to fill and click to match the new form's real inputs.
4. Update `spec/index.md` to describe the new scenario verbatim, in the same change — not after.

This skill summarizes the repo. `AGENTS.md` and `spec/index.md` are the
source of truth — if this skill's summary ever disagrees with those, they
win.
