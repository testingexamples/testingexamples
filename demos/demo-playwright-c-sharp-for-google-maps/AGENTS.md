# AGENTS.md

This repo is a small, beginner-friendly walkthrough that uses Playwright
with C# to describe driving a real browser against the real, live
[Google Maps](https://www.google.com/maps) website and running three real
assertion-based checks.

## Source of truth

`spec/index.md` is the single source of truth for the exact three assertions
and selectors this demo checks against Google Maps. The code in
`Program.cs` must match it exactly (expected title substring, search box
selector, search term, zoom button selector, URL zoom-parameter format). If
the code and `spec/index.md` ever disagree, that is a defect in one of them
— fix it before doing anything else.

## Install and run

See `README.md` for the Install and Run sections. Do not duplicate those
steps here; follow the README.

## Non-negotiable: never run this against live google.com in automation

Google's Terms of Service restrict automated querying of its services,
including Google Maps. This repo is deliberately different from this
workspace's other `demo-playwright-c-sharp-for-*` siblings (such as
`demo-playwright-c-sharp-for-nhs-wales`), which ARE meant to run against
their live target site. This repo's `Program.cs` must **never** be executed
against the live `google.com` in CI, in a pre-commit hook, in a scheduled
job, or by any other automated tooling. It exists to show correct,
syntactically valid Playwright syntax and interaction patterns, written from
knowledge of Google's well-known, stable accessible markup — not to be
exercised live. A human may choose to run it manually, occasionally, fully
aware of the caution in README.md; that is different from automated
execution, which is prohibited here.

## Known constraint: canvas rendering

Most of Google Maps renders to `<canvas>`/WebGL, so this demo cannot assert
on any pin, label, or street drawn on the map itself. Assertions are
deliberately limited to the page title, the URL, and the URL's embedded
`@lat,lng,zoomz` segment. Do not "improve" this demo by adding assertions
that depend on reading canvas pixel content or drawn map features; that is
out of scope by design.

CLAUDE.md is a pointer to this file — it is the single source of truth for
agent instructions.
