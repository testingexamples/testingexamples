#!/usr/bin/env python3

"""
Demo of Playwright browser automation with Python, using Google Maps
<https://www.google.com/maps> as an example of testing against a
canvas/WebGL-heavy, real-world, third-party application.

This is a beginner-friendly walkthrough: each step is commented so you can
follow along and adapt the pattern to your own site.

CAUTION: Google's Terms of Service restrict automated querying of its
services, including Google Maps. This script exists to show the syntax and
interaction pattern, not to be run repeatedly, or at all, against the live
google.com. See README.md for details before you run this.

Most of Google Maps renders to a `<canvas>`/WebGL surface, so you generally
cannot "find" a street or a pin the way you find a paragraph of text. The
assertions below are deliberately modest: they check the page title, the
URL, and a URL-embedded zoom parameter, rather than anything drawn on the
canvas itself.

Please see the file README.md for more information.

## Tracking

  * Package: demo-playwright-python-for-google-maps
  * Version: 1.0.0
  * Created: 2026-09-03T00:00:00Z
  * Updated: 2026-09-03T00:00:00Z
  * License: GPL-2.0-or-greater or for custom license contact us
  * Contact: Joel Parker Henderson (joel@joelparkerhenderson.com)
"""

# Import Playwright's sync API.
from playwright.sync_api import sync_playwright, Browser, BrowserContext, Page

import re
import sys
import traceback


def extract_zoom(url: str) -> float:
    """
    Extract the zoom level from a Google Maps URL.

    Google Maps encodes the current view as `@lat,lng,zoomz` in the URL
    path, for example:

        https://www.google.com/maps/place/Cardiff+Castle/@51.4816,-3.1815,17z

    The trailing `z` marks the zoom-level number. This helper pulls that
    number out so we can compare it before and after a zoom interaction,
    since we cannot inspect the canvas-rendered map directly.
    """
    match = re.search(r"@[-0-9.]+,[-0-9.]+,([0-9.]+)z", url)
    assert match is not None, (
        f'Expected URL to contain an "@lat,lng,zoomz" segment, got "{url}"'
    )
    return float(match.group(1))


def demo() -> None:
    """Drive a real browser through Google Maps and verify it."""

    with sync_playwright() as p:

        # Launch a browser. Set headless to True if you don't want to watch it.
        browser: Browser = p.chromium.launch(
            headless=False,
        )

        context: BrowserContext = browser.new_context()
        page: Page = context.new_page()

        try:

            ###
            # Step 1: Connect to Google Maps, then verify the page title.
            ###

            page.goto("https://www.google.com/maps")

            home_title = page.title()
            print(f'Maps page title: "{home_title}"')
            assert "Google Maps" in home_title, (
                f'Expected page title to contain "Google Maps", '
                f'got "{home_title}"'
            )
            print('✅ Page title contains "Google Maps".')

            ###
            # Step 2: Use the search box: type "Cardiff Castle", press
            # Enter, then verify the URL updated to reflect the search.
            #
            # `[aria-label="Search Google Maps"]` targets the search input
            # by its accessible name rather than a generated class name.
            # Google Maps' CSS classes are minified and change between
            # deploys; the `aria-label` is part of the accessible-markup
            # contract the UI exposes to assistive technology, so it is far
            # more stable across Google's front-end changes.
            ###

            search_box = page.locator('[aria-label="Search Google Maps"]')
            search_box.fill("Cardiff Castle")
            search_box.press("Enter")
            page.wait_for_load_state("load")

            searched_url = page.url
            print(f'URL after search: "{searched_url}"')
            assert "Cardiff" in searched_url, (
                f'Expected URL to contain "Cardiff", got "{searched_url}"'
            )
            print('✅ URL contains "Cardiff".')

            ###
            # Step 3: Click the zoom-in button, then verify the URL's
            # embedded zoom level increased.
            #
            # Google Maps renders the map itself to canvas/WebGL, so we
            # cannot assert on any pin, label, or street drawn there. What
            # we *can* assert on is the URL: Google Maps keeps the current
            # view synced into the address bar as `@lat,lng,zoomz`, so a
            # successful zoom-in should raise that trailing zoom number.
            ###

            zoom_before = extract_zoom(page.url)
            print(f"Zoom level before: {zoom_before}")

            page.locator('[aria-label="Zoom in"]').click()
            page.wait_for_timeout(1000)  # allow the URL to sync after the animation

            zoom_after = extract_zoom(page.url)
            print(f"Zoom level after: {zoom_after}")
            assert zoom_after > zoom_before, (
                f"Expected zoom level to increase after clicking Zoom in, "
                f"went from {zoom_before} to {zoom_after}"
            )
            print("✅ Zoom level increased after clicking Zoom in.")

            print("\nAll checks passed. 🎉")

        except Exception as err:
            print(err)
            print(traceback.format_exc())
            sys.exit(1)

        finally:
            browser.close()


def main() -> None:
    """Main entry point with error handling."""
    try:
        demo()
    except SystemExit:
        raise
    except Exception as err:
        print(f"Fatal error: {err}", file=sys.stderr)
        sys.exit(1)


if __name__ == "__main__":
    main()
