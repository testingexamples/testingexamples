# AGENTS.md

This repo is a small, beginner-friendly walkthrough that uses Playwright
with Java to describe driving a real browser against the real, live
[Google Search](https://www.google.com) website and running three real
assertion-based checks.

## Source of truth

`spec/index.md` is the single source of truth for the exact three assertions
and selectors this demo checks against Google Search. The code in
`src/main/java/demo/Demo.java` must match it exactly (expected title, search box selector,
search term, expected substrings). If the code and `spec/index.md` ever
disagree, that is a defect in one of them — fix it before doing anything
else.

## Install and run

See `README.md` for the Install and Run sections. Do not duplicate those
steps here; follow the README.

## Non-negotiable: never run this against live google.com in automation

Google's Terms of Service restrict automated querying of Google Search. This
repo is deliberately different from this workspace's other
`demo-playwright-java-for-*` siblings (such as
`demo-playwright-java-for-nhs-wales`), which ARE meant to run against
their live target site. This repo's `src/main/java/demo/Demo.java` must **never** be executed
against the live `google.com` in CI, in a pre-commit hook, in a scheduled
job, or by any other automated tooling. It exists to show correct,
syntactically valid Playwright syntax and interaction patterns, written from
knowledge of Google's well-known, stable accessible markup — not to be
exercised live. A human may choose to run it manually, occasionally, fully
aware of the caution in README.md; that is different from automated
execution, which is prohibited here.

## Known drift

Google's search input has historically been a plain `<input name="q">` and
has since become an auto-growing `<textarea name="q">`. Selectors and page
structure in `src/main/java/demo/Demo.java` reflect Google's markup as of this writing and
may drift; do not "fix" a mismatch you notice by weakening assertions —
update `spec/index.md` and the code together, and note the change here.

CLAUDE.md is a pointer to this file — it is the single source of truth for
agent instructions.
