package demo;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Demo of Selenium browser automation with Java.
 * Please see the file README.md for more information.
 */
public class Demo {

    public static void main(String[] args) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications"); // Disable notifications such as popups.

        // Selenium Manager finds or downloads a matching chromedriver.
        WebDriver driver = new ChromeDriver(options);

        try {
            driver.get("https://testingexamples.github.io/en-001/practice/");

            // Selenium does not auto-wait, so wait explicitly for the page content.
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Find an element by id.
            WebElement elementById = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("id-example-1")));
            System.out.println(elementById.getAttribute("outerHTML"));

            // Find an element by name.
            WebElement elementByName = driver.findElement(By.name("name-example-1"));
            System.out.println(elementByName.getAttribute("outerHTML"));

            // Find an element by class name.
            WebElement elementByClassName = driver.findElement(By.className("class-example-1"));
            System.out.println(elementByClassName.getAttribute("outerHTML"));

            // Find a link element by its text.
            WebElement elementByLinkText = driver.findElement(By.linkText("Link Example 1"));
            System.out.println(elementByLinkText.getAttribute("outerHTML"));

            // Find an element by XPath query.
            WebElement elementByXPath = driver.findElement(By.xpath("//input[@type='submit']"));
            System.out.println(elementByXPath.getAttribute("outerHTML"));

            // Form controls: locate, scroll into view, and act together inside one explicit
            // wait. The page re-renders while it hydrates, which can make a located element go
            // stale, and the page scrolls smoothly (so scroll with behavior 'instant', or the
            // click lands mid-animation); retrying the whole
            // step until it succeeds handles both.
            WebDriverWait formWait = new WebDriverWait(driver, Duration.ofSeconds(10));
            formWait.ignoring(StaleElementReferenceException.class, ElementClickInterceptedException.class);
            JavascriptExecutor js = (JavascriptExecutor) driver;

            // Type in a text input.
            formWait.until(d -> {
                WebElement text = d.findElement(By.id("text-example-1-id"));
                js.executeScript("arguments[0].scrollIntoView({block: 'center', behavior: 'instant'})", text);
                System.out.println(text.getAttribute("outerHTML"));
                text.clear();
                text.sendKeys("hello");
                return true;
            });

            // Click a checkbox input.
            formWait.until(d -> {
                WebElement checkbox = d.findElement(By.id("checkbox-example-1-id"));
                js.executeScript("arguments[0].scrollIntoView({block: 'center', behavior: 'instant'})", checkbox);
                System.out.println(checkbox.getAttribute("outerHTML"));
                checkbox.click();
                return true;
            });

            // Click a radio input.
            formWait.until(d -> {
                WebElement radio = d.findElement(By.id("radio-example-1-option-1-id"));
                js.executeScript("arguments[0].scrollIntoView({block: 'center', behavior: 'instant'})", radio);
                System.out.println(radio.getAttribute("outerHTML"));
                radio.click();
                return true;
            });

            // Choose a select input option by index.
            formWait.until(d -> {
                WebElement selectElement = d.findElement(By.id("select-example-1-id"));
                js.executeScript("arguments[0].scrollIntoView({block: 'center', behavior: 'instant'})", selectElement);
                System.out.println(selectElement.getAttribute("outerHTML"));
                Select select = new Select(selectElement);
                select.selectByIndex(0);
                System.out.println(select.getFirstSelectedOption().getAttribute("outerHTML"));
                return true;
            });
        } catch (RuntimeException err) {
            err.printStackTrace();
            System.exit(1);
        } finally {
            driver.quit();
        }
    }
}
