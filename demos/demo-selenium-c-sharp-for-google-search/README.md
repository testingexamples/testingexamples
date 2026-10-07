# Demo Selenium C# for Google Search

A friendly, step-by-step tutorial that demonstrates:

* [Selenium](https://www.selenium.dev/) browser automation testing
* [C#](https://learn.microsoft.com/dotnet/csharp/) programming language
* [ChromeDriver](https://developer.chrome.com/docs/chromedriver) extends WebDriver by adding Chromium-specific capabilities
* Locator strategies and assertions for a Google Search results page

This demo is meant to be read top to bottom like a guide: open `Program.cs`
alongside this README and follow along.

The exact scenario this demo teaches is specified in [spec/index.md](spec/index.md); the code and that spec must always agree.

## ⚠️ Google's Terms of Service — do not run this against live google.com

[Google's Terms of Service](https://www.google.com/policies/terms/)
restrict automated querying of Google Search. This code exists to teach
*patterns* — locator strategies and interaction techniques — not scripts
meant to be run against the live `google.com`.

**Non-negotiable:** never install this demo's dependencies in order to
execute it, never run `dotnet run`, and never point Selenium at a
real `google.com` results page from this repo. Read the code to learn the
patterns instead.

This is deliberately different from the sibling repo
`demo-selenium-c-sharp-for-nhs-wales`, which *is* meant to be installed and
run against its real, live target site. That target's terms permit it; the
live `google.com` does not.

## What this demo teaches

The script (never to be executed against the live site — see above) walks
through three checks a visitor's search might involve:

1. **Visit the Google Search home page** and check the page title is
   `Google`.
2. **Use the search box** — located by `By.Name("q")`, since Google's exact
   markup for the search box has drifted over time (historically an
   `<input>`, currently often a `<textarea>`) but has commonly carried
   `name="q"` regardless — type `testing examples`, press Enter, and check
   the resulting page title contains the query.
3. **Click the first result** — located by `By.CssSelector("#search a")`
   — and check the browser navigated to a hostname other than
   `www.google.com`.

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

This restores Selenium and the other packages listed in [demo-selenium-c-sharp-for-google-search.csproj](demo-selenium-c-sharp-for-google-search.csproj).

### Install a browser driver

Selenium 4.6 and newer includes Selenium Manager, which finds or downloads a matching `chromedriver` automatically. You only need Google Chrome installed. If Selenium Manager cannot reach the network, install `chromedriver` yourself (e.g. `brew install chromedriver`) and put it on your `PATH`.

## Run

**Do not run this demo against live Google** (see the Terms of Service
warning above). It is here to be read. For reference only, the command
that would start it is `dotnet run`.

### Troubleshooting "This version of ChromeDriver …"

If you get an error about a Chrome/chromedriver version mismatch, update Chrome, or delete `~/.cache/selenium` so Selenium Manager fetches a matching driver.

## Tracking

* Package: demo-selenium-c-sharp-for-google-search
* Version: 1.0.0
* Created: 2026-10-07T00:00:00Z
* Updated: 2026-10-07T00:00:00Z
* License: GPL-2.0-or-greater or for custom license contact us
* Contact: Joel Parker Henderson (joel@joelparkerhenderson.com)
