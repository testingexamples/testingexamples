# Demo Playwright Python for NHS Wales

A friendly, step-by-step tutorial that demonstrates:

* [Playwright](https://www.playwright.dev/) browser automation testing
* [Python](https://www.python.org/) programming language
* [Chromium](https://www.chromium.org/) open source web browser
* Real-world testing against the [NHS Wales](https://www.nhs.wales/) website

This demo is meant to be read top to bottom like a guide: open `src/demo.py`
alongside this README and follow along.

The exact test scenario is specified in [`spec/index.md`](spec/index.md); the code and the spec must always agree.

## What this demo tests

The script drives a real browser to the NHS Wales website and checks a few
things a visitor might do:

1. **Visit the home page** and verify the page title is `Home - NHS Wales`.
2. **Click the "About Us" link** and verify the resulting page has the title
   `About Us - NHS Wales` and a headline that reads `About Us`.
3. **Use the search box**: type `help`, click the search button, and verify
   the results page mentions `Search Results` and `Your search for "help"`.

Each step prints what it found, then asserts it matches what we expect, so
you can see the demo succeed (or fail loudly) as it runs.

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

The script will:

1. Launch your local Chrome/Chromium web browser and go to
   <https://www.nhs.wales/>.
2. Click around and use the search box, the same way a real visitor would.
3. Print a checklist of ✅ verifications as it confirms each expectation,
   then print "All checks passed. 🎉" when everything succeeds.

If you'd rather run the browser invisibly (headless), open `src/demo.py` and
change `headless=False` to `headless=True`.

## Tracking

* Package: demo-playwright-python-for-nhs-wales
* Version: 1.0.0
* Created: 2026-09-02T00:00:00Z
* Updated: 2026-09-02T00:00:00Z
* License: GPL-2.0-or-greater or for custom license contact us
* Contact: Joel Parker Henderson (joel@joelparkerhenderson.com)
