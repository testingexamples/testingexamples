# Spec: demos

## Summary

`demos/` holds 54 small, beginner-friendly browser-automation walkthroughs.
Each one drives a browser with one tool (Selenium or Playwright) in one
language. The family exists so a learner can see the same scenario in many
tool-and-language pairs. Each demo is also published as its own repository
(see [publishing.md](publishing.md)).

## Naming

`demo-<tool>-<language>` or `demo-<tool>-<language>-for-<target>`

- `<tool>`: `selenium` or `playwright`.
- `<language>`: `javascript`, `typescript`, `python`, `rust`, `java`,
  `c-sharp`, `elixir`, `go`, `kotlin`.
- `<target>`: absent for the generic demo, or `nhs-wales`, `google-search`,
  `google-maps`. The three targets exist for `javascript`, `typescript`,
  `python`, `rust`, `java`, and `c-sharp` only.

That gives 18 generic demos (2 tools x 9 languages) and 36 target demos
(2 tools x 6 languages x 3 targets).

## Libraries

| Language | Selenium | Playwright |
| --- | --- | --- |
| JavaScript, TypeScript | `selenium-webdriver` (npm) | `playwright` (npm) |
| Python | `selenium` (PyPI) | `playwright` (PyPI) |
| Rust | `thirtyfour` | `playwright-rs` |
| Java | `selenium-java` 4.27.0 | `com.microsoft.playwright:playwright` 1.49.0 |
| C# | `Selenium.WebDriver` 4.x | `Microsoft.Playwright` 1.x |
| Kotlin | `selenium-java` 4.27.0 via Maven and the Kotlin plugin | the Java Playwright library via Maven and the Kotlin plugin |
| Go | `github.com/tebeka/selenium` (no official binding) | `github.com/playwright-community/playwright-go`, pinned to v0.5001.0 (newer tags currently fail to download their driver) |
| Elixir | `wallaby`, a WebDriver client (no official binding) | `playwright` on Hex (alpha) |

A demo uses the library in this table. Switching library is a change to the
demo's README, AGENTS.md, and spec first.

Selenium demos in Rust and Go need a `chromedriver` already running
(`chromedriver --port=9515`); Elixir needs it on the `PATH`. Java, C#, and
Kotlin use Selenium Manager and need only Chrome.

## Files in every demo

`README.md`, `AGENTS.md`, `spec/index.md`, `llms.txt`, `llms.json`,
`CITATION.cff`, `cspell.json`, `LICENSE.md`, `.gitignore`,
`<name>-skill/SKILL.md`, the program (for example `src/demo.py`, `main.go`,
`src/main/java/demo/Demo.java`), and the stack's build and lock files.

`spec/index.md` is the single source of truth for what the program does. The
program and that spec must agree.

## Scenarios

### Generic demos

Target `https://testingexamples.github.io/en-001/practice/`. Five locators,
in order: by id (`id-example-1`), by name (`name-example-1`), by class
(`class-example-1`), by link text (`Link Example 1`), by XPath
(`//input[@type='submit']`). Four form interactions, in order: fill the text
input (`text-example-1-id`) with `hello`, check the checkbox
(`checkbox-example-1-id`), check the first radio
(`radio-example-1-option-1-id`), select option index 0 of the select
(`select-example-1-id`). The program prints what it finds; it does not
assert. Every selector comes from the site's fixture contract
(`sites/testingexamples.github.io/spec/index.md`).

### `-for-nhs-wales`

Runs against the live `https://www.nhs.wales/` and asserts three things: the
home page title is `Home - NHS Wales`; the "About Us" link leads to a page
titled `About Us - NHS Wales` with an `h1` of `About Us` (in Playwright the
nav item has role `menuitem`, not `link`); searching `help` (`#navKeywords`,
`#button-addon`) shows `Search Results` and `Your search for "help"`.
Selenium variants reject cookies; the JavaScript and TypeScript ones also wait for navigation before reading the page.

### `-for-google-search`, `-for-google-maps`

Teaching code only. **Never run, install-to-run, or point at the live
Google**: Google's Terms of Service restrict automated querying. They
compile or lint at most. Each demo's README and AGENTS.md say so.

## Adding a demo

1. Create `demos/demo-<tool>-<language>[-for-<target>]/` with every file
   above, following a sibling demo.
2. Run it and fix it (generic and NHS Wales demos only; never Google).
3. List it in `subtrees.tsv`.
4. Add it to `DEMO_REPOS` in
   `sites/testingexamples.github.io/src/lib/demos.ts` (the site's Code page
   and home list must match `subtrees.tsv`), and, for a new language, add its
   source examples to the site's home page (see [site.md](site.md)).
5. Create the public GitHub repo, then publish.
