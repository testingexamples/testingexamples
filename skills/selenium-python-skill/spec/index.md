# Spec

## Summary

This repo's one contract: [SKILL.md](../SKILL.md) must accurately describe how to
use Selenium WebDriver in Python. Its code examples must be real, working syntax
that matches the sibling
[demo-selenium-python](https://github.com/testingexamples/demo-selenium-python)
repo, and its sibling-repo links must stay correct.

## Scope

In scope:

- Explaining what Selenium is, how to install it, and how browser drivers
  (including Selenium Manager and manual chromedriver setup) work.
- Explaining the four core concepts of browser automation as Selenium implements
  them: locating (`By` / `find_element` / `find_elements`), acting, waiting
  (explicit waits via `WebDriverWait` + `expected_conditions`, since Selenium has
  no built-in auto-waiting), and asserting.
- Contrasting a print-based walkthrough script with a real `pytest` test that uses
  a fixture and `assert` statements.
- Listing common pitfalls specific to Selenium in Python.
- Linking out to this project's sibling demo repos and to official Selenium docs.

Out of scope:

- Selenium bindings for languages other than Python.
- Full API reference coverage of every `By` strategy, every `expected_conditions`
  helper, or every WebDriver capability. This skill teaches the core patterns, not
  an exhaustive manual.
- Being itself a runnable demo or test suite. That's what the sibling demo repos
  are for.

## Principles and rules

- Every code sample must use selenium's real, current Python API — no invented
  methods, classes, or import paths.
- The worked example must match the real `demo.py` from
  `testingexamples/demo-selenium-python`, not a rewritten or reimagined version.
- Wherever Google Search or Google Maps sibling repos are mentioned, the ToS
  caveat (illustrative only, not for repeated automated querying) must be
  included.
- Sibling-repo and documentation links must point at real, resolvable URLs.

## Acceptance criteria

- Every code sample in SKILL.md is valid Python using selenium's real API.
- Every linked sibling repo URL resolves.
- The distinction between `find_element` and `find_elements` is stated
  correctly (singular raises `NoSuchElementException`, plural returns an empty
  list).
- The lack of Selenium auto-waiting, and the `WebDriverWait` +
  `expected_conditions` remedy, are both present and correct.
- The walkthrough-vs-real-test contrast includes both a print-based script and a
  pytest fixture + `assert`-based test.
- The Google ToS caveat appears next to both Google Search and Google Maps
  sibling-repo mentions.

## Related topics

- [README.md](../README.md)
- [AGENTS.md](../AGENTS.md)
- [SKILL.md](../SKILL.md)
