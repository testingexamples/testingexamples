# Demo Selenium C# for Google Maps

A friendly, step-by-step tutorial that demonstrates:

* [Selenium](https://www.selenium.dev/) browser automation testing
* [C#](https://learn.microsoft.com/dotnet/csharp/) programming language
* [ChromeDriver](https://developer.chrome.com/docs/chromedriver) extends WebDriver by adding Chromium-specific capabilities
* Locator strategies and assertions for Google Maps

This demo is meant to be read top to bottom like a guide: open `Program.cs`
alongside this README and follow along.

The exact scenario this demo teaches is specified in [spec/index.md](spec/index.md); the code and that spec must always agree.

## ⚠️ Google's Terms of Service — do not run this against live Google Maps

[Google's Terms of Service](https://www.google.com/policies/terms/)
restrict automated querying of its services. This code exists to teach
*patterns* — locator strategies and interaction techniques — not scripts
meant to be run against the live Google Maps.

**Non-negotiable:** never install this demo's dependencies in order to
execute it, never run `dotnet run`, and never point Selenium at
real `google.com/maps` from this repo. Read the code to learn the patterns
instead.

This is deliberately different from the sibling repo
`demo-selenium-c-sharp-for-nhs-wales`, which *is* meant to be installed and
run against its real, live target site. That target's terms permit it; the
live Google Maps does not.

## What this demo teaches

The script (never to be executed against the live site — see above) walks
through three checks:

1. **Load Google Maps** and check the page title contains `Google Maps`.
2. **Search for a place** — located by
   `By.CssSelector("[aria-label="Search Google Maps"]")`, a stable
   accessible-name selector (see note below) — and check the resulting URL
   contains `Cardiff`.
3. **Click zoom in** — located by
   `By.CssSelector("[aria-label="Zoom in"]")` — and check the URL's
   embedded `@lat,lng,zoomz` zoom parameter increased.

### Why accessible-name selectors, and why assert on the URL?

Most of the map itself renders to a `<canvas>` element (or WebGL), so you
generally cannot "find" a street or a pin the way you find a paragraph of
text. But the UI chrome around the canvas — search box, zoom buttons,
layers menu — uses stable accessible `aria-label` attributes, which are a
much better choice than generated or hashed CSS class names that change on
every Maps deploy. That's why both locators above use `[aria-label="..."]`.

For the same reason, the zoom check can't inspect canvas pixels to see that
the map "looks" more zoomed in. Instead it reads Google Maps' own embedded
view state back out of the URL — the `@lat,lng,zoomz` segment, e.g.
`https://www.google.com/maps/place/Cardiff/@51.4816546,-3.1791934,12z/...`
— and asserts the parsed zoom number increased after clicking "Zoom in".
This URL-based assertion is the honest strategy for a canvas-rendered map.

Each step prints what it found, then asserts it matches what we expect,
the same style used across this project family's assertion-based demos.

## Install

### Install .NET

Install the .NET SDK (10.0 or newer, matching `TargetFramework` in the `.csproj`) from <https://dotnet.microsoft.com/download>.
On macOS with brew:

```sh
brew install dotnet
```

Confirm:

```sh
dotnet --version
```

### Install dependencies

```sh
dotnet build
```

This restores Selenium and the other packages listed in [demo-selenium-c-sharp-for-google-maps.csproj](demo-selenium-c-sharp-for-google-maps.csproj).

### Install a browser driver

Selenium 4.6 and newer includes Selenium Manager, which finds or downloads a matching `chromedriver` automatically. You only need Google Chrome installed. If Selenium Manager cannot reach the network, install `chromedriver` yourself (e.g. `brew install chromedriver`) and put it on your `PATH`.

## Run

**Do not run this demo against live Google** (see the Terms of Service
warning above). It is here to be read. For reference only, the command
that would start it is `dotnet run`.

### Troubleshooting "This version of ChromeDriver …"

If you get an error about a Chrome/chromedriver version mismatch, update Chrome, or delete `~/.cache/selenium` so Selenium Manager fetches a matching driver.

## Tracking

* Package: demo-selenium-c-sharp-for-google-maps
* Version: 1.0.0
* Created: 2026-10-07T00:00:00Z
* Updated: 2026-10-07T00:00:00Z
* License: GPL-2.0-or-greater or for custom license contact us
* Contact: Joel Parker Henderson (joel@joelparkerhenderson.com)
