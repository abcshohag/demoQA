package DemoQA;

import Pages.ElementsPage;
import Utils.BaseMethod;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;

public class DynamicPropertiesTest extends BaseMethod {
    static WebDriver driver;
    static ElementsPage elementsPage;

    @BeforeClass
    void setup() throws IOException {
        driver = getWebDriver();
        driver.manage().window().maximize();
        elementsPage = new ElementsPage();
        driver.get(elementsPage.pageUrl);
        scrollToElementAndClick(elementsPage.dynamicProperties_submenu);
    }

    @Test
    void testElementWaitForClickable(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        By continueShopping = By.cssSelector("#enableAfter");
        wait.until(ExpectedConditions.elementToBeClickable(continueShopping)).click();

        //This element will be clickable after 5 second of the page load
        Assert.assertTrue(driver.findElement(By.cssSelector("#enableAfter")).isEnabled());
    }
}