# Demo Playwright Java

Demonstration of:

* [Playwright](https://www.playwright.dev/) browser automation testing
* [Java](https://www.java.com/) programming language
* [Maven](https://maven.apache.org/) build tool

The exact scenario this demo walks through (target URL, locators, form
interactions) is specified in [spec/index.md](spec/index.md); the code and
spec must agree.

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

The program will do three things:

1. Launch a local Chrome web browser to view the free open source testing
   examples web page <https://testingexamples.github.io/en-001/practice/>.

2. Interact with the web page in various ways, such as finding elements,
   filling in form inputs, checking boxes, etc.

3. Print some typical output that demonstrates the program is running
   successfully.

### Troubleshooting "Executable doesn't exist"

The Playwright browsers are not installed yet; run the browser install command above.

## Tracking

* Package: demo-playwright-java
* Version: 1.0.0
* Created: 2026-10-06T00:00:00Z
* Updated: 2026-10-06T00:00:00Z
* License: GPL-2.0-or-greater or for custom license contact us
* Contact: Joel Parker Henderson (joel@joelparkerhenderson.com)
