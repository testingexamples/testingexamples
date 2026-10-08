# AGENTS.md

This repo is a small Playwright walkthrough demo, written in Go using
`github.com/playwright-community/playwright-go`, that launches Chromium, navigates to
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

Non-negotiable: `github.com/playwright-community/playwright-go` is the library this repo depends on. There is no official Go binding from the Playwright project itself. [`playwright-community/playwright-go`](https://github.com/playwright-community/playwright-go) is the community-maintained Playwright client for Go, and is what this demo uses. It is pinned to `v0.5001.0` (Playwright 1.50) in [go.mod](go.mod), the latest tag whose driver download works at the time of writing. Do not switch `go.mod` to a different library without updating README.md and `spec/index.md` first.

This is a generic, unrestricted target (`testingexamples.github.io`, a
fixture page built for this purpose): unlike the `-for-google-search` and
`-for-google-maps` sibling repos, there is no Terms-of-Service reason to
avoid running this demo.

CLAUDE.md is a pointer to this file — it is the single source of truth for
agent instructions.
