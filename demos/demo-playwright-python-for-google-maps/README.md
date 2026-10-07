# Demo Playwright Python for Google Maps

A friendly, step-by-step tutorial that demonstrates:

* [Playwright](https://www.playwright.dev/) browser automation testing
* [Python](https://www.python.org/) programming language
* [Chromium](https://www.chromium.org/) open source web browser
* Testing patterns against a canvas/WebGL-heavy, real-world application, using [Google Maps](https://www.google.com/maps) as the example

This demo is meant to be read top to bottom like a guide: open `src/demo.py`
alongside this README and follow along.

The exact test scenario is specified in [`spec/index.md`](spec/index.md); the code and the spec must always agree.

## Caution: Google's Terms of Service

Google's [Terms of Service](https://www.google.com/policies/terms/) restrict
automated querying of its services, including Google Maps. This demo exists
to show the syntax and interaction pattern of a Playwright test written
against a real, third-party mapping application — it is **not** meant to be
run repeatedly, or at all, against the live `google.com`. See
[AGENTS.md](AGENTS.md) for the non-negotiable that follows from this. If you
want a target you can safely run automation against as often as you like,
point Playwright at
[testingexamples.github.io](https://testingexamples.github.io/en-001/practice/) instead,
which was built exactly for that: stable ids, names, classes, and text that
don't shift under you.

## What this demo tests

Most of Google Maps renders to a `<canvas>`/WebGL surface rather than to
regular DOM elements, so you generally cannot "find" a street or a pin the
way you find a paragraph of text. This demo keeps its assertions modest and
honest, checking only what is reliably observable outside the canvas — the
page title and the URL:

1. **Visit Google Maps** and verify the page title contains `Google Maps`.
2. **Use the search box**: type `Cardiff Castle`, press Enter, and verify
   the resulting URL contains `Cardiff`.
3. **Click the zoom-in button** and verify the URL's embedded zoom-level
   number increased.

Each step prints what it found, then asserts it matches what we expect, the
same assertion style used by this workspace's other Playwright Python
demos.

## Install

### Install Python

Install Python from <https://www.python.org/>

Run this to confirm your version:

```sh
python3 --version
```

Output should be at least:

```stdout
Python 3.13.3
```

### Install Playwright

Install Playwright and its browser:

```sh
pip install playwright
playwright install chromium
```

### Update

Run:

```sh
pip install --upgrade pip
pip install --upgrade playwright
```

## Run

Run:

```sh
python3 src/demo.py
```

Read the caution above first. This repository does not run this script
against the live site in CI or in any automated tooling; see
[AGENTS.md](AGENTS.md).

The script will:

1. Launch your local Chrome/Chromium web browser and go to
   <https://www.google.com/maps>.
2. Search for "Cardiff Castle" and click zoom in, the same way a real user
   would.
3. Print a checklist of ✅ verifications as it confirms each expectation,
   then print "All checks passed. 🎉" when everything succeeds.

If you'd rather run the browser invisibly (headless), open `src/demo.py` and
change `headless=False` to `headless=True`.

## Tracking

* Package: demo-playwright-python-for-google-maps
* Version: 1.0.0
* Created: 2026-09-03T00:00:00Z
* Updated: 2026-09-03T00:00:00Z
* License: GPL-2.0-or-greater or for custom license contact us
* Contact: Joel Parker Henderson (joel@joelparkerhenderson.com)
