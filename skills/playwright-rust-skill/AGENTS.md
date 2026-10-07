# AGENTS.md

This repo has one job: `SKILL.md`, the Claude Code skill that teaches how to
write Playwright browser automation code in Rust using the `playwright-rs`
crate.

`spec/index.md` is the source of truth for what the skill must accurately
cover — its scope, the rules it must follow (such as the `playwright-rs` vs.
abandoned `playwright` crate-naming warning), and its acceptance criteria.
When changing `SKILL.md`, check it against `spec/index.md` first.

`CLAUDE.md` points here.

CLAUDE.md is a pointer to this file — it is the single source of truth for
agent instructions.
