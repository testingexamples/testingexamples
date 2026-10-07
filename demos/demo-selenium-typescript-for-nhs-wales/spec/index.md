# Spec: Demo Selenium TypeScript for NHS Wales

## Summary

This repo is a beginner-friendly walkthrough of Selenium WebDriver browser
automation in TypeScript. `src/demo.ts` drives a real Chrome browser to the
live production site <https://www.nhs.wales/> and runs three real,
assertion-based checks against the actual page content returned by the site.
It is a straight TypeScript port of the sibling
`demo-selenium-javascript-for-nhs-wales` repo's `src/demo.js`, with explicit
types (`WebDriver`, `WebElement`, etc.) added throughout.

## Scope

This spec covers only the three test scenarios implemented in
`src/demo.ts`. It does not cover unrelated NHS Wales site features, other
browsers, or other pages of the site.

## Principles and rules

* All checks are real assertions run against the live, real, production
  nhs.wales website. There is no mocking, stubbing, or fixture data.
* The demo uses the `selenium-webdriver` package's Chrome driver directly
  (via `Builder().forBrowser(Browser.CHROME)`), not a wrapper or test
  framework.
* Expected strings (titles, link text, selector ids, search term, result
  substrings) must be matched exactly as written below and in the source.
* If the code (`src/demo.ts`) and this file ever disagree, that is a defect
  in one of them; fix it before doing anything else.

## Detail

1. **Home page title check**
   * Navigate to: `https://www.nhs.wales/`
   * Get the page title.
   * Assert the title equals exactly: `Home - NHS Wales`

2. **About Us page check**
   * From the home page, click the link with link text: `About Us`
   * Explicitly wait (`until.titleIs('About Us - NHS Wales')`, 10s timeout)
     for the new page's title before reading it — Selenium does not
     auto-wait for navigation the way Playwright does, so reading the
     title immediately after `.click()` can see the still-navigating page.
   * Get the resulting page title.
   * Assert the title equals exactly: `About Us - NHS Wales`
   * Get the text of the `h1` element (via CSS selector `h1`), trimmed.
   * Assert the trimmed headline equals exactly: `About Us`

3. **Search results check**
   * Navigate to: `https://www.nhs.wales/`
   * Find the search input by id `navKeywords` and send keys: `help`
   * Find the search button by id `button-addon` and click it.
   * Explicitly wait (`until.titleIs('Search results - NHS Wales')`, 10s
     timeout) for the results page's title.
   * Explicitly wait further (`until.elementTextContains(bodyElement,
     'Your search for')`, 10s timeout): the results page renders its
     "Search Results" heading first and its "Your search for ..." summary
     line slightly after, in a second client-side render pass, so a title
     match alone does not guarantee the summary line has rendered yet.
   * Get the text of the `body` element.
   * Assert the body text contains the substring: `Search Results`
   * Assert the body text contains the exact phrase: `Your search for "help"`

## Acceptance criteria

* All 3 checks above pass when run against the live nhs.wales site.
* All 3 checks were verified for real on 2026-09-05 by actually installing
  dependencies and running `src/demo.ts` against the live site (twice,
  consecutively, both clean) — not just reasoned about. The two explicit
  waits above were added directly in response to real, reproducible
  timing failures hit during that verification (a `getTitle()` read
  immediately after `.click()` returning `null`, and a body-text read
  missing the "Your search for" line that rendered moments later) — they
  are load-bearing, not defensive boilerplate, and should not be removed.

## Related topics

* [../README.md](../README.md)
* [../AGENTS.md](../AGENTS.md)
* [demo-selenium-javascript-for-nhs-wales](https://github.com/joelparkerhenderson/demo-selenium-javascript-for-nhs-wales) — the JavaScript source this repo was ported from
* [demo-selenium-typescript](https://github.com/testingexamples/demo-selenium-typescript) — this repo's non-NHS-Wales sibling, whose TypeScript/tsconfig conventions this repo follows

## Sources

* <https://www.nhs.wales/>
