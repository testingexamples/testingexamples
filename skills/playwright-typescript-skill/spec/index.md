# Spec

## Summary

This repo's contract is narrow: [SKILL.md](../SKILL.md) must accurately describe how to write Playwright browser automation code in TypeScript, so that Claude Code can load it and produce correct, idiomatic guidance.

## Scope

In scope:

- Locating elements with Playwright's `Locator` API.
- Performing actions (`.click()`, `.fill()`, `.check()`, `.selectOption()`, etc.).
- Waiting: Playwright's auto-waiting and auto-retry behavior on actionability.
- Asserting: contrasting a plain walkthrough script against a real `@playwright/test` test using web-first assertions (`expect(locator).toHaveText(...)` and similar).
- Common pitfalls specific to TypeScript + Playwright (missing `await`, execution-path confusion between `tsc`/`ts-node`/the test runner, strict-mode locator violations, over-typing).
- Links to real sibling demo repos under the `testingexamples` GitHub org, and to official Playwright docs.

Out of scope:

- Non-TypeScript Playwright usage (Python, Java, .NET bindings).
- General TypeScript language teaching unrelated to Playwright.
- Automating Google Search or Google Maps for real, repeated use — those sibling repos are cited as illustrative-only, with their Terms-of-Service caveat stated explicitly.

## Principles and rules

- Every code sample must be real, valid, well-typed TypeScript using Playwright's actual API — no invented methods, types, or selectors.
- The worked example must match (or be a lightly trimmed version of) the real `src/demo.ts` from https://github.com/testingexamples/demo-playwright-typescript, not a rewritten approximation.
- Wherever Google Search or Google Maps automation is mentioned, the Terms-of-Service caveat (illustrative only, not for repeated automated querying) must be stated.
- Sibling-repo and documentation links must be real, verified URLs.

## Acceptance criteria

- Every code sample in SKILL.md is valid TypeScript using Playwright's real API and types.
- Every linked sibling repo URL resolves.
- SKILL.md explicitly contrasts a console.log walkthrough with a real `@playwright/test` test using `expect(...)` web-first assertions.
- SKILL.md states the Google Terms-of-Service caveat wherever Google Search or Google Maps are mentioned.
- SKILL.md's frontmatter (`name`, `description`) matches what Claude Code expects for a skill.

## Related topics

- [README.md](../README.md)
- [AGENTS.md](../AGENTS.md)
- [SKILL.md](../SKILL.md)
