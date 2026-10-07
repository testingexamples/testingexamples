# Demo Playwright TypeScript for NHS Wales

A friendly, step-by-step tutorial that demonstrates:

* [Playwright](https://www.playwright.dev/) browser automation testing
* [TypeScript](https://en.wikipedia.org/wiki/TypeScript) programming language
* [Node](https://nodejs.org/) runtime built on Chrome's V8 JavaScript engine
* [Chromium](https://www.chromium.org/) open source web browser
* Real-world testing against the [NHS Wales](https://www.nhs.wales/) website

This demo is meant to be read top to bottom like a guide: open `src/demo.ts`
alongside this README and follow along. It is a TypeScript port of the
sibling repo
[demo-playwright-javascript-for-nhs-wales](https://github.com/testingexamples/demo-playwright-javascript-for-nhs-wales/);
the scenario, selectors, and expected strings match that repo exactly.

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

Install TypeScript and ts-node to run files:

```sh
npm install --save-dev typescript @types/node ts-node
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
./src/demo.ts
```

If you prefer, you can run with ts-node explicitly:

```sh
npx ts-node ./src/demo.ts
```

Or compile and run:

```sh
npx tsc ./src/demo.ts
node ./src/demo.js
```

The script will:

1. Launch your local Chrome/Chromium web browser and go to
   <https://www.nhs.wales/>.
2. Click around and use the search box, the same way a real visitor would.
3. Print a checklist of ✅ verifications as it confirms each expectation,
   then print "All checks passed. 🎉" when everything succeeds.

If you'd rather run the browser invisibly (headless), open `src/demo.ts` and
change `headless: false` to `headless: true`.

## Known external blocker

As of 2026-09-02, nhs.wales's TLS certificate has been expired since
2025-08-18. Running this demo currently fails with a certificate error. That
failure is external to this repo and is not a code defect. See
[`AGENTS.md`](AGENTS.md) and [`spec/index.md`](spec/index.md) for details.

## Tracking

* Package: demo-playwright-typescript-for-nhs-wales
* Version: 1.0.0
* Created: 2026-09-03T00:00:00Z
* Updated: 2026-09-03T00:00:00Z
* License: GPL-2.0-or-greater or for custom license contact us
* Contact: Joel Parker Henderson (joel@joelparkerhenderson.com)
