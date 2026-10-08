# Demo Selenium Go

Demonstration of:

* [Selenium](https://www.selenium.dev/) browser automation testing
* [Go](https://go.dev/) programming language
* Go modules and the `go` tool
* [ChromeDriver](https://developer.chrome.com/docs/chromedriver) extends WebDriver by adding Chromium-specific capabilities

There is no official Go binding from the Selenium project itself. [`tebeka/selenium`](https://pkg.go.dev/github.com/tebeka/selenium) is the best-known Selenium/WebDriver client for Go, and is what this demo uses.

The exact scenario this demo walks through (target URL, locators, form
interactions) is specified in [spec/index.md](spec/index.md); the code and
spec must agree.

## Install

### Install Go

Install Go from <https://go.dev/dl/>. On macOS with brew:

```sh
brew install go
```

Confirm:

```sh
go version
```

### Install dependencies

```sh
go mod download
```

This downloads `tebeka/selenium` and the other modules listed in [go.mod](go.mod).

### Install chromedriver

Unlike Playwright, Selenium for Go does not download or start a driver for you: you need a WebDriver server already running.

If you use macOS brew:

```sh
brew install chromedriver
```

Or download the version that matches your Chrome browser app from <https://googlechromelabs.github.io/chrome-for-testing/>.

## Run

Start `chromedriver` listening on port 9515 (the port this demo connects to):

```sh
chromedriver --port=9515
```

Then, in another terminal, run:

```sh
go run .
```

The program will do three things:

1. Launch a local Chrome web browser to view the free open source testing
   examples web page <https://testingexamples.github.io/en-001/practice/>.

2. Interact with the web page in various ways, such as finding elements,
   filling in form inputs, checking boxes, etc.

3. Print some typical output that demonstrates the program is running
   successfully.

### Troubleshooting

If you get an error message about a Chrome/chromedriver version mismatch, harmonize your Chrome browser app and your `chromedriver` version (e.g. `brew upgrade chromedriver` on macOS).

## Tracking

* Package: demo-selenium-go
* Version: 1.0.0
* Created: 2026-10-08T00:00:00Z
* Updated: 2026-10-08T00:00:00Z
* License: GPL-2.0-or-greater or for custom license contact us
* Contact: Joel Parker Henderson (joel@joelparkerhenderson.com)
