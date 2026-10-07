# Demo Selenium Rust for Google Search

> **Read this before running anything.** Google's [Terms of Service](https://www.google.com/policies/terms/)
> restrict automated querying of Google Search. The tests in this repo
> exist to show the syntax and interaction *pattern* of Selenium's Rust
> binding — they are not meant to be run repeatedly, or at all, against
> the live `google.com`. This repo's own history never runs `cargo build`,
> `cargo check`, or `cargo test` against it. If you want to practise these
> same patterns hands-on, point a similar script at
> [testingexamples.github.io](https://testingexamples.github.io/en-001/practice/) instead
> (see the sibling repo `demo-selenium-rust`), which was built exactly for
> that: stable ids, names, classes, and text that don't shift under you.

Demonstration of:

* [Selenium](https://www.selenium.dev/) browser automation testing
* [Rust](https://www.rust-lang.org/) programming language
* [Cargo](https://doc.rust-lang.org/cargo/) build tool and package manager
* [ChromeDriver](https://developer.chrome.com/docs/chromedriver) extends WebDriver by adding Chromium-specific capabilities

There is no official Rust binding from the Selenium project itself.
[`thirtyfour`](https://crates.io/crates/thirtyfour) — whose name nods to
selenium's atomic number, 34 — is the de facto Selenium/WebDriver client
for Rust, and is what this demo uses.

The exact scenario this demo describes (target URL, selectors, assertions)
is specified in [spec/index.md](spec/index.md); the code and spec must
agree.

## What this demo tests

Unlike the plain `demo-selenium-rust` walkthrough (which only logs what it
finds), this repo demonstrates a REAL test with real assertions — matching
the sibling `demo-selenium-javascript-for-google-search` and
`demo-selenium-python-for-google-search` repos test-for-test:

1. **Home page title test**: the Google Search home page title is exactly
   `Google`.
2. **Search test**: filling the search box (`textarea[name="q"]`) with a
   query and pressing Enter leads to a results page whose title contains
   that query.
3. **Click-through test**: clicking the first organic result
   (`#search a`) navigates away to a different hostname than
   `www.google.com`.

## Install

### Install Rust and Cargo

Install Rust (which includes Cargo) from <https://www.rust-lang.org/tools/install>,
typically via `rustup`.

```sh
rustc --version
cargo --version
```

### Dependencies

This repo's [Cargo.toml](Cargo.toml) declares `thirtyfour`, `tokio`, and
`anyhow` as ordinary dependencies, the same as any other Selenium Rust
project — so the code reads and would build like normal Rust. But per the
caution above, this repo does not actually run `cargo build` or
`cargo test` against the live site as part of its own maintenance.

## Run

Do not run this against the live `google.com`. If you have adapted this
pattern to point at a site you're allowed to test, the usual commands
apply (with a WebDriver server such as `chromedriver` already running):

```sh
cargo build
cargo test
```

## Tracking

* Package: demo-selenium-rust-for-google-search
* Version: 1.0.0
* Created: 2026-09-03T00:00:00Z
* Updated: 2026-09-03T00:00:00Z
* License: GPL-2.0-or-greater or for custom license contact us
* Contact: Joel Parker Henderson (joel@joelparkerhenderson.com)
