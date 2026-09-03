---
name: demo-selenium-rust-for-nhs-wales-skill
description: Explains and helps run the Selenium + Rust demo that tests the real, live NHS Wales website; invoke when someone wants to run this demo, understand what it checks, or adapt its real-world assertion-testing pattern to a different live site.
---

# Demo Selenium Rust for NHS Wales — skill

## What this demo teaches

This repo is a beginner-friendly example of **real-world, assertion-based
browser testing against a live, production website** (no mocks, no
fixtures). It uses Selenium, via the `thirtyfour` crate — Rust's de facto
Selenium/WebDriver client (there is no official Selenium Rust binding) —
to drive a real Chrome browser to <https://www.nhs.wales/> and checks
three things a visitor might actually do:

1. The home page title is exactly `Home - NHS Wales`.
2. Clicking the "About Us" link leads to a page titled exactly
   `About Us - NHS Wales` with an `h1` reading exactly `About Us`.
3. Typing `help` into the search box (`#navKeywords`) and clicking the
   search button (`#button-addon`) leads to a results page whose body
   contains `Search Results` and the exact phrase `Your search for "help"`.

## How to run it

```sh
chromedriver --port=9515 &
cargo build
cargo run
```

See `../README.md` for prerequisites and version checks.

## Known blocker

As of 2026-09-02, nhs.wales's TLS certificate has been expired since
2025-08-18, so running this demo currently fails with a certificate error
external to this repo. See `AGENTS.md` and `spec/index.md`.

## Adapting the pattern to a different real site

1. Pick a real target site you're allowed to test against.
2. Pick 2-4 small, real user actions (visit a page, click a link, use a
   search box) and write down the exact expected titles/text/selectors
   *before* writing code — that becomes your spec.
3. Copy the structure of `src/main.rs`: connect to the WebDriver server,
   navigate, assert, print a checkmark, move to the next step, quit even
   on error.
4. Keep every expected string exact and verbatim between your spec and your
   code, the same way this repo keeps `spec/index.md` and `src/main.rs` in
   agreement.

This skill summarizes the repo. `AGENTS.md` and `spec/index.md` are the
source of truth — if this skill's summary ever disagrees with those, they
win.
