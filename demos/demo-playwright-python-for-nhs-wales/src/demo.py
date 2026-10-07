#!/usr/bin/env python3

"""
Demo of Playwright browser automation with Python, using the
NHS Wales website <https://www.nhs.wales/> as a friendly real-world example.

This is a beginner-friendly walkthrough: each step is commented so you can
follow along and adapt the pattern to your own site.

Please see the file README.md for more information.

## Tracking

  * Package: demo-playwright-python-for-nhs-wales
  * Version: 1.0.0
  * Created: 2026-09-02T00:00:00Z
  * Updated: 2026-09-02T00:00:00Z
  * License: GPL-2.0-or-greater or for custom license contact us
  * Contact: Joel Parker Henderson (joel@joelparkerhenderson.com)
"""

# Import Playwright's sync API.
from playwright.sync_api import sync_playwright, Browser, BrowserContext, Page

import sys
import traceback


def demo() -> None:
    """Drive a real browser through the NHS Wales website and verify it."""

    with sync_playwright() as p:

        # Launch a browser. Set headless to True if you don't want to watch it.
        browser: Browser = p.chromium.launch(
            headless=False,
        )

        context: BrowserContext = browser.new_context()
        page: Page = context.new_page()

        try:

            ###
            # Step 1: Connect to the NHS Wales home page, then verify the
            # page title is what we expect.
            ###

            page.goto("https://www.nhs.wales/")

            home_title = page.title()
            print(f'Home page title: "{home_title}"')
            assert home_title == "Home - NHS Wales", (
                f'Expected home page title to be "Home - NHS Wales", '
                f'got "{home_title}"'
            )
            print("✅ Home page title is correct.")

            ###
            # Step 2: Click the "About Us" link, then verify the response
            # page has the title and headline we expect.
            ###

            page.get_by_role("menuitem", name="About Us", exact=True).first.click()
            page.wait_for_load_state("load")

            about_title = page.title()
            print(f'About Us page title: "{about_title}"')
            assert about_title == "About Us - NHS Wales", (
                f'Expected About Us page title to be "About Us - NHS Wales", '
                f'got "{about_title}"'
            )
            print("✅ About Us page title is correct.")

            headline = page.locator("h1").first.inner_text().strip()
            print(f'About Us headline: "{headline}"')
            assert headline == "About Us", (
                f'Expected About Us headline to be "About Us", got "{headline}"'
            )
            print("✅ About Us headline is correct.")

            ###
            # Step 3: Use the page search box: type "help", click the search
            # button, then verify the response page contains the phrases
            # we expect.
            ###

            page.goto("https://www.nhs.wales/")
            page.locator("#navKeywords").fill("help")
            page.locator("#button-addon").click()
            page.wait_for_load_state("load")

            body_text = page.locator("body").inner_text()
            assert "Search Results" in body_text, (
                'Expected page to contain "Search Results"'
            )
            print('✅ Search results page contains "Search Results".')
            assert 'Your search for "help"' in body_text, (
                'Expected page to contain \'Your search for "help"\''
            )
            print('✅ Search results page contains \'Your search for "help"\'.')

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
