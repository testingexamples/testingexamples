# Spec: Playwright Rust Skill

## Summary

This repo's one contract is `SKILL.md`: it must accurately describe how to
use Playwright in Rust via the `playwright-rs` crate, never the abandoned
`playwright` crate, and its code examples must be real, working syntax that
matches the sibling `demo-playwright-rust` repo.

## Scope

In scope:

- The content, accuracy, and structure of `SKILL.md`.
- Keeping links to sibling demo repos and reference docs correct.
- Supporting metadata files (`README.md`, `AGENTS.md`, `CLAUDE.md`,
  `LICENSE.md`, `CITATION.cff`, `llms.txt`, `llms.json`) that describe or
  point at the skill.

Out of scope:

- Being a runnable demo. This repo contains no Cargo project of its own;
  runnable examples live in the sibling `demo-playwright-rust*` repos.
- Documenting any tool other than Playwright via `playwright-rs` in Rust.

## Principles and rules

- Always name the crate as `playwright-rs` (GitHub: `padamson/playwright-rust`).
  Never recommend or silently accept the crates.io package literally named
  `playwright` (GitHub: `octaltree/playwright-rust`) — it has been abandoned
  since 2022, and `SKILL.md` must warn readers about this explicitly.
- Every Rust code sample in `SKILL.md` must be valid syntax against
  `playwright-rs`'s real, published API (`Playwright::launch()`,
  `pw.chromium().launch()`, `browser.new_page()`, `page.goto()`,
  `page.locator()`, `.text_content()`, `.fill()`, `.check()`,
  `.select_option()`, `.input_value()`) — do not invent methods or a
  different crate's API.
- The worked example must match the real, verified `src/main.rs` from
  `demo-playwright-rust` (or a lightly trimmed version of it) — do not
  substitute invented selectors or a different demo target.
- Teach the four core concepts (locating, acting, waiting, asserting) as
  they apply specifically to `playwright-rs`'s async locator API, including
  Playwright's auto-waiting/auto-retry behavior as its headline advantage
  over Selenium/thirtyfour-style tools.
- Contrast a `println!`-based walkthrough with a real `#[tokio::test]` +
  `assert_eq!` test, since there is no `@playwright/test`-equivalent test
  runner for the Rust binding.
- Every sibling-repo link must stay correct and live under the
  `testingexamples` GitHub org. Any mention of the Google Search or Google
  Maps sibling repos must carry the caveat that Google's Terms of Service
  restrict automated querying, and that those two repos are illustrative
  only.
- Use the author, license, and organization facts exactly as given
  elsewhere in this repo's files — do not invent alternatives.

## Acceptance criteria

- Every code sample in `SKILL.md` is valid Rust using `playwright-rs`'s real
  API.
- The crate-naming warning (`playwright-rs` vs. the abandoned `playwright`)
  is present and correct in `SKILL.md`.
- Every linked sibling repo URL resolves:
  - https://github.com/testingexamples/demo-playwright-rust
  - https://github.com/testingexamples/demo-playwright-rust-for-google-search
  - https://github.com/testingexamples/demo-playwright-rust-for-google-maps
  - https://github.com/testingexamples/demo-playwright-rust-for-nhs-wales
- `SKILL.md` contains the "From walkthrough to real test" contrast using
  `#[tokio::test]` and `assert_eq!`.
- `SKILL.md` contains a "common pitfalls" list covering: the crate-naming
  trap, forgetting `#[tokio::main]`/`#[tokio::test]`, not closing the
  browser on error, and version instability on a pre-1.0 crate.

## Related topics

- [README.md](../README.md)
- [AGENTS.md](../AGENTS.md)
- [SKILL.md](../SKILL.md)
