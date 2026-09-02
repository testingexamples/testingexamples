#!/usr/bin/env python3

"""
Demo of Selenium WebDriver browser automation with Python, using the
NHS Wales website <https://www.nhs.wales/> as a friendly real-world example.

This is a beginner-friendly walkthrough: each step is commented so you can
follow along and adapt the pattern to your own site.

Please see the file README.md for more information.

## Tracking

  * Package: demo-selenium-python-for-nhs-wales
  * Version: 1.0.0
  * Created: 2026-09-02T00:00:00Z
  * Updated: 2026-09-02T00:00:00Z
  * License: GPL-2.0-or-greater or for custom license contact us
  * Contact: Joel Parker Henderson (joel@joelparkerhenderson.com)
"""

import sys

# Import Selenium WebDriver parts.
from selenium import webdriver
from selenium.webdriver.common.by import By

# Import chromedriver options that you can set as you wish.
from selenium.webdriver.chrome.options import Options


def demo() -> None:
    """Drive a real browser to the NHS Wales website and verify a few things
    a visitor might do."""

    options = Options()
    options.add_argument('--verbose')  # Enable verbose logging.
    options.add_argument('--disable-notifications')  # Disable notifications such as popups.
    options.add_experimental_option(
        "prefs",
        {"profile.default_content_setting_values.cookies": 2},  # Reject cookies.
    )

    # Initialize the driver. Choose other browsers as you wish.
    driver = webdriver.Chrome(options=options)

    try:

        ###
        # Step 1: Connect to the NHS Wales home page, then verify the
        # page title is what we expect.
        ###

        driver.get('https://www.nhs.wales/')

        home_title = driver.title
        print(f'Home page title: "{home_title}"')
        assert home_title == 'Home - NHS Wales', \
            f'Expected home page title to be "Home - NHS Wales", got "{home_title}"'
        print('✅ Home page title is correct.')

        ###
        # Step 2: Click the "About Us" link, then verify the response
        # page has the title and headline we expect.
        ###

        driver.find_element(By.LINK_TEXT, 'About Us').click()

        about_title = driver.title
        print(f'About Us page title: "{about_title}"')
        assert about_title == 'About Us - NHS Wales', \
            f'Expected About Us page title to be "About Us - NHS Wales", got "{about_title}"'
        print('✅ About Us page title is correct.')

        headline = driver.find_element(By.TAG_NAME, 'h1').text.strip()
        print(f'About Us headline: "{headline}"')
        assert headline == 'About Us', \
            f'Expected About Us headline to be "About Us", got "{headline}"'
        print('✅ About Us headline is correct.')

        ###
        # Step 3: Use the page search box: type "help", click the search
        # button, then verify the response page contains the phrases
        # we expect.
        ###

        driver.get('https://www.nhs.wales/')
        driver.find_element(By.ID, 'navKeywords').send_keys('help')
        driver.find_element(By.ID, 'button-addon').click()

        body_text = driver.find_element(By.TAG_NAME, 'body').text
        assert 'Search Results' in body_text, \
            'Expected page to contain "Search Results"'
        print('✅ Search results page contains "Search Results".')
        assert 'Your search for "help"' in body_text, \
            'Expected page to contain \'Your search for "help"\''
        print('✅ Search results page contains \'Your search for "help"\'.')

        print('\nAll checks passed. 🎉')

    finally:
        driver.quit()


def main() -> None:
    """Main entry point with error handling."""
    try:
        demo()
    except Exception as err:
        print(f'Fatal error: {err}', file=sys.stderr)
        sys.exit(1)


if __name__ == '__main__':
    main()
