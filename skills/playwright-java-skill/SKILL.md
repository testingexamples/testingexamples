---
name: playwright-java-skill
description: Use when asked to write, explain, debug, or extend Playwright browser automation code in Java — locating elements, performing actions, auto-waiting, and writing real assertions with PlaywrightAssertions and JUnit 5.
---

# Playwright Java Skill

## What Playwright for Java is

Playwright (https://playwright.dev/java/) drives Chromium, Firefox, and WebKit through one API. The Java binding is the `com.microsoft.playwright:playwright` artifact.

Maven:

```xml
<dependency>
  <groupId>com.microsoft.playwright</groupId>
  <artifactId>playwright</artifactId>
  <version>1.x.y</version> <!-- use the latest release -->
</dependency>
```

Browsers are downloaded on first use of `Playwright.create()`. To install them ahead of time (e.g. in CI):

```sh
mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="install --with-deps"
```

## Four core concepts: locate, act, wait, assert

### 1. Locate — `page.locator(...)`

A `Locator` is a lazy handle that re-queries the DOM on every use.

```java
page.locator("#id-example-1");                                        // id
page.locator("[name='name-example-1']");                              // attribute
page.locator(".class-example-1");                                     // class
page.locator("a", new Page.LocatorOptions().setHasText("Link Example 1")); // text
page.locator("xpath=//input[@type='submit']");                        // XPath
```

Prefer user-facing locators where they fit: `page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Submit"))`, `page.getByLabel("Text Example 1")`, `page.getByText("Id Example 1")`.

### 2. Act — `Locator` methods

`click()`, `fill(value)`, `check()`, `uncheck()`, `selectOption(...)`, `textContent()`, `inputValue()`, `getAttribute(name)`. These are plain synchronous calls in Java — there is no `await`.

### 3. Wait — auto-waiting

Before `click`, `fill`, `check`, etc., Playwright automatically waits for the element to be attached, visible, stable, enabled, and receiving events. You do **not** write polling loops or sleeps. Reach for `locator.waitFor()` or `page.waitForURL(...)` only for conditions outside the standard actionability checks.

### 4. Assert — see "From walkthrough to real test" below.

## Full worked example

A walkthrough against the free fixture page https://testingexamples.github.io/en-001/practice/:

```java
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.SelectOption;

public class Demo {
    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium()
                .launch(new BrowserType.LaunchOptions().setHeadless(false));
            BrowserContext context = browser.newContext();
            Page page = context.newPage();

            page.navigate("https://testingexamples.github.io/en-001/practice/");

            // Locate by id, name, class, link text, and XPath.
            System.out.println(page.locator("#id-example-1").evaluate("el => el.outerHTML"));
            System.out.println(page.locator("[name='name-example-1']").evaluate("el => el.outerHTML"));
            System.out.println(page.locator(".class-example-1").evaluate("el => el.outerHTML"));
            System.out.println(page.locator("a", new Page.LocatorOptions().setHasText("Link Example 1"))
                .evaluate("el => el.outerHTML"));
            System.out.println(page.locator("xpath=//input[@type='submit']").evaluate("el => el.outerHTML"));

            // Fill a text input.
            page.locator("#text-example-1-id").fill("hello");

            // Check a checkbox and a radio button.
            page.locator("#checkbox-example-1-id").check();
            page.locator("#radio-example-1-option-1-id").check();

            // Select an option by index.
            Locator select = page.locator("#select-example-1-id");
            select.selectOption(new SelectOption().setIndex(0));
            System.out.println("Selected option value: " + select.inputValue());

            browser.close();
        }
    }
}
```

`Playwright` is `AutoCloseable`, so try-with-resources shuts everything down, including on exceptions. This script asserts nothing — it is a walkthrough, not a test.

## From walkthrough to real test

A **real test** uses `PlaywrightAssertions.assertThat(...)` — **web-first assertions** that auto-retry until the condition holds or a timeout elapses — inside a test framework (JUnit 5 here):

```java
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FixtureTest {
    static Playwright playwright;
    static Browser browser;
    Page page;

    @BeforeAll
    static void launchBrowser() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch();
    }

    @AfterAll
    static void closeBrowser() {
        playwright.close();
    }

    @BeforeEach
    void createPage() {
        page = browser.newContext().newPage();
        page.navigate("https://testingexamples.github.io/en-001/practice/");
    }

    @AfterEach
    void closePage() {
        page.context().close();
    }

    @Test
    void idExampleHasExpectedText() {
        assertThat(page.locator("#id-example-1")).hasText("Id Example 1");
    }

    @Test
    void checkboxCanBeChecked() {
        page.locator("#checkbox-example-1-id").check();
        assertThat(page.locator("#checkbox-example-1-id")).isChecked();
    }
}
```

Siblings of `hasText`: `isVisible()`, `hasValue(...)`, `hasCount(...)`, `hasAttribute(...)`, `isChecked()`. Use these rather than `assertEquals(..., locator.textContent())`, which reads once and does not retry. Run with `mvn test`.

## Common pitfalls

- **Sharing Playwright objects across threads.** Playwright for Java is **not thread-safe**: create one `Playwright` (and its browser/context/page) per thread. Parallel JUnit needs one `Playwright` per test class or thread.
- **Asserting with plain `assertEquals` on a one-shot read.** It does not retry; use `PlaywrightAssertions.assertThat`.
- **Strict-mode violations.** If a locator matches several elements, actions throw. Narrow the locator, or call `.first()` / `.nth(i)` / `.filter(...)` deliberately.
- **Leaking contexts/browsers.** Use try-with-resources on `Playwright`, and close the `BrowserContext` per test for isolation.
- **Forgetting browser install in CI.** Run the `install` command above, or the first test run pays the download cost.
- **`evaluate` returns `Object`.** Cast it or use `String.valueOf(...)` before using it as a string.
- **Using `Thread.sleep`.** Never needed for actionability; rely on auto-waiting and web-first assertions.

## Learn more

- https://github.com/testingexamples/demo-playwright-java — the runnable locator-strategy walkthrough this skill's examples are drawn from, run against https://testingexamples.github.io/en-001/practice/.
- https://playwright.dev/java/docs/intro — official Playwright for Java documentation.
- https://playwright.dev/java/docs/test-assertions — assertions reference.
- https://playwright.dev/java/docs/multithreading — thread-safety guidance.
- https://testingexamples.github.io/en-001/practice/ — the free, stable fixture page used above; safe to run repeatedly.
- Google Search and Google Maps restrict automated querying in their Terms of Service; do not point repeated automation at them.

---

AGENTS.md and spec/index.md in this repo are the source of truth for this skill's own scope — if this file ever disagrees with those, they win.
