# AGENTS.md

This repo is a small, beginner-friendly walkthrough (`src/demo.py`) that uses
Selenium WebDriver in Python to drive a real Chrome browser against the live
production site <https://www.nhs.wales/> and run three real,
assertion-based checks.

## Source of truth

`spec/index.md` is the single source of truth for the exact three assertions
and selectors this demo checks against the real nhs.wales site. If
`src/demo.py` and `spec/index.md` ever disagree, that is a defect in one of
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

## Known blocker

As of the current date, nhs.wales's TLS certificate has been expired since
2025-08-18. Running this demo against the live site will currently fail with
a certificate/driver error that is unrelated to this code. Do not treat that
failure as a code defect, and do not attempt to "fix" it here — it will
resolve when the site operator renews the certificate.

---

CLAUDE.md is a pointer to this file — it is the single source of truth for
agent instructions.
