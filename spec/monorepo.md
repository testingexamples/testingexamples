# Spec: monorepo layout

## Summary

One git repository, `testingexamples/testingexamples`, holds every project.
Most directories are also published as their own standalone repository
(see [publishing.md](publishing.md)); the monorepo is where work happens.

## Layout

```text
testingexamples/
  README.md              what the monorepo is, in short
  .gitignore             build output and dependency directories
  bin/publish            publishes directories as standalone repos
  subtrees.tsv           directory -> standalone repo name (the publish list)
  spec/                  these specs (source of truth)
  sites/
    testingexamples.github.io/   the website (SvelteKit)
  demos/
    demo-<tool>-<language>[-for-<target>]/   54 demo projects
  skills/
    <tool>-<language>-skill/                 12 Claude Code skills
```

## Rules

- All work is committed in the monorepo. The standalone repositories are
  publish-only; never commit to them directly (a direct commit would make the
  next publish a non-fast-forward push).
- Each project directory is self-contained: its own README, build files,
  dependency lockfiles, and spec. Nothing under `demos/` imports from another
  directory.
- Build output and installed dependencies are never committed
  (`node_modules/`, `target/`, `obj/`, `bin/Debug/`, `_build/`, `deps/`,
  `build/`, `.svelte-kit/`). Each demo's own `.gitignore` covers its stack.
- Lockfiles (`pnpm-lock.yaml`, `Cargo.lock`, `go.sum`, `mix.lock`) are
  committed.

## Remotes

`origin` fetches from `git@github.com:testingexamples/testingexamples.git`
and pushes to that repo on GitHub, on GitLab, and on Codeberg. Push each host
separately (`git push git@<host>:testingexamples/testingexamples.git main`)
so one host failing does not stop the others.
