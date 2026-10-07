---
name: selenium-java-skill
description: Use when asked to write, explain, debug, or extend Selenium WebDriver browser automation code in Java — locating elements, performing actions, explicit waits, and writing real assertions with JUnit 5.
---

# Selenium Java Skill

## What Selenium is

Selenium (https://www.selenium.dev/) drives real browsers through the W3C WebDriver protocol. The Java binding is the `org.seleniumhq.selenium:selenium-java` artifact (Selenium 4.x). Since Selenium 4.6, **Selenium Manager** downloads and locates the matching `chromedriver` / `geckodriver` automatically, so you no longer install a driver by hand.

Maven:

```xml
<dependency>
  <groupId>org.seleniumhq.selenium</groupId>
  <artifactId>selenium-java</artifactId>
  <version>4.x.y</version> <!-- use the latest 4.x release -->
</dependency>
<dependency>
  <groupId>org.junit.jupiter</groupId>
  <artifactId>junit-jupiter</artifactId>
  <version>5.x.y</version>
  <scope>test</scope>
</dependency>
```

Gradle: `implementation 'org.seleniumhq.selenium:selenium-java:4.x.y'`.

## Four core concepts: locate, act, wait, assert

### 1. Locate — `driver.findElement(By...)`

```java
driver.findElement(By.id("id-example-1"));
driver.findElement(By.name("name-example-1"));
driver.findElement(By.className("class-example-1"));
driver.findElement(By.linkText("Link Example 1"));
driver.findElement(By.xpath("//input[@type='submit']"));
driver.findElement(By.cssSelector("input[type='submit']"));
```

`findElement` returns the first match or throws `NoSuchElementException`. `findElements` returns a `List<WebElement>` (empty if nothing matches — it never throws).

### 2. Act — `WebElement` methods

```java
element.getText();                 // visible text
element.getAttribute("value");     // attribute/property (null if absent)
element.sendKeys("hello");         // type into an input
element.click();                   // click checkbox, radio, button, link...
new Select(selectElement).selectByIndex(0);   // <select> dropdowns
```

`Select` is `org.openqa.selenium.support.ui.Select`. It only works on real `<select>` elements.

### 3. Wait — Selenium does NOT auto-wait

`findElement` fails immediately if the element is not yet present. Use an **explicit wait**:

```java
WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("id-example-1")));
```

Selenium 4 takes a `java.time.Duration` (the old `long` seconds constructor is removed). Do not mix implicit waits (`driver.manage().timeouts().implicitlyWait(...)`) with explicit waits — the combined timeouts are unpredictable. Prefer explicit waits only.

### 4. Assert — see "From walkthrough to real test" below.

## Full worked example

A walkthrough against the free fixture page https://testingexamples.github.io/en-001/practice/:

```java
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Demo {
    public static void main(String[] args) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        WebDriver driver = new ChromeDriver(options);

        try {
            driver.get("https://testingexamples.github.io/en-001/practice/");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Locate by id, waiting until visible.
            WebElement byId = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("id-example-1")));
            System.out.println(byId.getAttribute("outerHTML"));

            // Locate by name, class name, link text, and XPath.
            System.out.println(driver.findElement(By.name("name-example-1")).getAttribute("outerHTML"));
            System.out.println(driver.findElement(By.className("class-example-1")).getAttribute("outerHTML"));
            System.out.println(driver.findElement(By.linkText("Link Example 1")).getAttribute("outerHTML"));
            System.out.println(driver.findElement(By.xpath("//input[@type='submit']")).getAttribute("outerHTML"));

            // Fill a text input.
            WebElement text = driver.findElement(By.id("text-example-1-id"));
            text.clear();
            text.sendKeys("hello");

            // Click a checkbox and a radio button.
            driver.findElement(By.id("checkbox-example-1-id")).click();
            driver.findElement(By.id("radio-example-1-option-1-id")).click();

            // Choose a select option by index.
            Select select = new Select(driver.findElement(By.id("select-example-1-id")));
            select.selectByIndex(0);
            System.out.println("Selected option value: "
                + select.getFirstSelectedOption().getAttribute("value"));
        } finally {
            driver.quit();
        }
    }
}
```

Note what this does: it locates, acts, and prints. It asserts nothing, so it is a walkthrough, not a test.

## From walkthrough to real test

A **real test** uses a test framework (JUnit 5 here) with assertions, and owns the browser lifecycle in `@BeforeEach` / `@AfterEach`:

```java
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

class FixtureTest {
    private WebDriver driver;

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        driver.get("https://testingexamples.github.io/en-001/practice/");
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @Test
    void idExampleHasExpectedText() {
        assertEquals("Id Example 1", driver.findElement(By.id("id-example-1")).getText());
    }

    @Test
    void checkboxCanBeChecked() {
        var checkbox = driver.findElement(By.id("checkbox-example-1-id"));
        checkbox.click();
        assertTrue(checkbox.isSelected());
    }
}
```

JUnit's `assertEquals(expected, actual)` takes **expected first** — swapping them produces misleading failure messages. Run with `mvn test` or `gradle test`. Because Selenium does not retry, assert on state only after an explicit wait when the page is dynamic.

## Common pitfalls

- **Not calling `driver.quit()` on every path.** Use `finally` or `@AfterEach`; otherwise browser and driver processes leak. `driver.close()` only closes the current window — it is not a substitute.
- **Mixing implicit and explicit waits.** Pick explicit waits.
- **`StaleElementReferenceException`.** A `WebElement` goes stale when the DOM re-renders. Re-locate the element instead of holding the reference.
- **`Thread.sleep` for synchronization.** Slow and flaky; use `WebDriverWait` with an `ExpectedCondition`.
- **Sharing one `WebDriver` across parallel tests.** `WebDriver` is not thread-safe; create one per test/thread.
- **`new Select(...)` on a non-`<select>`.** It throws `UnexpectedTagNameException`.
- **Old Selenium 3 code.** `new WebDriverWait(driver, 10)` (a `long`) and `System.setProperty("webdriver.chrome.driver", ...)` are Selenium 3 idioms; they are unnecessary or removed in 4.x.

## Learn more

- https://github.com/testingexamples/demo-selenium-java — the runnable locator-strategy walkthrough this skill's examples are drawn from, run against https://testingexamples.github.io/en-001/practice/.
- https://www.selenium.dev/documentation/webdriver/ — official WebDriver documentation.
- https://www.selenium.dev/selenium/docs/api/java/ — Java API reference.
- https://testingexamples.github.io/en-001/practice/ — the free, stable fixture page used above; safe to run repeatedly.
- Google Search and Google Maps restrict automated querying in their Terms of Service; do not point repeated automation at them.

---

AGENTS.md and spec/index.md in this repo are the source of truth for this skill's own scope — if this file ever disagrees with those, they win.
