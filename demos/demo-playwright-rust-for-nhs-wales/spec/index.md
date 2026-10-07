# Spec

## Summary

This demo uses Playwright (via the `playwright-rs` crate) with Rust to
drive a real Chromium browser against the real, live
[NHS Wales](https://www.nhs.wales/) website and run three real
assertion-based checks, as a beginner-friendly example of end-to-end
browser testing.

## Scope

This spec covers `src/main.rs` only: the three test scenarios it runs, the
exact strings and selector ids it checks, and the criteria for the demo to
be considered passing. It does not cover installation or how to run the
program — see `README.md` for that.

## Principles and rules

* This demo runs real assertions against a real, live production website.
  There is no mocking, no stubbing, and no fixture server. That is the
  point of this demo: to show what real-world browser testing against a
  live site looks like.
* `src/main.rs` is the implementation. This file, `spec/index.md`, is the
  specification. They must agree exactly. If they ever disagree, that is a
  defect in one of them — fix it before doing anything else.
* This repo depends on the crate published on crates.io as `playwright-rs`
  (source: `padamson/playwright-rust`), not the older, unrelated, abandoned
  crate published simply as `playwright` (`octaltree/playwright-rust`).

## Detail

1. **Home page title test**
   * Navigate to: `https://www.nhs.wales/`
   * Assert the page title equals exactly: `Home - NHS Wales`

2. **About Us link test**
   * From the home page, click the link with exact accessible name: `About Us`
     (first matching link, exact match), found via
     `page.get_by_role(AriaRole::Menuitem, ...)` with `name("About Us")` and
     `exact(true)`
   * Wait for the resulting page to finish loading
   * Assert the resulting page title equals exactly: `About Us - NHS Wales`
   * Assert the resulting page's first `h1` element's trimmed inner text
     equals exactly: `About Us`

3. **Search box test**
   * Navigate to: `https://www.nhs.wales/`
   * Fill the element matching selector `#navKeywords` with the exact text:
     `help`
   * Click the element matching selector `#button-addon`
   * Wait for the resulting page to finish loading
   * Assert the resulting page's `body` inner text contains the exact
     substring: `Search Results`
   * Assert the resulting page's `body` inner text contains the exact
     substring: `Your search for "help"`

## Acceptance criteria

* All 3 checks above pass when run against the live `https://www.nhs.wales/`
  site.
* As of 2026-09-02, nhs.wales's TLS certificate has been expired since
  2025-08-18; until it's renewed, running this demo will fail with a
  certificate error unrelated to this code.

## Related topics

* [../README.md](../README.md)
* [../AGENTS.md](../AGENTS.md)

## Sources

* <https://www.nhs.wales/>
* <https://crates.io/crates/playwright-rs>
