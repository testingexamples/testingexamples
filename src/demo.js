#!/usr/bin/env node

///
// Demo of Playwright browser automation with JavaScript, using the real,
// live Google Search website <https://www.google.com> as a real-world
// example.
//
// This is a beginner-friendly walkthrough: each step is commented so you can
// follow along and adapt the pattern to your own site.
//
// IMPORTANT: Google's Terms of Service restrict automated querying of
// Google Search. This file is written and reviewed for correctness, but it
// is deliberately NOT meant to be run repeatedly, or at all, in CI or other
// automated tooling against the live google.com. See README.md and
// AGENTS.md for the full caution.
//
// Please see the file README.md for more information.
//
// ## Tracking
//
//   * Package: demo-playwright-javascript-for-google-search
//   * Version: 1.0.0
//   * Created: 2026-09-03T00:00:00Z
//   * Updated: 2026-09-03T00:00:00Z
//   * License: GPL-2.0-or-greater or for custom license contact us
//   * Contact: Joel Parker Henderson (joel@joelparkerhenderson.com)
///

// Import Playwright.
import { chromium } from 'playwright';

// Import strict assert, renamed for convenience as assert.
// We use this to verify each step actually did what we expect.
import { strict as assert } from 'assert';

async function demo() {

    // Launch a browser. Set headless to true if you don't want to watch it.
    const browser = await chromium.launch({
        headless: false,
    });

    const context = await browser.newContext();
    const page = await context.newPage();

    try {

        ///
        // Step 1: Connect to the Google Search home page, then verify the
        // page title is what we expect.
        ///

        await page.goto('https://www.google.com');

        const homeTitle = await page.title();
        console.log(`Home page title: "${homeTitle}"`);
        assert.equal(homeTitle, 'Google');
        console.log('✅ Home page title is correct.');

        ///
        // Step 2: Use the search box: type "testing examples" and press
        // Enter, then verify the resulting page title contains our query.
        //
        // Note on selector drift: Google's search box has historically been
        // rendered as a plain <input>. As of this writing it is rendered as
        // a <textarea name="q">, which is why we select it with
        // `textarea[name="q"]` rather than `input[name="q"]`. Google
        // changes its markup without notice, so if this selector ever stops
        // matching, that is expected drift, not a logic error — see
        // AGENTS.md and spec/index.md for how this repo treats that.
        //
        // Note on pressing Enter instead of clicking submit: Google's
        // search page renders a "Google Search" submit button, but it is
        // frequently obscured by an autocomplete/predictions dropdown that
        // opens as soon as you start typing, which makes a real click
        // unreliable (the click can land on a suggestion instead of the
        // button, or be intercepted entirely). Pressing Enter in the search
        // box submits the form directly and sidesteps that dropdown, so it
        // is the more reliable interaction here.
        ///

        const searchBox = page.locator('textarea[name="q"]');
        await searchBox.fill('testing examples');
        await searchBox.press('Enter');
        await page.waitForLoadState('load');

        const resultsTitle = await page.title();
        console.log(`Search results page title: "${resultsTitle}"`);
        assert.ok(
            resultsTitle.includes('testing examples'),
            `Expected page title to contain "testing examples", got "${resultsTitle}"`
        );
        console.log('✅ Search results page title contains "testing examples".');

        ///
        // Step 3: Click the first organic result link, then verify the
        // browser navigated away from google.com.
        //
        // Note on selector drift: `#search` is Google's long-standing
        // container id for the organic results column, and `a` picks the
        // first link inside it. The exact internal structure of a Google
        // results page (nested divs, generated class names, ad slots
        // above/around the organic results) shifts over time, so a
        // narrowly-scoped selector like this is a reasonable, if fragile,
        // best effort rather than a guarantee.
        ///

        const firstResultLink = page.locator('#search a').first();
        await firstResultLink.click();
        await page.waitForLoadState('load');

        const resultUrl = new URL(page.url());
        console.log(`Navigated to: "${resultUrl.href}"`);
        assert.ok(
            resultUrl.hostname !== 'www.google.com',
            `Expected to navigate away from www.google.com, but hostname is "${resultUrl.hostname}"`
        );
        console.log('✅ Browser navigated away from www.google.com.');

        console.log('\nAll checks passed. 🎉');

    } catch (err) {
        console.log(err.message);
        console.log(err.stack);
        process.exitCode = 1;
    } finally {
        await browser.close();
    }

}

demo().catch((err) => {
    console.error(err);
    process.exitCode = 1;
});
