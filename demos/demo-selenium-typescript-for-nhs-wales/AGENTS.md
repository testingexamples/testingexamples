# AGENTS.md

This repo is a small, beginner-friendly walkthrough (`src/demo.ts`) that uses
Selenium WebDriver in TypeScript to drive a real Chrome browser against the
live production site <https://www.nhs.wales/> and run three real,
assertion-based checks. It is a straight TypeScript port of the sibling
`demo-selenium-javascript-for-nhs-wales` repo — same scenario, selectors,
and expected strings, with explicit types added throughout (the same
porting convention `demo-selenium-typescript` uses for the generic,
non-NHS-Wales scenario).

## Source of truth

`spec/index.md` is the single source of truth for the exact three assertions
and selectors this demo checks against the real nhs.wales site. If
`src/demo.ts` and `spec/index.md` ever disagree, that is a defect in one of
them — fix it before doing anything else.

## Install and run

See `README.md` for Install and Run steps, including chromedriver
troubleshooting (macOS Gatekeeper quarantine blocks, and Chrome/chromedriver
version-mismatch errors). Do not duplicate those instructions here; follow
the README.

## Non-negotiable

This demo targets the real production nhs.wales site. Don't change the
target site, the three test scenarios, or the exact expected strings
(titles, link text, selector ids, search term, or result substrings) without
first updating `spec/index.md` to match.

## Verification note

This demo was actually installed and run against the live site on
2026-09-05 (twice, consecutively, both clean) — not just reasoned about.
That run surfaced two real timing failures (reading a page title
immediately after a `.click()`, and reading body text before a
second-pass render finished), which is why `src/demo.ts` has two explicit
`driver.wait(...)` calls that a naive port from the JavaScript sibling
would not have. Do not remove those waits as "unnecessary" without
re-running the demo for real first. If a future run of this demo fails,
check whether the live site's markup, copy, timing, or certificate has
changed before assuming the code is wrong.

---

CLAUDE.md is a pointer to this file — it is the single source of truth for
agent instructions.
