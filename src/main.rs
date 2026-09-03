//! Demo of Playwright browser automation with Rust, using the NHS Wales
//! website <https://www.nhs.wales/> as a friendly real-world example.
//!
//! This is a beginner-friendly walkthrough: each step is commented so you
//! can follow along and adapt the pattern to your own site. Ported from
//! the sibling `demo-playwright-javascript-for-nhs-wales` demo to the
//! `playwright-rs` crate.
//!
//! Please see the file README.md for more information.
//!
//! ## Tracking
//!
//!   * Package: demo-playwright-rust-for-nhs-wales
//!   * Version: 1.0.0
//!   * Created: 2026-09-03T00:00:00Z
//!   * Updated: 2026-09-03T00:00:00Z
//!   * License: GPL-2.0-or-greater or for custom license contact us
//!   * Contact: Joel Parker Henderson (joel@joelparkerhenderson.com)

use playwright_rs::{AriaRole, GetByRoleOptions, Page, Playwright, WaitUntil};

#[tokio::main]
async fn main() -> anyhow::Result<()> {
    // Launch a browser and open a page.
    let pw = Playwright::launch().await?;
    let browser = pw.chromium().launch().await?;
    let page = browser.new_page().await?;

    // Run the three checks, but always close the browser afterward, even
    // if an assertion fails — mirroring the try/catch/finally shape of the
    // JavaScript and Python siblings.
    let result = run_demo(&page).await;

    if let Err(ref err) = result {
        eprintln!("{err:?}");
    }

    browser.close().await?;
    result
}

async fn run_demo(page: &Page) -> anyhow::Result<()> {
    // ---
    // Step 1: Connect to the NHS Wales home page, then verify the page
    // title is what we expect.
    // ---

    page.goto("https://www.nhs.wales/", None).await?;

    let home_title = page.title().await?;
    println!("Home page title: \"{home_title}\"");
    assert_eq!(home_title, "Home - NHS Wales");
    println!("✅ Home page title is correct.");

    // ---
    // Step 2: Click the "About Us" link, then verify the response page
    // has the title and headline we expect.
    // ---

    page.get_by_role(
        AriaRole::Link,
        Some(GetByRoleOptions::default().name("About Us").exact(true)),
    )
    .first()
    .click(None)
    .await?;
    page.wait_for_load_state(Some(WaitUntil::Load)).await?;

    let about_title = page.title().await?;
    println!("About Us page title: \"{about_title}\"");
    assert_eq!(about_title, "About Us - NHS Wales");
    println!("✅ About Us page title is correct.");

    let headline = page.locator("h1").first().inner_text().await?;
    let headline = headline.trim();
    println!("About Us headline: \"{headline}\"");
    assert_eq!(headline, "About Us");
    println!("✅ About Us headline is correct.");

    // ---
    // Step 3: Use the page search box: type "help", click the search
    // button, then verify the response page contains the phrases we
    // expect.
    // ---

    page.goto("https://www.nhs.wales/", None).await?;
    page.locator("#navKeywords").fill("help", None).await?;
    page.locator("#button-addon").click(None).await?;
    page.wait_for_load_state(Some(WaitUntil::Load)).await?;

    let body_text = page.locator("body").inner_text().await?;
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
