---
name: demo-selenium-rust-for-google-search-skill
description: Explains the Selenium + Rust (thirtyfour) test-pattern demo against Google Search; invoke when someone wants to understand, review, or adapt these tests — never to run them against the live google.com.
---

# Demo Selenium Rust for Google Search — skill

## What this demo teaches

This repo demonstrates `thirtyfour` (Rust's de facto Selenium/WebDriver
client — there is no official Selenium Rust binding) syntax and
interaction patterns against Google Search, matching the sibling
`demo-selenium-javascript-for-google-search` and
`demo-selenium-python-for-google-search` repos test-for-test:

1. Home page title is exactly `Google`.
2. Searching `textarea[name="q"]` for a query and sending the WebDriver
   Enter key (`\u{E007}`) leads to a results page whose title contains
   that query.
3. Clicking the first organic result (`#search a`) navigates to a
   different hostname than `www.google.com`.

## The one rule that matters

**Never run `cargo build`, `cargo check`, or `cargo test` in this repo**,
and never let anything else do so either. Google's Terms of Service
restrict automated querying of Google Search. Review `src/demo.rs` by
reading it against `thirtyfour`'s documented API (see `spec/index.md`'s
Sources) — not by compiling it.

## Adapting the pattern to a site you can actually test

1. Copy `src/demo.rs`'s three-test structure.
2. Point `driver.goto(...)` at a site you're allowed to test — for
   hands-on practice, use <https://testingexamples.github.io/en-001/practice/> (see the
   sibling `demo-selenium-rust` repo).
3. Update every selector and assertion, and update `spec/index.md` in the
   same change.
4. Only then run `cargo build`/`cargo test` (with a WebDriver server such
   as `chromedriver` already running).

This skill summarizes the repo. `AGENTS.md` and `spec/index.md` are the
source of truth — if this skill's summary ever disagrees with those, they
win.
