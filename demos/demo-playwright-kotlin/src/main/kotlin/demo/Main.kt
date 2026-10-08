package demo

import com.microsoft.playwright.BrowserType
import com.microsoft.playwright.Locator
import com.microsoft.playwright.Page
import com.microsoft.playwright.Playwright
import com.microsoft.playwright.options.SelectOption

/**
 * Demo of Playwright browser automation with Kotlin.
 * Please see the file README.md for more information.
 */
private fun outerHtml(locator: Locator): String = locator.evaluate("el => el.outerHTML").toString()

fun main() {
    // Playwright is AutoCloseable: `use` shuts down the driver even if a step throws.
    Playwright.create().use { playwright ->
        val browser = playwright.chromium().launch(
            BrowserType.LaunchOptions()
                .setHeadless(false) // Set to true for headless mode.
                .setArgs(listOf("--disable-notifications")), // Disable notifications such as popups.
        )
        val page = browser.newContext().newPage()

        try {
            page.navigate("https://testingexamples.github.io/en-001/practice/")

            // Playwright locators auto-wait and retry; no explicit waits are needed.

            // Find an element by id.
            println(outerHtml(page.locator("#id-example-1")))

            // Find an element by name attribute.
            println(outerHtml(page.locator("[name='name-example-1']")))

            // Find an element by class name.
            println(outerHtml(page.locator(".class-example-1")))

            // Find a link element by its text.
            println(outerHtml(page.locator("a", Page.LocatorOptions().setHasText("Link Example 1"))))

            // Find an element by XPath.
            println(outerHtml(page.locator("xpath=//input[@type='submit']")))

            // Fill a text input.
            val text = page.locator("#text-example-1-id")
            println(outerHtml(text))
            text.fill("hello")

            // Check a checkbox.
            val checkbox = page.locator("#checkbox-example-1-id")
            println(outerHtml(checkbox))
            checkbox.check()

            // Check a radio button.
            val radio = page.locator("#radio-example-1-option-1-id")
            println(outerHtml(radio))
            radio.check()

            // Select an option by index.
            val select = page.locator("#select-example-1-id")
            println(outerHtml(select))
            select.selectOption(SelectOption().setIndex(0))
            println("Selected option value: " + select.inputValue())
        } finally {
            browser.close()
        }
    }
}
