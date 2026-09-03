---
name: demo-playwright-python-for-google-search-skill
description: Explains the Playwright + Python demo that describes testing Google Search's well-known, stable accessible markup; invoke when someone wants to understand what it checks, or adapt its pattern to a different site, without running it against live google.com.
---

# Demo Playwright Python for Google Search — skill

## What this demo teaches

This repo is a beginner-friendly example of **assertion-based browser
testing against real-world, third-party markup that the test author does
not control**, using Google Search as the example. It uses Playwright with
Python to describe driving a real Chromium browser to
<https://www.google.com> and checking three things a searcher might
actually do:

1. The home page title equals exactly `Google`.
2. Typing `testing examples` into the search box (`textarea[name="q"]`) and
   pressing Enter leads to a results page whose title contains `testing
   examples`.
3. Clicking the first organic result link (`#search a`) navigates away from
   `google.com`.

## CAUTION: do not run this against live google.com

Google's Terms of Service restrict automated querying of Google Search.
This repo's code must never be executed against the live site in CI or any
automated tooling — see `AGENTS.md` for the non-negotiable. Read
`README.md`'s caution before running anything manually.

## Adapting the pattern to a different real site

1. Pick a real target site whose Terms of Service permit automated testing,
   or use a fixture/mock/site you control (such as
   [testingexamples.github.io](https://testingexamples.github.io)).
2. Pick 2-4 small, real user actions (visit a page, use a search box, click
   a result) and write down the exact expected titles/text/selectors
   *before* writing code — that becomes your spec.
3. Copy the structure of `src/demo.py`: launch a browser, navigate, assert,
   print a checkmark, move to the next step, close the browser in a
   `finally` block.
4. Keep every expected string exact and verbatim between your spec and your
   code, the same way this repo keeps `spec/index.md` and `src/demo.py` in
   agreement.

This skill summarizes the repo. `AGENTS.md` and `spec/index.md` are the
source of truth — if this skill's summary ever disagrees with those, they
win.
