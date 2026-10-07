# Spec

## Summary

This repo's one contract is [SKILL.md](../SKILL.md): it must accurately
describe how to write, explain, debug, and extend Playwright browser
automation code in Python.

## Scope

- SKILL.md covers locating elements, performing actions, waiting
  (Playwright's auto-waiting/auto-retry model), and writing real assertions
  with `pytest` + `pytest-playwright`.
- SKILL.md's code examples are real, working Playwright Python syntax,
  matching the actual `src/demo.py` in the sibling
  [demo-playwright-python](https://github.com/joelparkerhenderson/demo-playwright-python)
  repo — not invented selectors or APIs.
- SKILL.md links to the sibling demo repos and to the official Playwright
  Python docs, and carries the Google Terms of Service caveat wherever the
  Google Search / Google Maps sibling repos are mentioned.
- Out of scope: this repo does not itself run Playwright, does not ship a
  demo application, and does not track Playwright's own release notes
  beyond what's needed to keep SKILL.md accurate.

## Principles and rules

- Every code sample in SKILL.md must be valid Python using Playwright's
  real, current API (`playwright.sync_api` / `playwright.async_api`).
- Do not invent selectors, method names, or fixture behavior. Ground
  examples in the real code in the sibling demo-playwright-python repo.
- Every sibling-repo and external URL cited in SKILL.md, README.md,
  llms.txt, and llms.json must resolve.
- The Google Search / Google Maps sibling repos are always presented as
  illustrative-only, with the Google Terms of Service caveat, never as
  something to run repeatedly against the live sites.
- AGENTS.md is the pointer of record for agents; CLAUDE.md only points to
  AGENTS.md.

## Acceptance criteria

- Every code sample in SKILL.md is valid Python using Playwright's real
  API.
- Every linked sibling repo URL resolves.
- SKILL.md explicitly contrasts a print-based walkthrough with a real
  `pytest` + `pytest-playwright` test using `expect(...)` web-first
  assertions.
- SKILL.md's frontmatter `name` and `description` match what's declared in
  this spec and in README.md/llms.txt/llms.json.
- The Google ToS caveat appears wherever the Google Search/Maps sibling
  repos are mentioned.

## Related topics

- [README.md](../README.md)
- [AGENTS.md](../AGENTS.md)
- [SKILL.md](../SKILL.md)
