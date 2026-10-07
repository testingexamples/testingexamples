---
name: demo-playwright-java-skill
description: Use when asked to run, explain, or extend the demo-playwright-java locator-strategy walkthrough, or to build a similar Playwright demo in Java against a different site.
---

# Demo Playwright Java Skill

This repo teaches five Playwright locator strategies and four form
interactions against the public page https://testingexamples.github.io/en-001/practice/,
in Java.

Locator strategies, form interactions, and the exact calls used are listed
in `spec/index.md`; the code is `src/main/java/demo/Demo.java`. Keep them in agreement.

Run it with `mvn compile exec:java` (see README.md for installing dependencies).

For the general teaching material behind this demo — locating, acting,
waiting, and asserting, plus pitfalls — see
https://github.com/testingexamples/playwright-java-skill.
