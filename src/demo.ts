#!/usr/bin/env ts-node

///
// Demo of Playwright browser automation with TypeScript, targeting the
// syntax and interaction patterns for Google Maps.
//
// IMPORTANT: Google's Terms of Service restrict automated querying of
// Google Maps. This file exists to show the syntax and interaction
// patterns of Playwright's TypeScript API — matching the framing already
// established at https://testingexamples.github.io/examples/google-maps/
// — it is NOT meant to be run repeatedly, or at all, against the live
// google.com. Do not run this in CI or any automated tooling. See
// README.md and AGENTS.md for the full caution.
//
// Google Maps renders primarily to <canvas>/WebGL, so most interactions
// go through the accessible UI chrome (search box, zoom buttons), which
// carries stable aria-label attributes, rather than through DOM
// selectors into the map surface itself.
//
// Please see the file README.md for more information.
//
// ## Tracking
//
//   * Package: demo-playwright-typescript-for-google-maps
//   * Version: 1.0.0
//   * Created: 2026-09-03T00:00:00Z
//   * Updated: 2026-09-03T00:00:00Z
//   * License: GPL-2.0-or-greater or for custom license contact us
//   * Contact: Joel Parker Henderson (joel@joelparkerhenderson.com)
///

// Import Playwright types and functions.
import { chromium, Browser, BrowserContext, Page, Locator } from 'playwright';

// Import strict assert, renamed for convenience as assert.
import { strict as assert } from 'assert';

// A parsed `@lat,lng,zoomz` fragment from a Google Maps URL, e.g. the
// `@51.4816,-3.1791,15z` in
// `https://www.google.com/maps/place/.../@51.4816,-3.1791,15z/...`.
interface MapView {
    lat: number;
    lng: number;
    zoom: number;
}

// Parse the `@lat,lng,zoomz` view fragment out of a Google Maps URL.
function parseMapView(url: string): MapView | null {
    const match = url.match(/@(-?\d+\.?\d*),(-?\d+\.?\d*),(\d+\.?\d*)z/);
    if (!match) {
        return null;
    }
    const [, lat, lng, zoom] = match;
    return { lat: Number(lat), lng: Number(lng), zoom: Number(zoom) };
}

async function demo(): Promise<void> {

    const browser: Browser = await chromium.launch({
        headless: false,
    });

    const context: BrowserContext = await browser.newContext();
    const page: Page = await context.newPage();

    try {

        ///
        // Check 1: Visit Google Maps and verify the page title contains
        // "Google Maps".
        ///

        await page.goto('https://www.google.com/maps');

        const title: string = await page.title();
        console.log(`Page title: "${title}"`);
        assert.ok(title.includes('Google Maps'), 'Expected page title to contain "Google Maps"');
        console.log('✅ Page title contains "Google Maps".');

        ///
        // Check 2: Use the search box and verify the URL contains the
        // query.
        //
        // Google Maps' accessible UI chrome (search box, zoom buttons,
        // layers menu) carries stable aria-label attributes, which are
        // preferred here over unstable class-name selectors into the
        // canvas/WebGL map surface.
        ///

        const query: string = 'Cardiff Castle';
        const searchBox: Locator = page.locator('[aria-label="Search Google Maps"]');
        await searchBox.fill(query);
        await page.keyboard.press('Enter');
        await page.waitForLoadState('load');

        const urlAfterSearch: string = page.url();
        console.log(`URL after search: "${urlAfterSearch}"`);
        assert.ok(
            urlAfterSearch.toLowerCase().includes(query.toLowerCase().replace(/\s+/g, '+')) ||
                urlAfterSearch.toLowerCase().includes(query.toLowerCase()),
            `Expected URL to contain "${query}"`
        );
        console.log(`✅ URL contains "${query}".`);

        ///
        // Check 3: Zoom in and verify the URL's embedded `@lat,lng,zoomz`
        // zoom parameter increased.
        ///

        const viewBeforeZoom: MapView | null = parseMapView(page.url());
        console.log(`Map view before zoom: ${JSON.stringify(viewBeforeZoom)}`);
        assert.ok(viewBeforeZoom !== null, 'Expected URL to contain an @lat,lng,zoomz fragment before zooming');

        const zoomInButton: Locator = page.locator('[aria-label="Zoom in"]');
        await zoomInButton.click();
        await page.waitForTimeout(500);

        const viewAfterZoom: MapView | null = parseMapView(page.url());
        console.log(`Map view after zoom: ${JSON.stringify(viewAfterZoom)}`);
        assert.ok(viewAfterZoom !== null, 'Expected URL to contain an @lat,lng,zoomz fragment after zooming');
        assert.ok(
            (viewAfterZoom as MapView).zoom > (viewBeforeZoom as MapView).zoom,
            'Expected zoom level to increase after clicking "Zoom in"'
        );
        console.log(
            `✅ Zoom level increased from ${(viewBeforeZoom as MapView).zoom} to ${(viewAfterZoom as MapView).zoom}.`
        );

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

// Non-negotiable: do not call demo() automatically in CI or any automated
// tooling. This file must never be executed against live google.com. See
// AGENTS.md.
demo().catch((err: Error): void => {
    console.error(err);
    process.exitCode = 1;
});
