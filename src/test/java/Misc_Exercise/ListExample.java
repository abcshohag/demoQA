package Misc_Exercise;

import Utils.BaseMethod;
import Utils.DriverUtils;
import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.util.List;

public class ListExample  extends BaseMethod {
    @Test
    void testMenu() throws InterruptedException {
        WebDriver driver = getWebDriver();
        driver.get("https://demoqa.com/");
        List<WebElement> el = driver.findElements(By.cssSelector(".mt-4"));
        Faker f = new Faker();

        int randomNumber = f.random().nextInt(0, el.size()-1);

        WebElement oneElement = el.get(randomNumber);

        String expected = oneElement.getText();

        oneElement.click();

        String actualText = driver.findElement(By.cssSelector(".main-header")).getText();

        Assert.assertEquals(actualText, expected);
    }

    @AfterClass
    void wrapUp(){
        quitWebdriver();
    }
}
