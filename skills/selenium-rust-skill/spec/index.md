# Spec

## Summary

This repo's one contract is [SKILL.md](../SKILL.md): a Claude Code skill that
accurately teaches how to write Selenium-style WebDriver browser automation
in Rust using the `thirtyfour` crate.

## Scope

- SKILL.md must accurately describe how to use the `thirtyfour` crate as the
  Selenium-equivalent for Rust — it must never imply that an official
  `selenium` crate exists for this purpose.
- Every Rust code sample in SKILL.md must be real, working syntax that
  matches `thirtyfour`'s actual API, grounded in the sibling
  [`demo-selenium-rust`](https://github.com/testingexamples/demo-selenium-rust)
  repo. Examples must not invent selectors, method names, or crate APIs that
  don't exist.
- SKILL.md must cover the four core concepts — locate, act, wait, assert —
  specifically for `thirtyfour`, including that it has no built-in
  auto-waiting and that `By::Name` and a reliable cross-version `Select`
  helper do not exist.
- SKILL.md must contrast a plain walkthrough with a real `#[tokio::test]` +
  `assert_eq!`/`assert!` test.
- SKILL.md must link to the sibling demo repos and note the Google
  Terms-of-Service caveat wherever the Google Search / Google Maps demos are
  mentioned.
- Out of scope: this repo does not itself contain a runnable Rust demo or
  Cargo project — that's what the sibling `demo-selenium-rust*` repos are for.

## Principles and rules

- Accuracy over completeness: never invent a crate, method, or locator that
  doesn't exist, even if it would make an example cleaner.
- The naming distinction (no official Selenium crate; `thirtyfour` is the
  WebDriver-protocol equivalent) must be stated clearly and early — this is
  the single most important fact in the skill.
- Every sibling-repo link must point to a real, existing repo under the
  `testingexamples` GitHub org.
- Keep author, license, and tracking metadata consistent across README.md,
  LICENSE.md, CITATION.cff, llms.txt, and llms.json.

## Acceptance criteria

- Every code sample in SKILL.md is valid Rust using `thirtyfour`'s real API.
- The no-official-Selenium-crate clarification is present and correct near
  the top of SKILL.md.
- Every linked sibling repo URL resolves:
  - https://github.com/testingexamples/demo-selenium-rust
  - https://github.com/testingexamples/demo-selenium-rust-for-google-search
  - https://github.com/testingexamples/demo-selenium-rust-for-google-maps
  - https://github.com/testingexamples/demo-selenium-rust-for-nhs-wales
- The Google Terms-of-Service caveat appears wherever the Google Search /
  Google Maps sibling repos are mentioned.
- SKILL.md's frontmatter (`name`, `description`) matches what Claude Code
  expects for a skill definition.

## Related topics

- [README.md](../README.md)
- [AGENTS.md](../AGENTS.md)
- [SKILL.md](../SKILL.md)
