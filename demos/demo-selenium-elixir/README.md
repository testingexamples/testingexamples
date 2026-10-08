# Demo Selenium Elixir

Demonstration of:

* [Selenium](https://www.selenium.dev/) browser automation testing
* [Elixir](https://elixir-lang.org/) programming language
* [Mix](https://hexdocs.pm/mix/Mix.html) build tool
* [ChromeDriver](https://developer.chrome.com/docs/chromedriver) extends WebDriver by adding Chromium-specific capabilities

There is no official Elixir binding from the Selenium project itself. [`wallaby`](https://hex.pm/packages/wallaby) is the best-known WebDriver client for Elixir: it starts `chromedriver` and drives Chrome through the WebDriver protocol, which is the same protocol Selenium speaks. It is what this demo uses.

The exact scenario this demo walks through (target URL, locators, form
interactions) is specified in [spec/index.md](spec/index.md); the code and
spec must agree.

## Install

### Install Elixir

Install Elixir (which includes Erlang/OTP and Mix) from <https://elixir-lang.org/install.html>. On macOS with brew:

```sh
brew install elixir
```

Confirm:

```sh
elixir --version
```

### Install dependencies

```sh
mix deps.get
```

This downloads Wallaby and the other packages listed in [mix.exs](mix.exs).

### Install chromedriver

Wallaby starts `chromedriver` for you, but it must be on your `PATH`.

If you use macOS brew:

```sh
brew install chromedriver
```

Or download the version that matches your Chrome browser app from <https://googlechromelabs.github.io/chrome-for-testing/>.

## Run

```sh
mix demo
```

`mix demo` is an alias for `mix run -e Demo.main()`; see [mix.exs](mix.exs).

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

* Package: demo-selenium-elixir
* Version: 1.0.0
* Created: 2026-10-08T00:00:00Z
* Updated: 2026-10-08T00:00:00Z
* License: GPL-2.0-or-greater or for custom license contact us
* Contact: Joel Parker Henderson (joel@joelparkerhenderson.com)
