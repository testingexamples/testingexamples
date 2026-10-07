# Spec

## Summary

This demo uses Playwright with Java to describe driving a real Chromium
browser against the real, live [Google Maps](https://www.google.com/maps)
website and running three real assertion-based checks, as a
beginner-friendly example of end-to-end browser testing against a
canvas/WebGL-heavy, real-world application.

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
* Most of Google Maps renders to `<canvas>`/WebGL rather than to regular DOM
  elements, so assertions are deliberately modest: they check the page
  title and the URL, never anything drawn on the canvas itself.
* `src/main/java/demo/Demo.java` is the implementation. This file, `spec/index.md`, is the
  specification. They must agree exactly. If they ever disagree, that is a
  defect in one of them — fix it before doing anything else.

## Detail

1. **Visit test**
   * Navigate to: `https://www.google.com/maps`
   * Assert the page title contains the exact substring: `Google Maps`

2. **Search test**
   * Locate the search box via selector: `[aria-label="Search Google Maps"]`
     (a stable accessible-name selector, chosen because it beats a
     generated CSS class name that changes between Google's front-end
     deploys — noted as a code comment in `src/main/java/demo/Demo.java`)
   * Type the exact text: `Cardiff Castle`
   * Press `Enter`
   * Wait for the resulting page to load
   * Assert the resulting URL contains the exact substring: `Cardiff`

3. **Zoom test**
   * Click the element matching selector: `[aria-label="Zoom in"]`
   * Assert the URL's embedded zoom-level number increased (Google Maps
     encodes the current view as `@lat,lng,zoomz` in the URL; extract the
     number before the trailing `z` before and after the click and assert
     the value after is greater than the value before — explained as a code
     comment in `src/main/java/demo/Demo.java`)

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

* <https://www.google.com/maps>
* <https://testingexamples.github.io/examples/google-maps/>
