# Demo Playwright Go

Demonstration of:

* [Playwright](https://playwright.dev/) browser automation testing
* [Go](https://go.dev/) programming language
* Go modules and the `go` tool
* [Chromium](https://www.chromium.org/) open source web browser

There is no official Go binding from the Playwright project itself. [`playwright-community/playwright-go`](https://github.com/playwright-community/playwright-go) is the community-maintained Playwright client for Go, and is what this demo uses. It is pinned to `v0.5001.0` (Playwright 1.50) in [go.mod](go.mod), the latest tag whose driver download works at the time of writing.

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

This downloads `playwright-go` and the other modules listed in [go.mod](go.mod).

### Install browsers

Playwright manages its own browser binaries. Install them with:

```sh
go run github.com/playwright-community/playwright-go/cmd/playwright install chromium
```

## Run

```sh
go run .
```

The program will do three things:

1. Launch a local Chromium web browser to view the free open source testing
   examples web page <https://testingexamples.github.io/en-001/practice/>.

2. Interact with the web page in various ways, such as finding elements,
   filling in form inputs, checking boxes, etc.

3. Print some typical output that demonstrates the program is running
   successfully.

### Troubleshooting

If you get an error like "could not launch Chromium" or "Executable doesn't exist", the Playwright browsers are not installed yet; run the browser install command above.

## Tracking

* Package: demo-playwright-go
* Version: 1.0.0
* Created: 2026-10-08T00:00:00Z
* Updated: 2026-10-08T00:00:00Z
* License: GPL-2.0-or-greater or for custom license contact us
* Contact: Joel Parker Henderson (joel@joelparkerhenderson.com)
