# Demo Selenium TypeScript for Google Maps

A friendly, step-by-step tutorial that demonstrates:

* [Selenium](https://www.selenium.dev/) browser automation testing
* [TypeScript](https://en.wikipedia.org/wiki/TypeScript) programming language
* [Node](https://nodejs.org/) runtime built on Chrome's V8 JavaScript engine
* [ChromeDriver](https://developer.chrome.com/docs/chromedriver) extends WebDriver by adding Chromium-specific capabilities
* Real-world testing patterns against the [Google Maps](https://www.google.com/maps) website

This demo is meant to be read top to bottom like a guide: open `src/demo.ts`
alongside this README and follow along.

The exact test scenario is specified in [`spec/index.md`](spec/index.md); the code and the spec must always agree.

## ⚠️ Caution: Google's Terms of Service restrict automated querying

Google's Terms of Service restrict automated querying of Google Maps.
`src/demo.ts` in this repo is written and reviewed for correctness against
Google's well-known, stable accessible markup, but it is **not meant to be
run repeatedly, or at all, against the live google.com/maps** — including
in CI or any other automated tooling. It exists to show the syntax and
interaction pattern of a real Selenium script, the same way the
[Google Maps examples](https://testingexamples.github.io/examples/google-maps/)
on this project's website do. See `AGENTS.md` for the non-negotiable
policy this repo follows because of this.

If you choose to try the script yourself, do so sparingly and manually, at
your own judgment and risk, and review Google's Terms of Service first.

## What this demo tests

Most of the Google Maps surface renders to a `<canvas>` element (or WebGL),
so it cannot be inspected the way a normal DOM element can — you generally
cannot "find" a street or a pin the way you find a paragraph of text. Because
of that, this demo keeps its assertions modest and honest about that
limitation, and leans on stable `aria-label` attributes and the page URL
(which Google Maps encodes the current search and view into) rather than
trying to inspect the map surface itself.

The script describes driving a real browser to the Google Maps website and
checking a few things a visitor might do:

1. **Visit the home page** and verify the page title contains `Google Maps`.
2. **Use the search box** (`[aria-label="Search Google Maps"]`): type
   `Cardiff Castle`, press Return, and verify the resulting URL contains
   `Cardiff`.
3. **Click "Zoom in"** (`[aria-label="Zoom in"]`) and verify the zoom level
   encoded in the URL increased.

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

### Install TypeScript

Install TypeScript, ts-node, and Node type definitions:

```sh
npm install --save-dev typescript @types/node ts-node
```

### Install Selenium

Install Selenium WebDriver:

```sh
npm install --save selenium-webdriver@latest
```

Selenium WebDriver ships its own JavaScript implementation but does not
ship complete TypeScript type declarations, so also install the community
types package as a dev dependency:

```sh
npm install --save-dev @types/selenium-webdriver@latest
```

### Install chromedriver

Install Google chromedriver:

```sh
npm install --save chromedriver@latest
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
npx ts-node src/demo.ts
```

**Please read the caution above first.** This repo's non-negotiable policy
(see `AGENTS.md`) is that `src/demo.ts` must never be executed against the
live google.com/maps in CI or other automated tooling, because of Google's
Terms of Service. If you choose to run it yourself, understand that you are
doing so against a live, third-party production site outside this project's
control, and that Google's markup may have drifted since this was written
(see the comments in `src/demo.ts`, particularly around the zoom-level URL
pattern).

If it is run, the script will:

1. Launch your local Chrome web browser and go to
   <https://www.google.com/maps>.
2. Search for "Cardiff Castle" and click "Zoom in", the same way a real
   visitor would.
3. Print a checklist of ✅ verifications as it confirms each expectation,
   then print "All checks passed. 🎉" when everything succeeds.

### Troubleshooting “chromedriver” Not Opened

If you get this kind of error message:

```txt
“chromedriver” Not Opened. Apple could not verify “chromedriver”
is free of malware that may harm your Mac or compromise your privacy.
```

Or this kind of error message:

```txt
Apple is not able to verify that it is free from malware that could harm your
Mac or compromise your privacy. Don’t open this unless you are certain it is
from a trustworthy source.
```

Then click "Done".

Try this command line solution:

```sh
xattr -d com.apple.quarantine $(which chromedriver) 2>/dev/null
```

Try adjusting your system settings:

* Apple menu -> Settings -> Security & Privacy -> General

* See the entry that says: "chromedriver" was blocked to protect your Mac.

* Click the button "Allow Anyway".

### Troubleshooting "This version of ChromeDriver …"

If you get this kind of error message:

```txt
UnhandledPromiseRejectionWarning:
SessionNotCreatedError: session not created:
This version of ChromeDriver only supports Chrome version …
```

Then you may need to harmonize your Chrome browser app and your Chrome webdriver.

If you use macOS brew, then upgrade chromedriver:

```sh
brew upgrade chromedriver
```

To update your Chrome browser app:

* On your computer, open Chrome.

* Find your current Chrome version by typing in the URL bar: `chrome://version/`.

* You should see a web page with many details, and you should see the first line with the version number, such as: "Google Chrome 135.0.7049.86 (Official Build)".

* At top right, tap the "More" icon, which is 3 vertical dots.

* You see the "More" menu. If you see a menu item "Update", then choose it. If you don't see a menu item "Update", then  you're on the current version.

To update your Chrome webdriver:

* Go to https://chromedriver.chromium.org/downloads

* Download the version that matches your Chrome browser app.

## Tracking

* Package: demo-selenium-typescript-for-google-maps
* Version: 1.0.0
* Created: 2026-09-03T00:00:00Z
* Updated: 2026-09-03T00:00:00Z
* License: GPL-2.0-or-greater or for custom license contact us
* Contact: Joel Parker Henderson (joel@joelparkerhenderson.com)
