//! Demo of Selenium WebDriver browser automation with Rust, using the NHS
//! Wales website <https://www.nhs.wales/> as a friendly real-world
//! example.
//!
//! This is a beginner-friendly walkthrough: each step is commented so you
//! can follow along and adapt the pattern to your own site. Ported from
//! the sibling `demo-selenium-javascript-for-nhs-wales` demo to the
//! `thirtyfour` crate.
//!
//! Please see the file README.md for more information.
//!
//! ## Tracking
//!
//!   * Package: demo-selenium-rust-for-nhs-wales
//!   * Version: 1.0.0
//!   * Created: 2026-09-03T00:00:00Z
//!   * Updated: 2026-09-03T00:00:00Z
//!   * License: GPL-2.0-or-greater or for custom license contact us
//!   * Contact: Joel Parker Henderson (joel@joelparkerhenderson.com)

use thirtyfour::prelude::*;

#[tokio::main]
async fn main() -> anyhow::Result<()> {
    // Connect to a WebDriver server (e.g. chromedriver listening on
    // 9515, started separately — see README.md) and open a session.
    let caps = DesiredCapabilities::chrome();
    let driver = WebDriver::new("http://localhost:9515", caps).await?;

    // Run the three checks, but always quit the session afterward, even
    // if an assertion fails — mirroring the try/catch/finally shape of
    // the JavaScript and Python siblings.
    let result = run_demo(&driver).await;

    if let Err(ref err) = result {
        eprintln!("{err:?}");
    }

    driver.quit().await?;
    result
}

async fn run_demo(driver: &WebDriver) -> anyhow::Result<()> {
    // ---
    // Step 1: Connect to the NHS Wales home page, then verify the page
    // title is what we expect.
    // ---

    driver.goto("https://www.nhs.wales/").await?;

    let home_title = driver.title().await?;
    println!("Home page title: \"{home_title}\"");
    assert_eq!(home_title, "Home - NHS Wales");
    println!("✅ Home page title is correct.");

    // ---
    // Step 2: Click the "About Us" link, then verify the response page
    // has the title and headline we expect.
    // ---

    driver
        .query(By::LinkText("About Us"))
        .desc("\"About Us\" link")
        .single()
        .await?
        .click()
        .await?;

    let about_title = driver.title().await?;
    println!("About Us page title: \"{about_title}\"");
    assert_eq!(about_title, "About Us - NHS Wales");
    println!("✅ About Us page title is correct.");

    let headline = driver
        .query(By::Css("h1"))
        .desc("page headline")
        .first()
        .await?
        .text()
        .await?;
    let headline = headline.trim();
    println!("About Us headline: \"{headline}\"");
    assert_eq!(headline, "About Us");
    println!("✅ About Us headline is correct.");

    // ---
    // Step 3: Use the page search box: type "help", click the search
    // button, then verify the response page contains the phrases we
    // expect.
    // ---

    driver.goto("https://www.nhs.wales/").await?;
    driver
        .query(By::Id("navKeywords"))
        .desc("search input")
        .single()
        .await?
        .send_keys("help")
        .await?;
    driver
        .query(By::Id("button-addon"))
        .desc("search button")
        .single()
        .await?
        .click()
        .await?;

    let body_text = driver
        .query(By::Css("body"))
        .desc("page body")
        .single()
        .await?
        .text()
        .await?;
    assert!(
        body_text.contains("Search Results"),
        "Expected page to contain \"Search Results\""
    );
    println!("✅ Search results page contains \"Search Results\".");
    assert!(
        body_text.contains("Your search for \"help\""),
        "Expected page to contain 'Your search for \"help\"'"
    );
    println!("✅ Search results page contains 'Your search for \"help\"'.");

    println!("\nAll checks passed. 🎉");

    Ok(())
}
