#!/usr/bin/env ts-node

///
// Demo of Playwright browser automation with TypeScript, using the
// NHS Wales website <https://www.nhs.wales/> as a friendly real-world example.
//
// This is a beginner-friendly walkthrough: each step is commented so you can
// follow along and adapt the pattern to your own site.
//
// Ported from demo-playwright-javascript-for-nhs-wales/src/demo.js to
// TypeScript. The scenario, selectors, and expected strings match that
// sibling repo exactly.
//
// Please see the file README.md for more information.
//
// ## Tracking
//
//   * Package: demo-playwright-typescript-for-nhs-wales
//   * Version: 1.0.0
//   * Created: 2026-09-03T00:00:00Z
//   * Updated: 2026-09-03T00:00:00Z
//   * License: GPL-2.0-or-greater or for custom license contact us
//   * Contact: Joel Parker Henderson (joel@joelparkerhenderson.com)
///

// Import Playwright types and functions.
import { chromium } from 'playwright';
import type { Browser, BrowserContext, Page, Locator } from 'playwright';

// Import strict assert, renamed for convenience as assert.
// We use this to verify each step actually did what we expect.
import { strict as assert } from 'assert';

async function demo(): Promise<void> {

    // Launch a browser. Set headless to true if you don't want to watch it.
    const browser: Browser = await chromium.launch({
        headless: false,
    });

    const context: BrowserContext = await browser.newContext();
    const page: Page = await context.newPage();

    try {

        ///
        // Step 1: Connect to the NHS Wales home page, then verify the
        // page title is what we expect.
        ///

        await page.goto('https://www.nhs.wales/');

        const homeTitle: string = await page.title();
        console.log(`Home page title: "${homeTitle}"`);
        assert.equal(homeTitle, 'Home - NHS Wales');
        console.log('✅ Home page title is correct.');

        ///
        // Step 2: Click the "About Us" link, then verify the response
        // page has the title and headline we expect.
        ///

        await page.getByRole('menuitem', { name: 'About Us', exact: true }).first().click();
        await page.waitForLoadState('load');

        const aboutTitle: string = await page.title();
        console.log(`About Us page title: "${aboutTitle}"`);
        assert.equal(aboutTitle, 'About Us - NHS Wales');
        console.log('✅ About Us page title is correct.');

        const headlineLocator: Locator = page.locator('h1').first();
        const headline: string = (await headlineLocator.innerText()).trim();
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

        const bodyLocator: Locator = page.locator('body');
        const bodyText: string = await bodyLocator.innerText();
        assert.ok(bodyText.includes('Search Results'), 'Expected page to contain "Search Results"');
        console.log('✅ Search results page contains "Search Results".');
        assert.ok(bodyText.includes('Your search for "help"'), 'Expected page to contain \'Your search for "help"\'');
        console.log('✅ Search results page contains \'Your search for "help"\'.');

        console.log('\nAll checks passed. 🎉');

    } catch (err: unknown) {
        if (err instanceof Error) {
            console.log(err.message);
            console.log(err.stack);
        } else {
            console.log('An unknown error occurred:', err);
        }
        process.exitCode = 1;
    } finally {
        await browser.close();
    }

}

demo().catch((err: Error): void => {
    console.error(err);
    process.exitCode = 1;
});
