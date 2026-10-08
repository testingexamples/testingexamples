defmodule Demo do
  @moduledoc """
  Demo of Playwright browser automation with Elixir.
  Please see the file README.md for more information.
  """

  alias Playwright.{Browser, Locator, Page}

  # Print the outer HTML of the element a locator points at.
  defp outer_html(locator), do: IO.puts(Locator.evaluate(locator, "el => el.outerHTML"))

  def main do
    # Launch a browser. Set headless to true if you don't want to watch it.
    {:ok, browser} = Playwright.launch(:chromium, %{headless: false, args: ["--disable-notifications"]})
    page = Browser.new_page(browser)

    try do
      Page.goto(page, "https://testingexamples.github.io/en-001/practice/")

      # Playwright locators auto-wait and retry; no explicit waits are needed.

      # Find an element by id.
      page |> Page.locator("#id-example-1") |> outer_html()

      # Find an element by name attribute.
      page |> Page.locator("[name='name-example-1']") |> outer_html()

      # Find an element by class name.
      page |> Page.locator(".class-example-1") |> outer_html()

      # Find a link element by its text.
      page |> Page.locator("a:has-text('Link Example 1')") |> outer_html()

      # Find an element by XPath.
      page |> Page.locator("xpath=//input[@type='submit']") |> outer_html()

      # Fill a text input.
      text = Page.locator(page, "#text-example-1-id")
      outer_html(text)
      Locator.fill(text, "hello")

      # Check a checkbox.
      checkbox = Page.locator(page, "#checkbox-example-1-id")
      outer_html(checkbox)
      Locator.check(checkbox)

      # Check a radio button.
      radio = Page.locator(page, "#radio-example-1-option-1-id")
      outer_html(radio)
      Locator.check(radio)

      # Select an option by index.
      select = Page.locator(page, "#select-example-1-id")
      outer_html(select)
      Locator.select_option(select, %{index: 0})
      IO.puts("Selected option value: " <> Locator.input_value(select))
    after
      Browser.close(browser)
    end
  end
end
