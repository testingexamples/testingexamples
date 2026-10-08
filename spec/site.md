# Spec: the website

## Summary

`sites/testingexamples.github.io` is the project website, a static SvelteKit
site served by GitHub Pages at `https://testingexamples.github.io/`. Its
detailed contract is `sites/testingexamples.github.io/spec/index.md` (the
fixture elements and the content pages) and
`sites/testingexamples.github.io/spec/locales/index.md`. This file records
how the site ties into the rest of the monorepo.

## Tooling

- pnpm (version pinned by `packageManager`), Node 26.
- `pnpm check` runs `svelte-check`. `pnpm test` runs Playwright against a
  fresh build served by `vite preview` on port 4173. Both must pass before a
  commit.
- Pushing the site to `testingexamples/testingexamples.github.io` `main`
  (via `bin/publish`) triggers the GitHub Pages deploy workflow.

## Structure

- Every page is a topic. The registry is `src/lib/i18n/topics.ts`: a topic id,
  a slug for each language group, and a loader. A page's text, in every
  locale, lives in one file, `src/lib/pages/<topicId>/Page.svelte`.
- Ten locales: `en-001`, `en-gb`, `en-gb-oxendict`, `en-us`, `cy-001`,
  `cy-gb`, `zh-cn`, `ar-001`, `ko-001`, `fr-001`, in six slug groups
  (English, Welsh, Chinese, Arabic, Korean, French). See
  [locales-for-global-sharing-with-svelte/index.md](locales-for-global-sharing-with-svelte/index.md).
- Slugs are per language, not per English variant. Nested slugs are written
  `<topics slug>/<leaf>` (for example `/cy-gb/pynciau/gherkin/`).
- The site root `/` is not a locale. It keeps its own English page (with the
  fixture elements) and redirects only real visitors, never automated
  browsers.

## Locale peer ids

Every page directory `/<locale>/<slug>/` has a `.locale-peer-id` file: 32
lowercase hexadecimal characters then a newline, byte-identical across every
locale's version of the same topic regardless of slug. The ids are random.
There is no registry: the files in `static/` are the only record, they are
committed, and `pnpm locale-peer-ids` creates missing ones (a new topic gets a
new random id; existing ids are kept) and removes stale ones. Run it after
adding or removing a topic or locale. If a topic's slug changes in every
locale at once, `git mv` its `.locale-peer-id` files first or it gets a new
id. `tests/locale-peer-id.spec.ts` enforces all of this.

## Ties to the demos

- The practice page (`/<locale>/practice/`) and the site root carry the
  fixture elements the demos locate. Adding a fixture is allowed; changing or
  removing an existing id, name, class, link text, or label is not, without
  updating every demo that uses it first.
- `src/lib/demos.ts` lists every `demo-*` repository. It must equal the
  `demos/demo-*` entries in `subtrees.tsv`. The Code page and the site root
  list it.
- Each locale home page has a section of source examples (the Google Search
  scenario in each tool and language: Selenium and Playwright in JavaScript,
  Python, Rust, C#, Java, Elixir, Go, and Kotlin, 16 in all). They are teaching
  code; they are compiled but never run against Google.

## Rules

- Moved or deleted pages are not redirected (decision, October 2026): the old
  URL returns 404.
- English wording changes do not automatically reach the other languages.
  Translations are written by the assistant and need a native-speaker review.
- A reword is made everywhere it appears: the page title and heading, links
  and "Next" labels on other pages, `static/llms.txt`, `static/llms.json`, and
  the spec.
