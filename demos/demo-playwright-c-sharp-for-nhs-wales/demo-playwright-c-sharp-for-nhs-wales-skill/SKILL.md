---
name: demo-playwright-c-sharp-for-nhs-wales-skill
description: Explains and helps run the Playwright + C# demo that tests the real, live NHS Wales website; invoke when someone wants to run this demo, understand what it checks, or adapt its real-world assertion-testing pattern to a different live site.
---

# Demo Playwright C# for NHS Wales — skill

## What this demo teaches

This repo is a beginner-friendly example of **real-world, assertion-based
browser testing against a live, production website** (no mocks, no
fixtures). It uses Playwright with C# to drive a real Chromium browser
to <https://www.nhs.wales/> and checks three things a visitor might
actually do:

1. The home page title is exactly `Home - NHS Wales`.
2. Clicking the "About Us" link leads to a page titled exactly
   `About Us - NHS Wales` with an `h1` reading exactly `About Us`.
3. Typing `help` into the search box (`#navKeywords`) and clicking the
   search button (`#button-addon`) leads to a results page whose body
   contains `Search Results` and the exact phrase `Your search for "help"`.

## How to run it

```sh
dotnet build
pwsh bin/Debug/net10.0/playwright.ps1 install chromium
dotnet run
```

See `../README.md` for prerequisites and version checks.

## Adapting the pattern to a different real site

1. Pick a real target site you're allowed to test against.
2. Pick 2-4 small, real user actions (visit a page, click a link, use a
   search box) and write down the exact expected titles/text/selectors
   *before* writing code — that becomes your spec.
3. Copy the structure of `Program.cs`: launch a browser, navigate, assert,
   print a checkmark, move to the next step, close the browser in a
   `finally` block.
4. Keep every expected string exact and verbatim between your spec and your
   code, the same way this repo keeps `spec/index.md` and `Program.cs` in
   agreement.

This skill summarizes the repo. `AGENTS.md` and `spec/index.md` are the
source of truth — if this skill's summary ever disagrees with those, they
win.
