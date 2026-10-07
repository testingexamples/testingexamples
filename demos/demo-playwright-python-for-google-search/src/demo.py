#!/usr/bin/env python3

"""
Demo of Playwright browser automation with Python, using Google Search
<https://www.google.com> as an example of testing against real-world,
third-party markup that the test author does not control.

This is a beginner-friendly walkthrough: each step is commented so you can
follow along and adapt the pattern to your own site.

CAUTION: Google's Terms of Service restrict automated querying of Google
Search. This script exists to show the syntax and interaction pattern, not
to be run repeatedly, or at all, against the live google.com. See README.md
for details before you run this.

Please see the file README.md for more information.

## Tracking

  * Package: demo-playwright-python-for-google-search
  * Version: 1.0.0
  * Created: 2026-09-03T00:00:00Z
  * Updated: 2026-09-03T00:00:00Z
  * License: GPL-2.0-or-greater or for custom license contact us
  * Contact: Joel Parker Henderson (joel@joelparkerhenderson.com)
"""

# Import Playwright's sync API.
from playwright.sync_api import sync_playwright, Browser, BrowserContext, Page
from urllib.parse import urlparse

import sys
import traceback


def demo() -> None:
    """Drive a real browser through Google Search and verify it."""

    with sync_playwright() as p:

        # Launch a browser. Set headless to True if you don't want to watch it.
        browser: Browser = p.chromium.launch(
            headless=False,
        )

        context: BrowserContext = browser.new_context()
        page: Page = context.new_page()

        try:

            ###
            # Step 1: Connect to the Google home page, then verify the
            # page title is what we expect.
            ###

            page.goto("https://www.google.com")

            home_title = page.title()
            print(f'Home page title: "{home_title}"')
            assert home_title == "Google", (
                f'Expected home page title to be "Google", got "{home_title}"'
            )
            print("✅ Home page title is correct.")

            ###
            # Step 2: Use the search box: type "testing examples", press
            # Enter, then verify the resulting page title contains our
            # search term.
            #
            # Google's search input is reachable via `textarea[name="q"]`
            # as of this writing. Historically it was a plain
            # `<input name="q">`; Google has since swapped it for an
            # auto-growing `<textarea>` with the same name attribute. Noting
            # that drift here honestly, since it is exactly the kind of
            # third-party markup change this demo warns you about.
            ###

            search_box = page.locator('textarea[name="q"]')
            search_box.fill("testing examples")
            search_box.press("Enter")
            page.wait_for_load_state("load")

            results_title = page.title()
            print(f'Search results page title: "{results_title}"')
            assert "testing examples" in results_title, (
                f'Expected search results page title to contain '
                f'"testing examples", got "{results_title}"'
            )
            print('✅ Search results page title contains "testing examples".')

            ###
            # Step 3: Click the first organic result link, then verify the
            # browser navigated away from google.com.
            #
            # `#search a` targets the first link inside Google's results
            # container. The exact structure of a Google results page shifts
            # over time (Google regularly changes wrapper divs, classes, and
            # ids), so treat this selector as illustrative rather than
            # guaranteed to remain accurate.
            ###

            first_result = page.locator("#search a").first
            first_result.click()
            page.wait_for_load_state("load")

            landed_url = page.url
            landed_host = urlparse(landed_url).hostname
            print(f'Landed on URL: "{landed_url}" (host: "{landed_host}")')
            assert landed_host != "www.google.com", (
                f'Expected to navigate away from www.google.com, '
                f'but landed host was "{landed_host}"'
            )
            print("✅ Browser navigated away from google.com.")

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
