---
name: demo-selenium-elixir-skill
description: Use when asked to run, explain, or extend the demo-selenium-elixir locator-strategy walkthrough, or to build a similar Selenium demo in Elixir against a different site.
---

# Demo Selenium Elixir Skill

This repo teaches five Selenium locator strategies and four form
interactions against the public page https://testingexamples.github.io/en-001/practice/,
using `wallaby`. There is no official Elixir binding from the Selenium project itself. [`wallaby`](https://hex.pm/packages/wallaby) is the best-known WebDriver client for Elixir: it starts `chromedriver` and drives Chrome through the WebDriver protocol, which is the same protocol Selenium speaks. It is what this demo uses.

Locator strategies: by id (`Browser.find(session, Query.css("#id-example-1"))`), by name attribute (`Browser.find(session, Query.css("[name='name-example-1']"))`), by class name (`Browser.find(session, Query.css(".class-example-1"))`), by link text (`Browser.find(session, Query.link("Link Example 1"))`), by xpath (`Browser.find(session, Query.xpath("//input[@type='submit']"))`).

Form interactions: text input (`Query.css("#text-example-1-id")`); checkbox (`Query.css("#checkbox-example-1-id")`); radio button (`Query.css("#radio-example-1-option-1-id")`); select (`Query.css("#select-example-1-id option:first-child")`).

Each step prints the located element's outer HTML or value — this is a
walkthrough that demonstrates locators, not a test suite with assertions.

## Running it

1. Install Elixir and `chromedriver` per README.md.
2. Run `mix deps.get`, then `mix demo`. A visible (non-headless) Chrome window opens, walks the page, and closes.

## Adapting it to a different site or selectors

1. Update the target URL passed to `Browser.visit(session, ...)`.
2. Update each locator string to match the new page's real ids/classes/attributes/link text/XPath.
3. Update the values used to fill and click to match the new form's real inputs.
4. Update `spec/index.md` to describe the new scenario verbatim, in the same change — not after.

This skill summarizes the repo. `AGENTS.md` and `spec/index.md` are the
source of truth — if this skill's summary ever disagrees with those, they
win.
