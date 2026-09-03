# Demo Playwright Rust for NHS Wales

A friendly, step-by-step tutorial that demonstrates:

* [Playwright](https://www.playwright.dev/) browser automation testing
* [Rust](https://www.rust-lang.org/) programming language
* [Cargo](https://doc.rust-lang.org/cargo/) build tool and package manager
* [Chromium](https://www.chromium.org/) open source web browser
* Real-world testing against the [NHS Wales](https://www.nhs.wales/) website

Playwright ships official bindings for JavaScript, Python, .NET, and Java.
Rust is community-maintained. This demo uses [`playwright-rs`](https://crates.io/crates/playwright-rs)
(`padamson/playwright-rust`), which is actively maintained but still
pre-1.0 and stabilising its API. Be careful which crate you install: an
older, unrelated crate published on crates.io simply as `playwright`
(`octaltree/playwright-rust`) has been abandoned since 2022 — don't reach
for that one.

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

This downloads and compiles `playwright-rs` and the other dependencies
listed in [Cargo.toml](Cargo.toml).

### Install the Playwright browser binaries

`playwright-rs` needs Playwright's own browser binaries, the same way the
JavaScript and Python bindings do. Consult the crate's documentation for
the current install command (typically a `playwright install` style
step driven by the crate's own CLI helper).

### Update

Run:

```sh
cargo update
```

## Run

Run:

```sh
cargo run
```

The program will:

1. Launch your local Chrome/Chromium web browser and go to
   <https://www.nhs.wales/>.
2. Click around and use the search box, the same way a real visitor would.
3. Print a checklist of ✅ verifications as it confirms each expectation,
   then print "All checks passed. 🎉" when everything succeeds.

## Tracking

* Package: demo-playwright-rust-for-nhs-wales
* Version: 1.0.0
* Created: 2026-09-03T00:00:00Z
* Updated: 2026-09-03T00:00:00Z
* License: GPL-2.0-or-greater or for custom license contact us
* Contact: Joel Parker Henderson (joel@joelparkerhenderson.com)
