package demo

import java.time.Duration
import org.openqa.selenium.By
import org.openqa.selenium.ElementClickInterceptedException
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.StaleElementReferenceException
import org.openqa.selenium.WebElement
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.Select
import org.openqa.selenium.support.ui.WebDriverWait

/**
 * Demo of Selenium browser automation with Kotlin.
 * Please see the file README.md for more information.
 */
fun main() {
    val options = ChromeOptions()
    options.addArguments("--disable-notifications") // Disable notifications such as popups.

    // Selenium Manager finds or downloads a matching chromedriver.
    val driver = ChromeDriver(options)

    try {
        driver.get("https://testingexamples.github.io/en-001/practice/")

        // Selenium does not auto-wait, so wait explicitly for the page content.
        val wait = WebDriverWait(driver, Duration.ofSeconds(10))

        // Find an element by id.
        val elementById = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("id-example-1")))
        println(elementById.getAttribute("outerHTML"))

        // Find an element by name.
        val elementByName = driver.findElement(By.name("name-example-1"))
        println(elementByName.getAttribute("outerHTML"))

        // Find an element by class name.
        val elementByClassName = driver.findElement(By.className("class-example-1"))
        println(elementByClassName.getAttribute("outerHTML"))

        // Find a link element by its text.
        val elementByLinkText = driver.findElement(By.linkText("Link Example 1"))
        println(elementByLinkText.getAttribute("outerHTML"))

        // Find an element by XPath query.
        val elementByXPath = driver.findElement(By.xpath("//input[@type='submit']"))
        println(elementByXPath.getAttribute("outerHTML"))

        // Form controls: locate, scroll into view, and act together inside one explicit
        // wait. The page re-renders while it hydrates, which can make a located element go
        // stale, and the page scrolls smoothly (so scroll with behavior 'instant', or the
        // click lands mid-animation); retrying the whole step until it succeeds handles both.
        val formWait = WebDriverWait(driver, Duration.ofSeconds(10))
        formWait.ignoring(
            StaleElementReferenceException::class.java,
            ElementClickInterceptedException::class.java,
        )
        val js = driver as JavascriptExecutor

        fun actOn(by: By, action: (WebElement) -> Unit) {
            formWait.until {
                val element = driver.findElement(by)
                js.executeScript("arguments[0].scrollIntoView({block: 'center', behavior: 'instant'})", element)
                println(element.getAttribute("outerHTML"))
                action(element)
                true
            }
        }

        // Type in a text input.
        actOn(By.id("text-example-1-id")) { text ->
            text.clear()
            text.sendKeys("hello")
        }

        // Click a checkbox input.
        actOn(By.id("checkbox-example-1-id")) { checkbox -> checkbox.click() }

        // Click a radio input.
        actOn(By.id("radio-example-1-option-1-id")) { radio -> radio.click() }

        // Choose a select input option by index.
        actOn(By.id("select-example-1-id")) { selectElement ->
            val select = Select(selectElement)
            select.selectByIndex(0)
            println(select.firstSelectedOption.getAttribute("outerHTML"))
        }
    } finally {
        driver.quit()
    }
}
