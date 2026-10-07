---
name: demo-playwright-typescript-for-google-maps-skill
description: Explains the Playwright + TypeScript reference for Google Maps interaction patterns (page title, search box, zoom); invoke when asked to explain, extend, or adapt this reference, or to port it to a different tool or site. This code must never be executed against live google.com.
---

# Demo Playwright TypeScript for Google Maps — skill

## What this repo teaches

This repo is a typed Playwright TypeScript reference for three interaction
patterns against Google Maps, mirroring
[testingexamples.github.io/examples/google-maps/](https://testingexamples.github.io/examples/google-maps/):

1. Verifying the page title of `https://www.google.com/maps` contains
   `Google Maps`.
2. Filling `[aria-label="Search Google Maps"]` with a query, pressing
   Enter, and verifying the resulting URL contains the query.
3. Clicking `[aria-label="Zoom in"]` and verifying the zoom level embedded
   in the URL's `@lat,lng,zoomz` fragment increased.

It also demonstrates why: Google Maps renders primarily to
`<canvas>`/WebGL, so DOM-based element location doesn't reach the map
surface itself — only the accessible UI chrome (search box, zoom buttons,
layers menu), which carries stable `aria-label` attributes, is reliably
selectable.

## Non-negotiable: never run this against live google.com

**Do not execute `src/demo.ts` against google.com, in CI or any automated
tooling.** Google's Terms of Service restrict automated querying of Google
Maps. This code exists to demonstrate syntax and interaction patterns
only — see `../AGENTS.md` for the full policy. This is unlike the sibling
repo demo-playwright-typescript-for-nhs-wales, which is meant to run for
real, because NHS Wales has no such restriction.

If you want a live target to actually practice these patterns against,
point Playwright at `https://testingexamples.github.io/en-001/practice/` instead.

## Adapting the pattern to a safe-to-run site

1. Pick a real target site you're allowed to test against repeatedly (for
   example, `https://testingexamples.github.io/en-001/practice/`).
2. Keep the same three-check shape: a page-level assertion (title), a
   search-and-verify interaction, and a state-change-and-verify
   interaction (zoom, in this case).
3. Update every selector and expected string, then update `spec/index.md`
   to match — the spec and the code must always agree.
4. Only then is it appropriate to actually run the demo.

This skill summarizes the repo. `AGENTS.md` and `spec/index.md` are the
source of truth — if this skill's summary ever disagrees with those, they
win.
