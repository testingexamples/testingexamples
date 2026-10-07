---
name: demo-selenium-javascript-for-nhs-wales
description: Explains and runs the Selenium WebDriver + JavaScript demo that drives a real Chrome browser against the live nhs.wales site to check its home page title, About Us page, and search results. Invoke when asked to run, explain, or adapt this demo, or to write a similar real-site Selenium assertion test in JavaScript.
---

# Demo Selenium JavaScript for NHS Wales

## What this demo teaches

This repo shows real-world Selenium assertion testing in JavaScript against
a live government website, with no mocking or fixtures:

1. Load `https://www.nhs.wales/` and assert the page title is exactly
   `Home - NHS Wales`.
2. Click the `About Us` link and assert the resulting page title is exactly
   `About Us - NHS Wales` and its `h1` reads exactly `About Us`.
3. Type `help` into the search input (`id=navKeywords`), click the search
   button (`id=button-addon`), and assert the results page body contains
   `Search Results` and the exact phrase `Your search for "help"`.

## How to run it

```sh
npm install --save selenium-webdriver@latest
npm install --save chromedriver@latest
node src/demo.js
```

See `README.md` for full install steps and chromedriver troubleshooting
(macOS Gatekeeper quarantine, Chrome/chromedriver version mismatches).

## Adapting the pattern to a different real site

1. Pick a real page and note its exact title string.
2. Pick a real link on that page and the exact title/headline of where it
   leads.
3. Pick a real search box (or form) and the exact substrings you expect on
   the results page.
4. Mirror the three-step structure in `src/demo.js`: navigate, assert exact
   title; click, assert exact title and headline; fill and submit, assert
   substrings in the resulting body text.
5. Update `spec/index.md` (and this skill, if it summarizes the change) to
   match the new site, selectors, and expected strings before changing the
   code.

---

This skill summarizes the repo. `AGENTS.md` and `spec/index.md` are the
source of truth — if this skill's summary ever disagrees with those, they
win.
