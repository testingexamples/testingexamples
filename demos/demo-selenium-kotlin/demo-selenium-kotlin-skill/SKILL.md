---
name: demo-selenium-kotlin-skill
description: Use when asked to run, explain, or extend the demo-selenium-kotlin locator-strategy walkthrough, or to build a similar Selenium demo in Kotlin against a different site.
---

# Demo Selenium Kotlin Skill

This repo teaches five Selenium locator strategies and four form
interactions against the public page https://testingexamples.github.io/en-001/practice/,
using `selenium-java`. Kotlin runs on the JVM, so this demo uses the official Selenium Java bindings (`selenium-java`) directly from Kotlin.

Locator strategies: by id (`driver.findElement(By.id("id-example-1"))`), by name attribute (`driver.findElement(By.name("name-example-1"))`), by class name (`driver.findElement(By.className("class-example-1"))`), by link text (`driver.findElement(By.linkText("Link Example 1"))`), by xpath (`driver.findElement(By.xpath("//input[@type='submit']"))`).

Form interactions: text input (`By.id("text-example-1-id")`); checkbox (`By.id("checkbox-example-1-id")`); radio button (`By.id("radio-example-1-option-1-id")`); select (`By.id("select-example-1-id")`).

Each step prints the located element's outer HTML or value — this is a
walkthrough that demonstrates locators, not a test suite with assertions.

## Running it

1. Install a JDK and Maven per README.md.
2. Run `mvn compile exec:java`. A visible (non-headless) Chrome window opens, walks the page, and closes.

## Adapting it to a different site or selectors

1. Update the target URL passed to `driver.get(...)`.
2. Update each locator string to match the new page's real ids/classes/attributes/link text/XPath.
3. Update the values used to fill and click to match the new form's real inputs.
4. Update `spec/index.md` to describe the new scenario verbatim, in the same change — not after.

This skill summarizes the repo. `AGENTS.md` and `spec/index.md` are the
source of truth — if this skill's summary ever disagrees with those, they
win.
