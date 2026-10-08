defmodule DemoSeleniumElixir.MixProject do
  use Mix.Project

  def project do
    [
      app: :demo_selenium_elixir,
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
    [{:wallaby, "~> 0.30"}]
  end
end
