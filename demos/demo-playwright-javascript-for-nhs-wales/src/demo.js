#!/usr/bin/env node

///
// Demo of Playwright browser automation with JavaScript, using the
// NHS Wales website <https://www.nhs.wales/> as a friendly real-world example.
//
// This is a beginner-friendly walkthrough: each step is commented so you can
// follow along and adapt the pattern to your own site.
//
// Please see the file README.md for more information.
//
// ## Tracking
//
//   * Package: demo-playwright-javascript-for-nhs-wales
//   * Version: 1.0.0
//   * Created: 2026-09-02T00:00:00Z
//   * Updated: 2026-09-02T00:00:00Z
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
        // Step 1: Connect to the NHS Wales home page, then verify the
        // page title is what we expect.
        ///

        await page.goto('https://www.nhs.wales/');

        const homeTitle = await page.title();
        console.log(`Home page title: "${homeTitle}"`);
        assert.equal(homeTitle, 'Home - NHS Wales');
        console.log('✅ Home page title is correct.');

        ///
        // Step 2: Click the "About Us" link, then verify the response
        // page has the title and headline we expect.
        ///

        await page.getByRole('link', { name: 'About Us', exact: true }).first().click();
        await page.waitForLoadState('load');

        const aboutTitle = await page.title();
        console.log(`About Us page title: "${aboutTitle}"`);
        assert.equal(aboutTitle, 'About Us - NHS Wales');
        console.log('✅ About Us page title is correct.');

        const headline = (await page.locator('h1').first().innerText()).trim();
        console.log(`About Us headline: "${headline}"`);
        assert.equal(headline, 'About Us');
        console.log('✅ About Us headline is correct.');

        ///
        // Step 3: Use the page search box: type "help", click the search
        // button, then verify the response page contains the phrases
        // we expect.
        ///

        await page.goto('https://www.nhs.wales/');
        await page.locator('#navKeywords').fill('help');
        await page.locator('#button-addon').click();
        await page.waitForLoadState('load');

        const bodyText = await page.locator('body').innerText();
        assert.ok(bodyText.includes('Search Results'), 'Expected page to contain "Search Results"');
        console.log('✅ Search results page contains "Search Results".');
        assert.ok(bodyText.includes('Your search for "help"'), 'Expected page to contain \'Your search for "help"\'');
        console.log('✅ Search results page contains \'Your search for "help"\'.');

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
