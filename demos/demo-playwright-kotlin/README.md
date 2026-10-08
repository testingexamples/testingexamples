# Demo Playwright Kotlin

Demonstration of:

* [Playwright](https://playwright.dev/) browser automation testing
* [Kotlin](https://kotlinlang.org/) programming language
* [Maven](https://maven.apache.org/) with the Kotlin Maven plugin
* [Chromium](https://www.chromium.org/) open source web browser

Kotlin runs on the JVM, so this demo uses the official Playwright for Java library (`com.microsoft.playwright:playwright`) directly from Kotlin.

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

This downloads Playwright, the Kotlin compiler, and the other dependencies listed in [pom.xml](pom.xml).

### Install browsers

Playwright for Java downloads the browsers it needs the first time `Playwright.create()` runs, so the first run takes longer. To install them ahead of time:

```sh
mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="install chromium"
```

## Run

```sh
mvn compile exec:java
```

The program will do three things:

1. Launch a local Chromium web browser to view the free open source testing
   examples web page <https://testingexamples.github.io/en-001/practice/>.

2. Interact with the web page in various ways, such as finding elements,
   filling in form inputs, checking boxes, etc.

3. Print some typical output that demonstrates the program is running
   successfully.

### Troubleshooting

If you get "Executable doesn't exist", the Playwright browsers are not installed yet; run the browser install command above.

## Tracking

* Package: demo-playwright-kotlin
* Version: 1.0.0
* Created: 2026-10-08T00:00:00Z
* Updated: 2026-10-08T00:00:00Z
* License: GPL-2.0-or-greater or for custom license contact us
* Contact: Joel Parker Henderson (joel@joelparkerhenderson.com)
