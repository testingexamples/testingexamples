defmodule DemoPlaywrightElixir.MixProject do
  use Mix.Project

  def project do
    [
      app: :demo_playwright_elixir,
      version: "1.0.0",
      elixir: "~> 1.15",
      start_permanent: false,
      deps: deps(),
      aliases: [demo: "run -e Demo.main()"]
    ]
  end

  def application do
    [extra_applications: [:logger]]
  end

  defp deps do
    [{:playwright, "~> 1.49.1-alpha.2"}]
  end
end
