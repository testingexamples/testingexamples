---
name: demo-playwright-go-skill
description: Use when asked to run, explain, or extend the demo-playwright-go locator-strategy walkthrough, or to build a similar Playwright demo in Go against a different site.
---

# Demo Playwright Go Skill

This repo teaches five Playwright locator strategies and four form
interactions against the public page https://testingexamples.github.io/en-001/practice/,
using `github.com/playwright-community/playwright-go`. There is no official Go binding from the Playwright project itself. [`playwright-community/playwright-go`](https://github.com/playwright-community/playwright-go) is the community-maintained Playwright client for Go, and is what this demo uses. It is pinned to `v0.5001.0` (Playwright 1.50) in [go.mod](go.mod), the latest tag whose driver download works at the time of writing.

Locator strategies: by id (`page.Locator("#id-example-1")`), by name attribute (`page.Locator("[name='name-example-1']")`), by class name (`page.Locator(".class-example-1")`), by link text (`page.Locator("a", playwright.PageLocatorOptions{HasText: "Link Example 1"})`), by xpath (`page.Locator("xpath=//input[@type='submit']")`).

Form interactions: text input (`#text-example-1-id`); checkbox (`#checkbox-example-1-id`); radio button (`#radio-example-1-option-1-id`); select (`#select-example-1-id`).

Each step prints the located element's outer HTML or value — this is a
walkthrough that demonstrates locators, not a test suite with assertions.

## Running it

1. Install Go per README.md, then install the Playwright browsers with the command in README.md.
2. Run `go run .`. A visible (non-headless) Chromium window opens, walks the page, and closes.

## Adapting it to a different site or selectors

1. Update the target URL passed to `page.Goto(...)`.
2. Update each locator string to match the new page's real ids/classes/attributes/link text/XPath.
3. Update the values used to fill and click to match the new form's real inputs.
4. Update `spec/index.md` to describe the new scenario verbatim, in the same change — not after.

This skill summarizes the repo. `AGENTS.md` and `spec/index.md` are the
source of truth — if this skill's summary ever disagrees with those, they
win.
