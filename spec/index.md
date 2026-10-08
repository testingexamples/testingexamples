# Spec: testingexamples monorepo

This directory is the source of truth for the monorepo, for
specification-driven development: **change the spec first, then the code, in
the same change.** If the code and a spec ever disagree, that is a defect in
one of them; fix it before making any other change.

## Specs

| Spec | Covers |
| --- | --- |
| [monorepo.md](monorepo.md) | Directory layout and what lives where |
| [publishing.md](publishing.md) | How directories are published as standalone repos (`bin/publish`, `subtrees.tsv`) |
| [demos.md](demos.md) | The `demos/demo-*` family: naming, files, scenarios, libraries, adding a demo |
| [skills.md](skills.md) | The `skills/*-skill` Claude Code skills |
| [site.md](site.md) | The `sites/testingexamples.github.io` website and its tie to the demos |
| [development.md](development.md) | The working rules: spec first, tests, commits, publishing |
| [locales-for-global-sharing-with-svelte/index.md](locales-for-global-sharing-with-svelte/index.md) | Locale support end to end, including the `.locale-peer-id` file |
| [locales/locales-by-priority.md](locales/locales-by-priority.md) | Which locales to translate, in what order |

Each project directory also has its own, more detailed spec:

- `sites/testingexamples.github.io/spec/index.md` (the fixture and content contract)
- `demos/<name>/spec/index.md` (the exact scenario one demo performs)

This root spec says how the pieces fit together; the project specs say what
each piece does. They must not contradict each other.

## Status

Facts in these specs were true on 2026-10-08. When one changes, update the
spec in the same commit.
