# Demo Selenium Java

Demonstration of:

* [Selenium](https://www.selenium.dev/) browser automation testing
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

This downloads Selenium and the other dependencies listed in [pom.xml](pom.xml).

### Install a browser driver

Selenium 4.6 and newer includes Selenium Manager, which finds or downloads a matching `chromedriver` automatically. You only need Google Chrome installed. If Selenium Manager cannot reach the network, install `chromedriver` yourself (e.g. `brew install chromedriver`) and put it on your `PATH`.

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

### Troubleshooting "This version of ChromeDriver …"

If you get an error about a Chrome/chromedriver version mismatch, update Chrome, or delete `~/.cache/selenium` so Selenium Manager fetches a matching driver.

## Tracking

* Package: demo-selenium-java
* Version: 1.0.0
* Created: 2026-10-06T00:00:00Z
* Updated: 2026-10-06T00:00:00Z
* License: GPL-2.0-or-greater or for custom license contact us
* Contact: Joel Parker Henderson (joel@joelparkerhenderson.com)
