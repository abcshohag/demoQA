package Misc_Exercise;

import Utils.BaseMethod;
import Utils.DriverUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class DynamicWaitExample  extends BaseMethod {
    @Test
    void dynamicPageLoading(){
        WebDriver driver = getWebDriver();
        driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
        setTimeout(15);
        driver.findElement(By.cssSelector("#start button")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated (By.cssSelector("#finish h4")));

        String result = driver.findElement(By.cssSelector("#finish h4")).getText();
        Assert.assertTrue(result.contains("Hello World!"));
    }

    @AfterClass
    void wrapUp(){
        quitWebdriver();
    }
}
