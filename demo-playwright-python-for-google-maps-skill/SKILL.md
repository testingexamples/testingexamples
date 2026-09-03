---
name: demo-playwright-python-for-google-maps-skill
description: Explains the Playwright + Python demo that describes testing Google Maps' well-known, stable accessible markup with canvas-aware assertions; invoke when someone wants to understand what it checks, or adapt its pattern to a different canvas/WebGL-heavy site, without running it against live google.com.
---

# Demo Playwright Python for Google Maps — skill

## What this demo teaches

This repo is a beginner-friendly example of **assertion-based browser
testing against a canvas/WebGL-heavy, real-world, third-party application**,
using Google Maps as the example. It uses Playwright with Python to
describe driving a real Chromium browser to
<https://www.google.com/maps> and checking three things a user might
actually do:

1. The page title contains `Google Maps`.
2. Typing `Cardiff Castle` into the search box
   (`[aria-label="Search Google Maps"]`) and pressing Enter leads to a URL
   that contains `Cardiff`.
3. Clicking the zoom-in button (`[aria-label="Zoom in"]`) increases the
   zoom-level number embedded in the URL (`@lat,lng,zoomz`).

Most of Google Maps renders to `<canvas>`/WebGL, so this demo deliberately
does not try to assert on any pin, label, or street drawn on the map
itself — only on the page title and the URL, which stay observable outside
the canvas.

## CAUTION: do not run this against live google.com

Google's Terms of Service restrict automated querying of its services,
including Google Maps. This repo's code must never be executed against the
live site in CI or any automated tooling — see `AGENTS.md` for the
non-negotiable. Read `README.md`'s caution before running anything
manually.

## Adapting the pattern to a different canvas-heavy site

1. Pick a real target site whose Terms of Service permit automated testing,
   or use a fixture/mock/site you control.
2. Identify what is observable outside the canvas — page title, URL
   parameters, any DOM-based side panel — since you generally cannot
   locate elements drawn to canvas/WebGL the way you locate a paragraph of
   text.
3. Prefer accessible-name locators (`[aria-label="..."]`) over generated
   class names, which change between front-end deploys.
4. Copy the structure of `src/demo.py`: launch a browser, navigate, assert,
   print a checkmark, move to the next step, close the browser in a
   `finally` block.
5. Keep every expected string exact and verbatim between your spec and your
   code, the same way this repo keeps `spec/index.md` and `src/demo.py` in
   agreement.

This skill summarizes the repo. `AGENTS.md` and `spec/index.md` are the
source of truth — if this skill's summary ever disagrees with those, they
win.
