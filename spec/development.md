# Spec: how we work

## Spec first

Change the relevant spec, then the code, in the same change. A new demo,
page, or rule starts as spec text. If the code and a spec disagree, fix one of
them before doing anything else.

## Before committing

- Site changes: `pnpm check` and `pnpm test` pass in
  `sites/testingexamples.github.io`.
- Demo changes: the demo runs (generic and NHS Wales demos only). Google
  demos are never run.
- New or changed library versions are pinned in the demo's build file and
  explained in its README when a newer one does not work.

## Commits

- One logical change per commit, with a message that says what and why.
- Commits made with the assistant end with the trailer
  `Co-Authored-By: Claude Sonnet 5.5 <noreply@anthropic.com>`.
- Do not rewrite or delete history that has been pushed.
- Do not commit build output, installed dependencies, or secrets.

## Publishing

1. Commit in the monorepo and push `main` to each remote host.
2. Run `bin/publish --dry-run`, then `bin/publish` (see
   [publishing.md](publishing.md)). A Codeberg failure for a repo that does
   not exist there is accepted.
3. Do not edit a standalone repository directly.

## Things that need a person

- Native-speaker review of translations.
- Creating repositories on Codeberg (no push-to-create, no API token here).
- Deleting a repository on any host.
- Anything that sends content outside the repository beyond the publish
  targets above.
