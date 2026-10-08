# Demo Selenium Kotlin

Demonstration of:

* [Selenium](https://www.selenium.dev/) browser automation testing
* [Kotlin](https://kotlinlang.org/) programming language
* [Maven](https://maven.apache.org/) with the Kotlin Maven plugin
* [ChromeDriver](https://developer.chrome.com/docs/chromedriver) extends WebDriver by adding Chromium-specific capabilities

Kotlin runs on the JVM, so this demo uses the official Selenium Java bindings (`selenium-java`) directly from Kotlin.

The exact scenario this demo walks through (target URL, locators, form
interactions) is specified in [spec/index.md](spec/index.md); the code and
spec must agree.

## Install

### Install Java and Maven

Install a JDK (17 or newer) and [Maven](https://maven.apache.org/install.html). On macOS with brew:

```sh
brew install openjdk maven
```

Confirm:

```sh
java -version
mvn -version
```

Kotlin itself is downloaded by Maven (the Kotlin compiler plugin), so you do not need to install it separately.

### Install dependencies

```sh
mvn compile
```

This downloads Selenium, the Kotlin compiler, and the other dependencies listed in [pom.xml](pom.xml).

### Install a browser driver

Selenium 4.6 and newer includes Selenium Manager, which finds or downloads a matching `chromedriver` automatically. You only need Google Chrome installed.

## Run

```sh
mvn compile exec:java
```

The program will do three things:

1. Launch a local Chrome web browser to view the free open source testing
   examples web page <https://testingexamples.github.io/en-001/practice/>.

2. Interact with the web page in various ways, such as finding elements,
   filling in form inputs, checking boxes, etc.

3. Print some typical output that demonstrates the program is running
   successfully.

### Troubleshooting

If you get an error about a Chrome/chromedriver version mismatch, update Chrome, or delete `~/.cache/selenium` so Selenium Manager fetches a matching driver.

## Tracking

* Package: demo-selenium-kotlin
* Version: 1.0.0
* Created: 2026-10-08T00:00:00Z
* Updated: 2026-10-08T00:00:00Z
* License: GPL-2.0-or-greater or for custom license contact us
* Contact: Joel Parker Henderson (joel@joelparkerhenderson.com)
