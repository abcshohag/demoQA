package Misc_Exercise;

import Pages.BasePage;
import Pages.ElementsPage;
import Utils.DriverUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;

public class DynamicPropertiesTest extends BasePage {
    static WebDriver driver;
    static ElementsPage elementsPage;

    @BeforeClass
    void setup() throws IOException {
        driver = DriverUtils.getWebDriver();
        driver.manage().window().maximize();
        elementsPage = new ElementsPage();
        driver.get(elementsPage.pageUrl);
        DriverUtils.scrollToElementAndClick(elementsPage.dynamicProperties_submenu);
    }

    @Test
    void testElementWaitForClickable(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#enableAfter"))).click();

        //This element will be clickable after 5 second of the page load
        Assert.assertTrue(driver.findElement(By.cssSelector("#enableAfter")).isEnabled());
    }
}
