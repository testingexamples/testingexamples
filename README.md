# testingexamples

Monorepo for the [testingexamples](https://github.com/testingexamples) projects.

| Directory | Contents |
| --- | --- |
| `sites/testingexamples.github.io` | The fixture website |
| `demos/demo-*` | Selenium and Playwright demo projects |
| `skills/*-skill` | Claude Code skills for each tool and language |

Each subdirectory is also published as its own standalone repository,
`testingexamples/<name>`, on GitHub, GitLab, and Codeberg, using `git subtree`.

## Publishing

`subtrees.tsv` maps each directory to its repository name.

```sh
bin/publish --dry-run            # show what would be pushed
bin/publish                      # publish everything
bin/publish demos/demo-selenium-python selenium-python-skill
```

Set `HOSTS` or `ORG` to change the targets. Work is committed here; the
standalone repositories are publish-only, so don't commit to them directly.
