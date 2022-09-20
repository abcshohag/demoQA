import Utils.BaseMethod;
import Utils.DriverUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class JsAlertTest extends BaseMethod {
    WebDriver driver;
    @BeforeClass
    void setup(){
        driver = getWebDriver();
        driver.get("https://demoqa.com/alerts");
    }

    @Test
    void alertBoxTest() throws InterruptedException {
        // https://www.guru99.com/alert-popup-handling-selenium.html

        //click first alert
        driver.findElement(By.cssSelector("#alertButton")).click();
        alertAccept();

        //click second alert
        driver.findElement(By.cssSelector("#timerAlertButton")).click();
        WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(15000));
        w.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();

        //click third alert box - Click OK
        driver.findElement(By.cssSelector("#confirmButton")).click();
        Thread.sleep(2000);
        driver.switchTo().alert().accept();
        Assert.assertTrue(driver.findElement(By.cssSelector("#confirmResult")).getText().contains("Ok"));

        //click third alert box - click Cancel
        driver.findElement(By.cssSelector("#confirmButton")).click();
        driver.switchTo().alert().dismiss();
        Thread.sleep(2000);
        Assert.assertTrue(driver.findElement(By.cssSelector("#confirmResult")).getText().contains("Cancel"));

        //click fourth alert box
        driver.findElement(By.cssSelector("#promtButton")).click();
        driver.switchTo().alert().sendKeys("Hello");
        Thread.sleep(2000);
        driver.switchTo().alert().accept();
        Assert.assertTrue(driver.findElement(By.cssSelector("#promptResult")).getText().contains("Hello"));
    }
}
