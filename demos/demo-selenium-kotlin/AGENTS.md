# AGENTS.md

This repo is a small Selenium walkthrough demo, written in Kotlin using
`selenium-java`, that launches Chrome, navigates to
https://testingexamples.github.io/en-001/practice/, and demonstrates five ways to locate
elements plus four form interactions, logging what it finds at each step.

`spec/index.md` is the single source of truth for the exact scenario this
demo walks through: the target URL, every selector/locator used, and the
expected values. If the code in `src/main/kotlin/demo/Main.kt` and `spec/index.md` ever
disagree, that is a defect in one of them — fix it before doing anything
else.

For how to install dependencies and run the program, see README.md's
Install and Run sections; this file does not duplicate them.

Non-negotiable: do not add new locator strategies or form interactions to
`src/main/kotlin/demo/Main.kt` without updating `spec/index.md` first, in the same change.

Non-negotiable: `selenium-java` is the library this repo depends on. Kotlin runs on the JVM, so this demo uses the official Selenium Java bindings (`selenium-java`) directly from Kotlin. Do not switch `pom.xml` to a different library without updating README.md and `spec/index.md` first.

This is a generic, unrestricted target (`testingexamples.github.io`, a
fixture page built for this purpose): unlike the `-for-google-search` and
`-for-google-maps` sibling repos, there is no Terms-of-Service reason to
avoid running this demo.

CLAUDE.md is a pointer to this file — it is the single source of truth for
agent instructions.
