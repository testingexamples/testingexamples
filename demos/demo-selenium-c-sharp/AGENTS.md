# AGENTS.md

This repo is a small Selenium walkthrough demo, written in C#, that
launches Chrome, navigates to https://testingexamples.github.io/en-001/practice/, and
demonstrates five ways to locate elements plus four form interactions,
printing what it finds at each step.

`spec/index.md` is the single source of truth for the exact scenario this
demo walks through: the target URL, every selector/locator used, and the
expected values. If the code in `Program.cs` and `spec/index.md` ever
disagree, that is a defect in one of them — fix it before doing anything
else.

For how to install dependencies and run the program, see README.md's
Install and Run sections; this file does not duplicate them.

Non-negotiable: do not add new locator strategies or form interactions to
`Program.cs` without updating `spec/index.md` first, in the same change.

Non-negotiable: every selector comes from the fixture contract of
https://github.com/testingexamples/testingexamples.github.io (see its
`spec/index.md`). Never change a selector here to something the fixture
page does not provide.

This is a generic, unrestricted target (`testingexamples.github.io`, a
fixture page built for this purpose): unlike the `-for-google-search` and
`-for-google-maps` sibling repos of other demos, there is no
Terms-of-Service reason to avoid running this demo.

CLAUDE.md is a pointer to this file — it is the single source of truth for
agent instructions.
