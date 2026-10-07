# AGENTS.md

This repo is a small, beginner-friendly walkthrough that uses Playwright with
C# to drive a real browser against the real, live
[NHS Wales](https://www.nhs.wales/) website and run three real
assertion-based checks.

## Source of truth

`spec/index.md` is the single source of truth for the exact three assertions
and selectors this demo checks against the real nhs.wales site. The code in
`Program.cs` must match it exactly (expected titles, expected headline,
selector ids, search term, expected substrings). If the code and
`spec/index.md` ever disagree, that is a defect in one of them — fix it
before doing anything else.

## Install and run

See `README.md` for the Install and Run sections. Do not duplicate those
steps here; follow the README.

## Non-negotiable

This demo targets the real production nhs.wales site on purpose. Don't
change the target site, the three test scenarios, or the exact expected
strings without updating `spec/index.md` first. These repos exist
specifically to demonstrate real-world testing against a live NHS Wales
site — not against a mock or a fixture.

## Known current external blocker

As of 2026-09-02, nhs.wales's TLS certificate has been expired since
2025-08-18. Running this demo currently fails with a certificate error. That
failure is external to this repo and is not a code defect — do not "fix" it
by weakening assertions, disabling TLS verification, or pointing the demo at
a different site.

CLAUDE.md is a pointer to this file — it is the single source of truth for
agent instructions.
