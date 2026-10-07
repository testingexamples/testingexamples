package demo;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.SelectOption;

/**
 * Demo of Playwright browser automation with Java.
 * Please see the file README.md for more information.
 */
public class Demo {

    private static String outerHtml(Locator locator) {
        return String.valueOf(locator.evaluate("el => el.outerHTML"));
    }

    public static void main(String[] args) {
        // Playwright is AutoCloseable: try-with-resources shuts down the driver.
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                .setHeadless(false) // Set to true for headless mode.
                .setArgs(java.util.List.of("--disable-notifications")));
            BrowserContext context = browser.newContext();
            Page page = context.newPage();

            try {
                page.navigate("https://testingexamples.github.io/en-001/practice/");

                // Playwright locators auto-wait and retry; no explicit waits are needed.

                // Find an element by id.
                System.out.println(outerHtml(page.locator("#id-example-1")));

                // Find an element by name attribute.
                System.out.println(outerHtml(page.locator("[name='name-example-1']")));

                // Find an element by class name.
                System.out.println(outerHtml(page.locator(".class-example-1")));

                // Find a link element by its text.
                System.out.println(outerHtml(
                    page.locator("a", new Page.LocatorOptions().setHasText("Link Example 1"))));

                // Find an element by XPath.
                System.out.println(outerHtml(page.locator("xpath=//input[@type='submit']")));

                // Fill a text input.
                Locator text = page.locator("#text-example-1-id");
                System.out.println(outerHtml(text));
                text.fill("hello");

                // Check a checkbox.
                Locator checkbox = page.locator("#checkbox-example-1-id");
                System.out.println(outerHtml(checkbox));
                checkbox.check();

                // Check a radio button.
                Locator radio = page.locator("#radio-example-1-option-1-id");
                System.out.println(outerHtml(radio));
                radio.check();

                // Select an option by index.
                Locator select = page.locator("#select-example-1-id");
                System.out.println(outerHtml(select));
                select.selectOption(new SelectOption().setIndex(0));
                System.out.println("Selected option value: " + select.inputValue());
            } finally {
                browser.close();
            }
        }
    }
}
