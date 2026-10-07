# Spec: Demo Selenium Java for NHS Wales

## Summary

This repo is a beginner-friendly walkthrough of Selenium WebDriver browser
automation in Java. `src/main/java/demo/Demo.java` drives a real Chrome browser to the live
production site <https://www.nhs.wales/> and runs three real,
assertion-based checks against the actual page content returned by the site.

## Scope

This spec covers only the three test scenarios implemented in
`src/main/java/demo/Demo.java`. It does not cover unrelated NHS Wales site features, other
browsers, or other pages of the site.

## Principles and rules

* All checks are real assertions run against the live, real, production
  nhs.wales website. There is no mocking, stubbing, or fixture data.
* The demo uses the `selenium` package's Chrome driver directly (via
  `new ChromeDriver(options)`), not a wrapper or test framework.
* Expected strings (titles, link text, selector ids, search term, result
  substrings) must be matched exactly as written below and in the source.
* If the code (`src/main/java/demo/Demo.java`) and this file ever disagree, that is a defect
  in one of them; fix it before doing anything else.

## Detail

1. **Home page title check**
   * Navigate to: `https://www.nhs.wales/`
   * Get the page title (`driver.getTitle()`).
   * Assert the title equals exactly: `Home - NHS Wales`

2. **About Us page check**
   * From the home page, click the link with link text: `About Us`
   * Get the resulting page title (`driver.getTitle()`).
   * Assert the title equals exactly: `About Us - NHS Wales`
   * Get the text of the `h1` element (via `By.tagName("h1")`), stripped.
   * Assert the stripped headline equals exactly: `About Us`

3. **Search results check**
   * Navigate to: `https://www.nhs.wales/`
   * Find the search input by id `navKeywords` and send keys: `help`
   * Find the search button by id `button-addon` and click it.
   * Get the text of the `body` element (via `By.tagName("body")`).
   * Assert the body text contains the substring: `Search Results`
   * Assert the body text contains the exact phrase: `Your search for "help"`

## Acceptance criteria

* All 3 checks above pass when run against the live nhs.wales site.
* As of 2026-09-02, nhs.wales's TLS certificate has been expired since
  2025-08-18. Until it is renewed, running this demo will fail with a
  certificate/driver error unrelated to this code.

## Related topics

* [../README.md](../README.md)
* [../AGENTS.md](../AGENTS.md)

## Sources

* <https://www.nhs.wales/>
