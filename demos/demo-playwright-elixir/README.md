# Demo Playwright Elixir

Demonstration of:

* [Playwright](https://playwright.dev/) browser automation testing
* [Elixir](https://elixir-lang.org/) programming language
* [Mix](https://hexdocs.pm/mix/Mix.html) build tool
* [Chromium](https://www.chromium.org/) open source web browser

There is no official Elixir binding from the Playwright project itself. [`playwright`](https://hex.pm/packages/playwright) (`mechanical-orchard/playwright-elixir`) is the community Playwright client for Elixir, and is what this demo uses. It is in alpha, and its API is not yet at parity with Playwright for other languages.

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

This downloads `playwright` and the other packages listed in [mix.exs](mix.exs).

### Install browsers

Playwright manages its own browser binaries. Install them with:

```sh
mix playwright.install
```

## Run

```sh
mix demo
```

`mix demo` is an alias for `mix run -e Demo.main()`; see [mix.exs](mix.exs).

The program will do three things:

1. Launch a local Chromium web browser to view the free open source testing
   examples web page <https://testingexamples.github.io/en-001/practice/>.

2. Interact with the web page in various ways, such as finding elements,
   filling in form inputs, checking boxes, etc.

3. Print some typical output that demonstrates the program is running
   successfully.

### Troubleshooting

If you get an error like "Executable doesn't exist", the Playwright browsers are not installed yet; run `mix playwright.install`.

## Tracking

* Package: demo-playwright-elixir
* Version: 1.0.0
* Created: 2026-10-08T00:00:00Z
* Updated: 2026-10-08T00:00:00Z
* License: GPL-2.0-or-greater or for custom license contact us
* Contact: Joel Parker Henderson (joel@joelparkerhenderson.com)
