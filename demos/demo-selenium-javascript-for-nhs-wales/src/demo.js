#!/usr/bin/env node

///
// Demo of Selenium WebDriver browser automation with JavaScript, using the
// NHS Wales website <https://www.nhs.wales/> as a friendly real-world example.
//
// This is a beginner-friendly walkthrough: each step is commented so you can
// follow along and adapt the pattern to your own site.
//
// Please see the file README.md for more information.
//
// ## Tracking
//
//   * Package: demo-selenium-javascript-for-nhs-wales
//   * Version: 1.0.0
//   * Created: 2026-09-02T00:00:00Z
//   * Updated: 2026-09-02T00:00:00Z
//   * License: GPL-2.0-or-greater or for custom license contact us
//   * Contact: Joel Parker Henderson (joel@joelparkerhenderson.com)
///

// Import Selenium WebDriver parts.
import { Browser, Builder, By, until } from 'selenium-webdriver';

// Import chromedriver options that you can set as you wish.
import { Options } from 'selenium-webdriver/chrome.js';
const options = new Options();
options.addArguments('--verbose'); // Enable verbose logging.
options.addArguments('--disable-notifications'); // Disable notifications such as popups.
options.setUserPreferences({ "profile.default_content_setting_values.cookies": 2 }); // Reject cookies.

// Import strict assert, renamed for convenience as assert.
// We use this to verify each step actually did what we expect.
import { strict as assert } from 'assert';

async function demo() {

    // Initialize the driver. Choose other browsers and options as you wish.
    const driver = await new Builder()
        .forBrowser(Browser.CHROME)
        .setChromeOptions(options)
        .build();

    try {

        ///
        // Step 1: Connect to the NHS Wales home page, then verify the
        // page title is what we expect.
        ///

        await driver.get('https://www.nhs.wales/');

        const homeTitle = await driver.getTitle();
        console.log(`Home page title: "${homeTitle}"`);
        assert.equal(homeTitle, 'Home - NHS Wales');
        console.log('✅ Home page title is correct.');

        ///
        // Step 2: Click the "About Us" link, then verify the response
        // page has the title and headline we expect.
        ///

        await driver.findElement(By.linkText('About Us')).click();

        // Selenium does not auto-wait for navigation the way Playwright
        // does, so wait explicitly for the new page's title before reading
        // it; without this, `getTitle()` can read the still-navigating page.
        await driver.wait(until.titleIs('About Us - NHS Wales'), 10000);

        const aboutTitle = await driver.getTitle();
        console.log(`About Us page title: "${aboutTitle}"`);
        assert.equal(aboutTitle, 'About Us - NHS Wales');
        console.log('✅ About Us page title is correct.');

        const headline = (await driver.findElement(By.css('h1')).getText()).trim();
        console.log(`About Us headline: "${headline}"`);
        assert.equal(headline, 'About Us');
        console.log('✅ About Us headline is correct.');

        ///
        // Step 3: Use the page search box: type "help", click the search
        // button, then verify the response page contains the phrases
        // we expect.
        ///

        await driver.get('https://www.nhs.wales/');
        await driver.findElement(By.id('navKeywords')).sendKeys('help');
        await driver.findElement(By.id('button-addon')).click();

        // Wait for the results page to finish navigating, then for its
        // "Your search for ..." summary line, which renders slightly after
        // the heading (a second client-side render pass).
        await driver.wait(until.titleIs('Search results - NHS Wales'), 10000);
        const bodyElement = await driver.findElement(By.css('body'));
        await driver.wait(until.elementTextContains(bodyElement, 'Your search for'), 10000);

        const bodyText = await bodyElement.getText();
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
        await driver.quit();
    }

}

demo().catch((err) => {
    console.error(err);
    process.exitCode = 1;
});
