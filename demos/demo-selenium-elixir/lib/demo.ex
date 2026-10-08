defmodule Demo do
  @moduledoc """
  Demo of Selenium WebDriver browser automation with Elixir, using Wallaby.
  Please see the file README.md for more information.
  """

  alias Wallaby.{Browser, Element, Query}

  # Print the outer HTML of an element.
  defp outer_html(element), do: IO.puts(Element.attr(element, "outerHTML"))

  # Scroll an element into view. The page scrolls smoothly, so scroll with
  # behavior 'instant' or a click lands mid-animation.
  defp scroll_to(session, css) do
    Browser.execute_script(
      session,
      "document.querySelector(arguments[0]).scrollIntoView({block: 'center', behavior: 'instant'})",
      [css]
    )
  end

  def main do
    {:ok, _} = Application.ensure_all_started(:wallaby)
    {:ok, session} = Wallaby.start_session(capabilities: %{chromeOptions: %{args: ["--disable-notifications"]}})

    try do
      session = Browser.visit(session, "https://testingexamples.github.io/en-001/practice/")

      # Wallaby queries wait and retry until the element appears.

      # Find an element by id.
      session |> Browser.find(Query.css("#id-example-1")) |> outer_html()

      # Find an element by name.
      session |> Browser.find(Query.css("[name='name-example-1']")) |> outer_html()

      # Find an element by class name.
      session |> Browser.find(Query.css(".class-example-1")) |> outer_html()

      # Find a link element by its text.
      session |> Browser.find(Query.link("Link Example 1")) |> outer_html()

      # Find an element by XPath query.
      session |> Browser.find(Query.xpath("//input[@type='submit']")) |> outer_html()

      # Type in a text input.
      scroll_to(session, "#text-example-1-id")
      session |> Browser.fill_in(Query.css("#text-example-1-id"), with: "hello")

      # Click a checkbox input.
      scroll_to(session, "#checkbox-example-1-id")
      session |> Browser.click(Query.css("#checkbox-example-1-id"))

      # Click a radio input.
      scroll_to(session, "#radio-example-1-option-1-id")
      session |> Browser.click(Query.css("#radio-example-1-option-1-id"))

      # Choose a select input option by index: click the first <option>.
      scroll_to(session, "#select-example-1-id option:first-child")
      session |> Browser.click(Query.css("#select-example-1-id option:first-child"))

      IO.puts("Selected option: " <> (session |> Browser.find(Query.css("#select-example-1-id")) |> Element.value()))
    after
      Wallaby.end_session(session)
    end
  end
end
