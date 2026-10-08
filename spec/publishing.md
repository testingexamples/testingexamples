# Spec: publishing standalone repos

## Summary

Directories listed in `subtrees.tsv` are published to standalone repositories
with `git subtree`, using `bin/publish`. Publishing preserves history: a
directory's split commits are the same commits that were imported, so a
publish is a fast-forward push.

## `subtrees.tsv`

Tab-separated, one directory per line: `<prefix><TAB><repo-name>`. Blank lines
and lines starting with `#` are ignored. Currently 55 entries: the 54 demos
under `demos/` and `sites/testingexamples.github.io`. Skills are not listed;
see [skills.md](skills.md).

Adding a directory to the monorepo does not publish it. It is published only
when it is listed here.

## `bin/publish`

```sh
bin/publish [--dry-run] [<prefix-or-repo-name> ...]
```

- With no arguments, publishes every listed directory. With arguments,
  publishes only the entries whose prefix or repo name matches.
- For each entry: `git subtree split --prefix=<prefix>`, then push the result
  to `refs/heads/main` of `git@<host>:<ORG>/<repo>.git` for every host.
- Environment: `ORG` (default `testingexamples`), `HOSTS` (default
  `github.com gitlab.com codeberg.org`), `BRANCH` (default `main`).
- A failing host is reported (`FAIL <host>`) and skipped; the other hosts and
  entries still run. The exit status is non-zero if any push failed.
- `--dry-run` prints what would be pushed and pushes nothing.

## Host requirements

| Host | Creates a missing repo on push? | Notes |
| --- | --- | --- |
| github.com | No | Create the public repo first (`gh repo create testingexamples/<name> --public`). |
| gitlab.com | Yes (push to create) | The project appears on first push. |
| codeberg.org | No | The repo must already exist. On 2026-10-08 only the twelve generic demos in JavaScript, TypeScript, Python, Rust, Java, and C# exist there; every other publish to Codeberg fails, which is accepted. |

A new standalone repository is therefore created on GitHub (public, with a
one-line description) before its first publish.

## Rules

- Never rewrite or force-push published history.
- Run `bin/publish --dry-run <name>` before publishing something new.
- After a publish, the monorepo's `subtree split` of a directory equals the
  remote `main` (`git subtree split --prefix=<p>` vs `git ls-remote <url> main`).
