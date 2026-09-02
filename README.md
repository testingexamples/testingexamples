# Demo Selenium JavaScript for NHS Wales

A friendly, step-by-step tutorial that demonstrates:

* [Selenium](https://www.selenium.dev/) browser automation testing
* [JavaScript](https://en.wikipedia.org/wiki/JavaScript) programming language
* [Node](https://nodejs.org/) runtime built on Chrome's V8 JavaScript engine
* [ChromeDriver](https://developer.chrome.com/docs/chromedriver) extends WebDriver by adding Chromium-specific capabilities
* Real-world testing against the [NHS Wales](https://www.nhs.wales/) website

This demo is meant to be read top to bottom like a guide: open `src/demo.js`
alongside this README and follow along.

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

### Install Node and NPM

Install Node and NPM from <https://nodejs.org/>

Run this to confirm your version:

```sh
node -v
```

Output should be at least:

```stdout
v23.6.1
```

Run this to confirm your version:

```sh
npm -v
```

Output should be at least:

```stdout
11.2.0
```

### Install Selenium

Install Selenium WebDriver:

```sh
npm install --save selenium-webdriver@latest
```

### Install chromedriver

Install Google chromedriver:

```sh
npm install --save chromedriver@latest
```

### Update

Run:

```sh
npm install npm@latest
npm upgrade
npm audit fix
```

## Run

Run:

```sh
node src/demo.js
```

The script will:

1. Launch your local Chrome web browser and go to <https://www.nhs.wales/>.
2. Click around and use the search box, the same way a real visitor would.
3. Print a checklist of ✅ verifications as it confirms each expectation,
   then print "All checks passed. 🎉" when everything succeeds.

### Troubleshooting “chromedriver” Not Opened

If you get this kind of error message:

```txt
“chromedriver” Not Opened. Apple could not verify “chromedriver”
is free of malware that may harm your Mac or compromise your privacy.
```

Or this kind of error message:

```txt
Apple is not able to verify that it is free from malware that could harm your
Mac or compromise your privacy. Don’t open this unless you are certain it is
from a trustworthy source.
```

Then click "Done".

Try this command line solution:

```sh
xattr -d com.apple.quarantine $(which chromedriver) 2>/dev/null
```

Try adjusting your system settings:

* Apple menu -> Settings -> Security & Privacy -> General

* See the entry that says: "chromedriver" was blocked to protect your Mac.

* Click the button "Allow Anyway".

### Troubleshooting "This version of ChromeDriver …"

If you get this kind of error message:

```txt
UnhandledPromiseRejectionWarning:
SessionNotCreatedError: session not created:
This version of ChromeDriver only supports Chrome version …
```

Then you may need to harmonize your Chrome browser app and your Chrome webdriver.

If you use macOS brew, then upgrade chromedriver:

```sh
brew upgrade chromedriver
```

To update your Chrome browser app:

* On your computer, open Chrome.

* Find your current Chrome version by typing in the URL bar: `chrome://version/`.

* You should see a web page with many details, and you should see the first line with the version number, such as: "Google Chrome 135.0.7049.86 (Official Build)".

* At top right, tap the "More" icon, which is 3 vertical dots.

* You see the "More" menu. If you see a menu item "Update", then choose it. If you don't see a menu item "Update", then  you're on the current version.

To update your Chrome webdriver:

* Go to https://chromedriver.chromium.org/downloads

* Download the version that matches your Chrome browser app.

## Tracking

* Package: demo-selenium-javascript-for-nhs-wales
* Version: 1.0.0
* Created: 2026-09-02T00:00:00Z
* Updated: 2026-09-02T00:00:00Z
* License: GPL-2.0-or-greater or for custom license contact us
* Contact: Joel Parker Henderson (joel@joelparkerhenderson.com)
