# Demo Playwright Java for NHS Wales

A friendly, step-by-step tutorial that demonstrates:

* [Playwright](https://www.playwright.dev/) browser automation testing
* [Java](https://www.java.com/) programming language
* [Chromium](https://www.chromium.org/) open source web browser
* Real-world testing against the [NHS Wales](https://www.nhs.wales/) website

This demo is meant to be read top to bottom like a guide: open `src/main/java/demo/Demo.java`
alongside this README and follow along.

The exact test scenario is specified in [`spec/index.md`](spec/index.md); the code and the spec must always agree.

## What this demo tests

The script drives a real browser to the NHS Wales website and checks a few
things a visitor might do:

1. **Visit the home page** and verify the page title is `Home - NHS Wales`.
2. **Click the "About Us" link** and verify the resulting page has the title
   `About Us - NHS Wales` and a headline that reads `About Us`.
3. **Use the search box**: type `help`, click the search button, and verify
   the results page mentions `Search Results` and `Your search for "help"`.

Each step prints what it found, then asserts it matches what we expect, so
you can see the demo succeed (or fail loudly) as it runs.

## Install

### Install Java and Maven

Install a JDK (17 or newer) and [Maven](https://maven.apache.org/install.html).
On macOS with brew:

```sh
brew install openjdk maven
```

Confirm:

```sh
java -version
mvn -version
```

### Install dependencies

```sh
mvn compile
```

This downloads Playwright and the other dependencies listed in [pom.xml](pom.xml).

### Install browsers

Playwright manages its own browser binaries. Install them with:

```sh
mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="install chromium"
```

Playwright for Java also downloads missing browsers automatically the first time `Playwright.create()` runs.

## Run

```sh
mvn compile exec:java
```

The program will:

1. Launch your local Chrome web browser and go to <https://www.nhs.wales/>.
2. Click around and use the search box, the same way a real visitor would.
3. Print a checklist of ✅ verifications as it confirms each expectation,
   then print "All checks passed. 🎉" when everything succeeds.

### Troubleshooting "Executable doesn't exist"

The Playwright browsers are not installed yet; run the browser install command above.

## Tracking

* Package: demo-playwright-java-for-nhs-wales
* Version: 1.0.0
* Created: 2026-10-07T00:00:00Z
* Updated: 2026-10-07T00:00:00Z
* License: GPL-2.0-or-greater or for custom license contact us
* Contact: Joel Parker Henderson (joel@joelparkerhenderson.com)
