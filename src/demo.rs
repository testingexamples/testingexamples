//! Demo of Selenium browser automation with Rust, against Google Search.
//!
//! CAUTION — read AGENTS.md and README.md before touching this file:
//! Google's Terms of Service restrict automated querying of Google
//! Search. These tests exist to show the syntax and interaction pattern
//! of `thirtyfour`, matching the sibling JavaScript/Python/TypeScript
//! `-for-google-search` demos test-for-test. They are not meant to be run
//! repeatedly, or at all, against the live `google.com`. Do not add a CI
//! job, pre-commit hook, or anything else that executes `cargo test` in
//! this repo against the real site.
//!
//! Unlike the plain `demo-selenium-rust` walkthrough (which only logs
//! what it finds), this repo demonstrates a REAL test with real
//! assertions, matching the sibling `-for-google-search` repos.
//!
//! ## Tracking
//!
//!   * Package: demo-selenium-rust-for-google-search
//!   * Version: 1.0.0
//!   * Created: 2026-09-03T00:00:00Z
//!   * Updated: 2026-09-03T00:00:00Z
//!   * License: GPL-2.0-or-greater or for custom license contact us
//!   * Contact: Joel Parker Henderson (joel@joelparkerhenderson.com)

#[cfg(test)]
mod tests {
    use thirtyfour::prelude::*;
    use url::Url;

    /// Test 1: the Google Search home page title is exactly "Google".
    #[tokio::test]
    async fn google_search_home_page_title_is_google() -> anyhow::Result<()> {
        let caps = DesiredCapabilities::chrome();
        let driver = WebDriver::new("http://localhost:9515", caps).await?;

        // 1. Browse to the site.
        driver.goto("https://www.google.com").await?;

        // Assert the home page title.
        let title = driver.title().await?;
        assert_eq!(title, "Google");

        driver.quit().await?;
        Ok(())
    }

    /// Test 2: searching for a query navigates to a results page whose
    /// title contains that query.
    #[tokio::test]
    async fn google_search_result_title_contains_query() -> anyhow::Result<()> {
        let caps = DesiredCapabilities::chrome();
        let driver = WebDriver::new("http://localhost:9515", caps).await?;

        driver.goto("https://www.google.com").await?;

        // 2. Use the search box.
        // Google's search input has drifted between <input> and
        // <textarea> over the years, but is currently a <textarea>
        // carrying name="q".
        let query = "testing examples";
        let search_box = driver
            .query(By::Css("textarea[name='q']"))
            .desc("Google search box")
            .single()
            .await?;

        // 3. Submit by sending the WebDriver "Enter" key (\u{E007})
        // appended to the query — more reliable than locating the submit
        // button, which autocomplete suggestions can obscure.
        search_box.send_keys(format!("{query}\u{E007}")).await?;

        // Assert the results page title contains the query.
        let result_title = driver.title().await?;
        assert!(
            result_title.contains(query),
            "Expected results page title {result_title:?} to contain {query:?}"
        );

        driver.quit().await?;
        Ok(())
    }

    /// Test 3: clicking the first organic result navigates away from
    /// google.com to a different hostname.
    #[tokio::test]
    async fn google_search_click_first_result_changes_hostname() -> anyhow::Result<()> {
        let caps = DesiredCapabilities::chrome();
        let driver = WebDriver::new("http://localhost:9515", caps).await?;

        driver.goto("https://www.google.com").await?;

        let search_box = driver
            .query(By::Css("textarea[name='q']"))
            .desc("Google search box")
            .single()
            .await?;
        search_box
            .send_keys(format!("testing examples\u{E007}"))
            .await?;

        // 4. Follow a link: click the first organic result. Google wraps
        // its organic results in a container with id="search"; the first
        // <a> inside it is the first result's link.
        let first_result = driver
            .query(By::Css("#search a"))
            .desc("first organic result link")
            .first()
            .await?;
        first_result.click().await?;

        // Assert the hostname changed away from google.com.
        let landing_url: Url = driver.current_url().await?;
        let landing_host = landing_url.host_str().unwrap_or_default();
        assert_ne!(
            landing_host, "www.google.com",
            "Expected clicking the first result to navigate away from google.com"
        );

        driver.quit().await?;
        Ok(())
    }
}
