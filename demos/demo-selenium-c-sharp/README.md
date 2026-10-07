# Demo Selenium C#

Demonstration of:

* [Selenium](https://www.selenium.dev/) browser automation testing
* [C#](https://learn.microsoft.com/dotnet/csharp/) programming language
* [.NET](https://dotnet.microsoft.com/) SDK and `dotnet` build tool

The exact scenario this demo walks through (target URL, locators, form
interactions) is specified in [spec/index.md](spec/index.md); the code and
spec must agree.

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

This restores Selenium and the other packages listed in [demo-selenium-c-sharp.csproj](demo-selenium-c-sharp.csproj).

### Install a browser driver

Selenium 4.6 and newer includes Selenium Manager, which finds or downloads a matching `chromedriver` automatically. You only need Google Chrome installed. If Selenium Manager cannot reach the network, install `chromedriver` yourself (e.g. `brew install chromedriver`) and put it on your `PATH`.

## Run

```sh
dotnet run
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

* Package: demo-selenium-c-sharp
* Version: 1.0.0
* Created: 2026-10-06T00:00:00Z
* Updated: 2026-10-06T00:00:00Z
* License: GPL-2.0-or-greater or for custom license contact us
* Contact: Joel Parker Henderson (joel@joelparkerhenderson.com)
