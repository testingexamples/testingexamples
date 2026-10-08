import Config

# Wallaby is a WebDriver client; it starts `chromedriver` (which must be on
# your PATH, see README.md) and drives Chrome through it.
config :wallaby,
  driver: Wallaby.Chrome,
  chromedriver: [headless: false],
  js_errors: false
