# Demo Selenium Rust for NHS Wales

A friendly, step-by-step tutorial that demonstrates:

* [Selenium](https://www.selenium.dev/) browser automation testing
* [Rust](https://www.rust-lang.org/) programming language
* [Cargo](https://doc.rust-lang.org/cargo/) build tool and package manager
* [ChromeDriver](https://developer.chrome.com/docs/chromedriver) extends WebDriver by adding Chromium-specific capabilities
* Real-world testing against the [NHS Wales](https://www.nhs.wales/) website

There is no official Rust binding from the Selenium project itself.
[`thirtyfour`](https://crates.io/crates/thirtyfour) — whose name nods to
selenium's atomic number, 34 — is the de facto Selenium/WebDriver client
for Rust, and is what this demo uses.

This demo is meant to be read top to bottom like a guide: open `src/main.rs`
alongside this README and follow along.

The exact test scenario is specified in [`spec/index.md`](spec/index.md); the code and the spec must always agree.

## What this demo tests

The program drives a real browser to the NHS Wales website and checks a
few things a visitor might do:

1. **Visit the home page** and verify the page title is `Home - NHS Wales`.
2. **Click the "About Us" link** and verify the resulting page has the title
   `About Us - NHS Wales` and a headline that reads `About Us`.
3. **Use the search box**: type `help`, click the search button, and verify
   the results page mentions `Search Results` and `Your search for "help"`.

Each step prints what it found, then asserts it matches what we expect, so
you can see the demo succeed (or fail loudly, via a panic) as it runs.

## Install

### Install Rust and Cargo

Install Rust (which includes Cargo) from <https://www.rust-lang.org/tools/install>,
typically via `rustup`.

Run this to confirm your version:

```sh
rustc --version
```

Output should be at least:

```stdout
rustc 1.88.0
```

Run this to confirm your version:

```sh
cargo --version
```

### Install dependencies

```sh
cargo build
```

This downloads and compiles `thirtyfour` and the other dependencies listed
in [Cargo.toml](Cargo.toml).

### Install chromedriver

Unlike Playwright, Selenium/`thirtyfour` does not bundle or manage browser
binaries for you: you need a WebDriver server already running.

If you use macOS brew:

```sh
brew install chromedriver
```

Or download the version that matches your Chrome browser app from
<https://chromedriver.chromium.org/downloads> or, for newer Chrome
versions, <https://googlechromelabs.github.io/chrome-for-testing/>.

### Update

Run:

```sh
cargo update
```

## Run

Start `chromedriver` listening on port 9515 (the port this demo connects
to):

```sh
chromedriver --port=9515
```

Then, in another terminal, run:

```sh
cargo run
```

The program will:

1. Launch your local Chrome web browser (via `chromedriver`) and go to
   <https://www.nhs.wales/>.
2. Click around and use the search box, the same way a real visitor would.
3. Print a checklist of ✅ verifications as it confirms each expectation,
   then print "All checks passed. 🎉" when everything succeeds.

### Troubleshooting "This version of ChromeDriver …"

If you get an error message about a Chrome/chromedriver version mismatch,
harmonize your Chrome browser app and your `chromedriver` version (e.g.
`brew upgrade chromedriver` on macOS, or download a matching version from
the links above).

## Tracking

* Package: demo-selenium-rust-for-nhs-wales
* Version: 1.0.0
* Created: 2026-09-03T00:00:00Z
* Updated: 2026-09-03T00:00:00Z
* License: GPL-2.0-or-greater or for custom license contact us
* Contact: Joel Parker Henderson (joel@joelparkerhenderson.com)
