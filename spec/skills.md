# Spec: skills

## Summary

`skills/` holds 12 Claude Code skills, one per tool and language:
`<tool>-<language>-skill` for `selenium` and `playwright` in `javascript`,
`typescript`, `python`, `rust`, `java`, and `c-sharp`. Each teaches an agent
how to write browser automation in that stack, using worked examples against
`https://testingexamples.github.io/en-001/practice/`.

## Files

Each skill directory contains `SKILL.md` (the skill itself), `README.md`,
`AGENTS.md`, `spec/index.md`, `llms.txt`, `llms.json`, `CITATION.cff`,
`LICENSE.md`, and `.gitignore`. `SKILL.md` is what an agent loads; the rest
document the skill.

## Rules

- Skills live only in the monorepo. They are not in `subtrees.tsv` and
  `bin/publish` does not publish them. The earlier standalone
  `testingexamples/*-skill` repositories are retired.
- The code in a skill's examples must run against the practice page and use
  the same library as the matching demo (see [demos.md](demos.md)).
- A skill summarizes its stack; `spec/index.md` and `AGENTS.md` in the skill
  directory win if they disagree with `SKILL.md`.
- The Elixir, Go, and Kotlin demos have no standalone skill directory under
  `skills/`; each of those demos carries its own `<name>-skill/SKILL.md`.
