# AGENTS.md

This repo is a small Selenium walkthrough demo, written in Go using
`github.com/tebeka/selenium`, that launches Chrome that connects to a WebDriver server (e.g. `chromedriver`), navigates to
https://testingexamples.github.io/en-001/practice/, and demonstrates five ways to locate
elements plus four form interactions, logging what it finds at each step.

`spec/index.md` is the single source of truth for the exact scenario this
demo walks through: the target URL, every selector/locator used, and the
expected values. If the code in `main.go` and `spec/index.md` ever
disagree, that is a defect in one of them — fix it before doing anything
else.

For how to install dependencies and run the program, see README.md's
Install and Run sections; this file does not duplicate them.

Non-negotiable: do not add new locator strategies or form interactions to
`main.go` without updating `spec/index.md` first, in the same change.

Non-negotiable: `github.com/tebeka/selenium` is the library this repo depends on. There is no official Go binding from the Selenium project itself. [`tebeka/selenium`](https://pkg.go.dev/github.com/tebeka/selenium) is the best-known Selenium/WebDriver client for Go, and is what this demo uses. Do not switch `go.mod` to a different library without updating README.md and `spec/index.md` first.

This is a generic, unrestricted target (`testingexamples.github.io`, a
fixture page built for this purpose): unlike the `-for-google-search` and
`-for-google-maps` sibling repos, there is no Terms-of-Service reason to
avoid running this demo.

CLAUDE.md is a pointer to this file — it is the single source of truth for
agent instructions.
