# Spec

## Summary

This repo's contract is narrow: [SKILL.md](../SKILL.md) must accurately describe how to write Playwright for Java browser automation code in Java, so that Claude Code can load it and produce correct, idiomatic guidance.

## Scope

In scope:

- Install and setup (package/dependency coordinates, driver or browser installation).
- Locating elements, acting on them, waiting, and asserting.
- Playwright auto-waits on actionability; web-first assertions retry.
- Contrasting a plain walkthrough with a real test: JUnit 5 with PlaywrightAssertions.assertThat.
- Common pitfalls specific to Playwright for Java + Java.
- Official documentation links, and a link to the sibling [demo-playwright-java](https://github.com/testingexamples/demo-playwright-java) repo.

Out of scope:

- Other language bindings of Playwright for Java.
- General Java language teaching unrelated to Playwright for Java.
- A runnable project — this repo is teaching material, not a demo. The runnable walkthrough is the sibling repo [demo-playwright-java](https://github.com/testingexamples/demo-playwright-java).
- Automating Google Search or Google Maps for real, repeated use; the Terms-of-Service caveat is stated in SKILL.md.

## Principles and rules

- Every code sample must be real, valid Java using Playwright for Java's actual API — no invented methods, types, or selectors.
- Fixture selectors and text must match the home page contract in [spec/index.md of testingexamples.github.io](https://github.com/testingexamples/testingexamples.github.io/blob/main/spec/index.md).
- Every external link must be a real, verified URL.
- Keep author, license, and tracking metadata consistent across README.md, LICENSE.md, CITATION.cff, llms.txt, and llms.json.

## Acceptance criteria

- Every code sample in SKILL.md compiles against current Playwright for Java releases (verify when a toolchain is available).
- SKILL.md covers locate, act, wait, assert, with the waiting model (Playwright auto-waits on actionability; web-first assertions retry.) stated correctly.
- SKILL.md frontmatter (`name`, `description`) matches what Claude Code expects for a skill definition.

## Related topics

- [README.md](../README.md)
- [AGENTS.md](../AGENTS.md)
- [SKILL.md](../SKILL.md)
