# Spec

## Summary

This repo describes, in typed Playwright TypeScript, three interaction
patterns and assertion checks against Google Maps
(`https://www.google.com/maps`). It mirrors the interaction patterns
documented at
[testingexamples.github.io/examples/google-maps/](https://testingexamples.github.io/examples/google-maps/).

## Scope

This spec covers `src/demo.ts` only: the three checks it describes, the
exact selectors, and the criteria that would make each check pass, if it
were ever run. It does not cover installation — see `README.md` — and it
does not authorize running the code; see `AGENTS.md`.

## Principles and rules

* **This code must never be executed against live google.com, in CI or
  any automated tooling.** Google's Terms of Service restrict automated
  querying of Google Maps. This repo is a reference for syntax and
  interaction patterns only.
* Google Maps renders primarily to `<canvas>`/WebGL, so selectors target
  the accessible UI chrome (`aria-label` attributes on the search box and
  zoom buttons) rather than the map surface itself.
* `src/demo.ts` is the reference implementation. This file, `spec/index.md`,
  is the specification. They must agree exactly. If they ever disagree,
  that is a defect in one of them — fix it without running the code
  against google.com.

## Detail

1. **Page title check**
   * Navigate to: `https://www.google.com/maps`
   * Assert the page title contains: `Google Maps`

2. **Search check**
   * Fill the element matching selector `[aria-label="Search Google Maps"]`
     with a query (e.g. `Cardiff Castle`)
   * Press `Enter`
   * Assert the resulting page's URL contains the query

3. **Zoom check**
   * Parse the `@lat,lng,zoomz` fragment embedded in the current URL (e.g.
     the `15` in `@51.4816,-3.1791,15z`) and record the zoom value
   * Click the element matching selector `[aria-label="Zoom in"]`
   * Re-parse the `@lat,lng,zoomz` fragment in the resulting URL
   * Assert the new zoom value is greater than the recorded zoom value

## Acceptance criteria

These three checks describe what would need to be true for the demo to
pass, if it were ever run against the live site. This repo does not run
them — see `AGENTS.md` for the non-negotiable policy.

## Related topics

* [../README.md](../README.md)
* [../AGENTS.md](../AGENTS.md)

## Sources

* <https://testingexamples.github.io/examples/google-maps/>
* <https://www.google.com/maps>
