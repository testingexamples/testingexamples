# AGENTS.md

This repo is a small Selenium walkthrough demo, written in Elixir using
`wallaby`, that launches Chrome, navigates to
https://testingexamples.github.io/en-001/practice/, and demonstrates five ways to locate
elements plus four form interactions, logging what it finds at each step.

`spec/index.md` is the single source of truth for the exact scenario this
demo walks through: the target URL, every selector/locator used, and the
expected values. If the code in `lib/demo.ex` and `spec/index.md` ever
disagree, that is a defect in one of them — fix it before doing anything
else.

For how to install dependencies and run the program, see README.md's
Install and Run sections; this file does not duplicate them.

Non-negotiable: do not add new locator strategies or form interactions to
`lib/demo.ex` without updating `spec/index.md` first, in the same change.

Non-negotiable: `wallaby` is the library this repo depends on. There is no official Elixir binding from the Selenium project itself. [`wallaby`](https://hex.pm/packages/wallaby) is the best-known WebDriver client for Elixir: it starts `chromedriver` and drives Chrome through the WebDriver protocol, which is the same protocol Selenium speaks. It is what this demo uses. Do not switch `mix.exs` to a different library without updating README.md and `spec/index.md` first.

This is a generic, unrestricted target (`testingexamples.github.io`, a
fixture page built for this purpose): unlike the `-for-google-search` and
`-for-google-maps` sibling repos, there is no Terms-of-Service reason to
avoid running this demo.

CLAUDE.md is a pointer to this file — it is the single source of truth for
agent instructions.
