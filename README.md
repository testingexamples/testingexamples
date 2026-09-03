# Demo Playwright JavaScript for Google Search

A friendly, step-by-step tutorial that demonstrates:

* [Playwright](https://www.playwright.dev/) browser automation testing
* [JavaScript](https://en.wikipedia.org/wiki/JavaScript) programming language
* [Node](https://nodejs.org/) runtime built on Chrome's V8 JavaScript engine
* [Chromium](https://www.chromium.org/) open source web browser
* Real-world testing patterns against the [Google Search](https://www.google.com) website

This demo is meant to be read top to bottom like a guide: open `src/demo.js`
alongside this README and follow along.

The exact test scenario is specified in [`spec/index.md`](spec/index.md); the code and the spec must always agree.

## Caution: Google's Terms of Service

Google's Terms of Service restrict automated querying of Google Search.
`src/demo.js` in this repo is written and reviewed for correctness against
Google's well-known, stable accessible markup, but it is **not meant to be
run repeatedly, or at all, against the live google.com** — including in CI
or any other automated tooling. It exists to show the syntax and
interaction pattern of a real Playwright script, the same way the
[Google Search examples](https://testingexamples.github.io/examples/google-search/)
on this project's website do. See `AGENTS.md` for the non-negotiable
policy this repo follows because of this.

## What this demo tests

The script describes driving a real browser to the Google Search website and
checking a few things a visitor might do:

1. **Visit the home page** and verify the page title equals `Google`.
2. **Use the search box**: type `testing examples`, press Enter, and verify
   the resulting page title contains `testing examples`.
3. **Click the first result link** and verify the browser navigated away
   from `www.google.com`.

Each step prints what it found, then asserts it matches what we expect, so
you can see the demo succeed (or fail loudly) if it is ever run.

## Install

### Install Node and NPM

Install Node and NPM from <https://nodejs.org/>

Run this to confirm your version:

```sh
node -v
```

Output should be at least:

```stdout
v23.6.1
```

Run this to confirm your version:

```sh
npm -v
```

Output should be at least:

```stdout
11.2.0
```

### Install Playwright

Install Playwright and its browser:

```sh
npm install playwright@latest
npx playwright install chromium
```

### Update

Run:

```sh
npm install npm@latest
npm upgrade
npm audit fix
```

## Run

Run:

```sh
node src/demo.js
```

**Please read the caution above first.** This repo's non-negotiable policy
(see `AGENTS.md`) is that `src/demo.js` must never be executed against the
live google.com in CI or other automated tooling, because of Google's Terms
of Service. If you choose to run it yourself, understand that you are doing
so against a live, third-party production site outside this project's
control, and that Google's markup may have drifted since this was written
(see the comments in `src/demo.js`, particularly around the search box
selector).

If it is run, the script will:

1. Launch your local Chrome/Chromium web browser and go to
   <https://www.google.com>.
2. Type into the search box and press Enter, the same way a real visitor
   would, then click the first result link.
3. Print a checklist of ✅ verifications as it confirms each expectation,
   then print "All checks passed. 🎉" when everything succeeds.

If you'd rather run the browser invisibly (headless), open `src/demo.js` and
change `headless: false` to `headless: true`.

## Tracking

* Package: demo-playwright-javascript-for-google-search
* Version: 1.0.0
* Created: 2026-09-03T00:00:00Z
* Updated: 2026-09-03T00:00:00Z
* License: GPL-2.0-or-greater or for custom license contact us
* Contact: Joel Parker Henderson (joel@joelparkerhenderson.com)
