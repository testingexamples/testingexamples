# Demo Selenium Java for NHS Wales

A friendly, step-by-step tutorial that demonstrates:

* [Selenium](https://www.selenium.dev/) browser automation testing
* [Java](https://www.java.com/) programming language
* [ChromeDriver](https://developer.chrome.com/docs/chromedriver) extends WebDriver by adding Chromium-specific capabilities
* Real-world testing against the [NHS Wales](https://www.nhs.wales/) website

This demo is meant to be read top to bottom like a guide: open `src/main/java/demo/Demo.java`
alongside this README and follow along.

The exact scenario this demo checks is specified in [spec/index.md](spec/index.md); the code and that spec must always agree.

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

This downloads Selenium and the other dependencies listed in [pom.xml](pom.xml).

### Install a browser driver

Selenium 4.6 and newer includes Selenium Manager, which finds or downloads a matching `chromedriver` automatically. You only need Google Chrome installed. If Selenium Manager cannot reach the network, install `chromedriver` yourself (e.g. `brew install chromedriver`) and put it on your `PATH`.

## Run

```sh
mvn compile exec:java
```

The program will:

1. Launch your local Chrome web browser and go to <https://www.nhs.wales/>.
2. Click around and use the search box, the same way a real visitor would.
3. Print a checklist of ✅ verifications as it confirms each expectation,
   then print "All checks passed. 🎉" when everything succeeds.

### Troubleshooting "This version of ChromeDriver …"

If you get an error about a Chrome/chromedriver version mismatch, update Chrome, or delete `~/.cache/selenium` so Selenium Manager fetches a matching driver.

## Tracking

* Package: demo-selenium-java-for-nhs-wales
* Version: 1.0.0
* Created: 2026-10-07T00:00:00Z
* Updated: 2026-10-07T00:00:00Z
* License: GPL-2.0-or-greater or for custom license contact us
* Contact: Joel Parker Henderson (joel@joelparkerhenderson.com)
