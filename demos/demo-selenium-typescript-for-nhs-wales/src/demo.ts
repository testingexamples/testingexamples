#!/usr/bin/env ts-node

///
// Demo of Selenium WebDriver browser automation with TypeScript, using the
// NHS Wales website <https://www.nhs.wales/> as a friendly real-world example.
//
// This is a beginner-friendly walkthrough: each step is commented so you can
// follow along and adapt the pattern to your own site.
//
// Ported from demo-selenium-javascript-for-nhs-wales/src/demo.js to
// TypeScript, following the same JavaScript→TypeScript porting convention
// already used by demo-selenium-typescript (this repo's non-NHS-Wales
// sibling). The scenario, selectors, and expected strings match the
// JavaScript sibling exactly.
//
// Please see the file README.md for more information.
//
// ## Tracking
//
//   * Package: demo-selenium-typescript-for-nhs-wales
//   * Version: 1.0.0
//   * Created: 2026-09-05T00:00:00Z
//   * Updated: 2026-09-05T00:00:00Z
//   * License: GPL-2.0-or-greater or for custom license contact us
//   * Contact: Joel Parker Henderson (joel@joelparkerhenderson.com)
///

// Import Selenium WebDriver parts, with their types.
import { Browser, Builder, By, until, WebDriver, WebElement } from 'selenium-webdriver';

// Import chromedriver options that you can set as you wish.
import { Options } from 'selenium-webdriver/chrome.js';
const options: Options = new Options();
options.addArguments('--verbose'); // Enable verbose logging.
options.addArguments('--disable-notifications'); // Disable notifications such as popups.
options.setUserPreferences({ "profile.default_content_setting_values.cookies": 2 }); // Reject cookies.

// Import strict assert, renamed for convenience as assert.
// We use this to verify each step actually did what we expect.
import { strict as assert } from 'assert';

async function demo(): Promise<void> {

    // Initialize the driver. Choose other browsers and options as you wish.
    const driver: WebDriver = await new Builder()
        .forBrowser(Browser.CHROME)
        .setChromeOptions(options)
        .build();

    try {

        ///
        // Step 1: Connect to the NHS Wales home page, then verify the
        // page title is what we expect.
        ///

        await driver.get('https://www.nhs.wales/');

        const homeTitle: string = await driver.getTitle();
        console.log(`Home page title: "${homeTitle}"`);
        assert.equal(homeTitle, 'Home - NHS Wales');
        console.log('✅ Home page title is correct.');

        ///
        // Step 2: Click the "About Us" link, then verify the response
        // page has the title and headline we expect.
        ///

        const aboutLink: WebElement = await driver.findElement(By.linkText('About Us'));
        await aboutLink.click();

        // Selenium does not auto-wait for navigation the way Playwright
        // does, so wait explicitly for the new page's title before reading
        // it — without this, `getTitle()` can read the still-navigating
        // page and see a stale or empty title.
        await driver.wait(until.titleIs('About Us - NHS Wales'), 10000);

        const aboutTitle: string = await driver.getTitle();
        console.log(`About Us page title: "${aboutTitle}"`);
        assert.equal(aboutTitle, 'About Us - NHS Wales');
        console.log('✅ About Us page title is correct.');

        const headlineElement: WebElement = await driver.findElement(By.css('h1'));
        const headline: string = (await headlineElement.getText()).trim();
        console.log(`About Us headline: "${headline}"`);
        assert.equal(headline, 'About Us');
        console.log('✅ About Us headline is correct.');

        ///
        // Step 3: Use the page search box: type "help", click the search
        // button, then verify the response page contains the phrases
        // we expect.
        ///

        await driver.get('https://www.nhs.wales/');

        const searchBox: WebElement = await driver.findElement(By.id('navKeywords'));
        await searchBox.sendKeys('help');

        const searchButton: WebElement = await driver.findElement(By.id('button-addon'));
        await searchButton.click();

        // Wait for the results page to finish navigating before reading
        // its body text — same reasoning as the explicit wait above.
        await driver.wait(until.titleIs('Search results - NHS Wales'), 10000);

        const bodyElement: WebElement = await driver.findElement(By.css('body'));

        // The results page renders its "Search Results" heading first and
        // its "Your search for ..." summary line slightly after (a second
        // client-side render pass) — so a title match alone isn't enough
        // to guarantee the summary line is there yet. Wait for it directly
        // before reading the body text.
        await driver.wait(until.elementTextContains(bodyElement, 'Your search for'), 10000);

        const bodyText: string = await bodyElement.getText();
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
        await driver.quit();
    }

}

demo().catch((err: Error): void => {
    console.error(err);
    process.exitCode = 1;
});
