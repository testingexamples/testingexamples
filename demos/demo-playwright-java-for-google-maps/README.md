# Demo Playwright Java for Google Maps

A friendly, step-by-step tutorial that demonstrates:

* [Playwright](https://www.playwright.dev/) browser automation testing
* [Java](https://www.java.com/) programming language
* [Chromium](https://www.chromium.org/) open source web browser
* Testing patterns against a canvas/WebGL-heavy, real-world application, using [Google Maps](https://www.google.com/maps) as the example

This demo is meant to be read top to bottom like a guide: open `src/main/java/demo/Demo.java`
alongside this README and follow along.

The exact test scenario is specified in [`spec/index.md`](spec/index.md); the code and the spec must always agree.

## Caution: Google's Terms of Service

Google's [Terms of Service](https://www.google.com/policies/terms/) restrict
automated querying of its services, including Google Maps. This demo exists
to show the syntax and interaction pattern of a Playwright test written
against a real, third-party mapping application — it is **not** meant to be
run repeatedly, or at all, against the live `google.com`. See
[AGENTS.md](AGENTS.md) for the non-negotiable that follows from this. If you
want a target you can safely run automation against as often as you like,
point Playwright at
[testingexamples.github.io](https://testingexamples.github.io/en-001/practice/) instead,
which was built exactly for that: stable ids, names, classes, and text that
don't shift under you.

## What this demo tests

Most of Google Maps renders to a `<canvas>`/WebGL surface rather than to
regular DOM elements, so you generally cannot "find" a street or a pin the
way you find a paragraph of text. This demo keeps its assertions modest and
honest, checking only what is reliably observable outside the canvas — the
page title and the URL:

1. **Visit Google Maps** and verify the page title contains `Google Maps`.
2. **Use the search box**: type `Cardiff Castle`, press Enter, and verify
   the resulting URL contains `Cardiff`.
3. **Click the zoom-in button** and verify the URL's embedded zoom-level
   number increased.

Each step prints what it found, then asserts it matches what we expect, the
same assertion style used by this workspace's other Playwright Java
demos.

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

**Do not run this demo against live Google** (see the Terms of Service
warning above). It is here to be read. For reference only, the command
that would start it is `mvn compile exec:java`.

### Troubleshooting "Executable doesn't exist"

The Playwright browsers are not installed yet; run the browser install command above.

## Tracking

* Package: demo-playwright-java-for-google-maps
* Version: 1.0.0
* Created: 2026-10-07T00:00:00Z
* Updated: 2026-10-07T00:00:00Z
* License: GPL-2.0-or-greater or for custom license contact us
* Contact: Joel Parker Henderson (joel@joelparkerhenderson.com)
