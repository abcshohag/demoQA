package Misc_Exercise;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class AlertTest {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = WebDriverManager.chromedriver().create();
        driver.get("https://demoqa.com/alerts");
        driver.findElement(By.cssSelector("#alertButton")).click();
        driver.switchTo().alert().accept();
        driver.findElement(By.cssSelector("#timerAlertButton")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.alertIsPresent()).accept();

        driver.findElement(By.cssSelector("#confirmButton")).click();
        wait.until(ExpectedConditions.alertIsPresent()).accept();

        Assert.assertTrue(driver.findElement(By.cssSelector("#confirmResult")).getText().contains("You selected Ok"));
        driver.findElement(By.cssSelector("#promtButton")).click();
        driver.switchTo().alert().sendKeys("Hi There");
        driver.switchTo().alert().accept();

        Assert.assertTrue(driver.findElement(By.cssSelector("#promptResult")).getText().contains("Hi There"));

    }
}
