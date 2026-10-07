# Spec

## Summary

This demo uses Playwright with Java to describe driving a real Chromium
browser against the real, live [Google Search](https://www.google.com)
website and running three real assertion-based checks, as a
beginner-friendly example of end-to-end browser testing against real-world,
third-party markup.

## Scope

This spec covers `src/main/java/demo/Demo.java` only: the three test scenarios it runs, the
exact strings and selectors it checks, and the criteria for the demo to be
considered correct. It does not cover installation or how to run the script
— see `README.md` for that. It does not cover whether the demo is actually
executed against the live site — see the non-negotiable in `AGENTS.md`: this
code must not be run against live `google.com` in CI or automated tooling.

## Principles and rules

* This demo targets a real, live production website's well-known, stable
  accessible markup, described from documented knowledge of Playwright's
  Java API rather than live verification, in keeping with the
  non-negotiable in `AGENTS.md`.
* `src/main/java/demo/Demo.java` is the implementation. This file, `spec/index.md`, is the
  specification. They must agree exactly. If they ever disagree, that is a
  defect in one of them — fix it before doing anything else.

## Detail

1. **Page test**
   * Navigate to: `https://www.google.com`
   * Assert the page title equals exactly: `Google`

2. **Search test**
   * Locate the search box via selector: `textarea[name="q"]` (Google's
     search input is reachable via this selector as of this writing;
     historically it was a plain `<input name="q">` — this drift is noted
     as a code comment in `src/main/java/demo/Demo.java`)
   * Type the exact text: `testing examples`
   * Press `Enter`
   * Wait for the resulting page to load
   * Assert the resulting page title contains the exact substring:
     `testing examples`

3. **Link test**
   * Locate the first organic result link via selector: `#search a` (the
     first matching element)
   * Click it
   * Wait for the resulting page to load
   * Assert the resulting page's URL hostname is no longer `www.google.com`
     (i.e. the browser navigated away from google.com)

## Acceptance criteria

* The code is syntactically correct and matches this spec: the target URL,
  the three selectors above, and the exact strings above are all present in
  `src/main/java/demo/Demo.java` verbatim.
* This demo is not run against the live site in CI or any automated
  tooling — see the non-negotiable in `AGENTS.md`. A human may run it
  manually, aware that Google's markup and Terms of Service may make the
  live assertions fail or become non-compliant.

## Related topics

* [../README.md](../README.md)
* [../AGENTS.md](../AGENTS.md)

## Sources

* <https://www.google.com>
* <https://testingexamples.github.io/examples/google-search/>
