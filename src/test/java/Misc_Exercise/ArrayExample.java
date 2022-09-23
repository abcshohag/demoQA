package Misc_Exercise;

import Utils.BaseMethod;
import Utils.DriverUtils;
import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.sql.Driver;
import java.util.List;
import java.util.Random;

public class ArrayExample extends BaseMethod {
    WebDriver driver;

    @BeforeClass
    void setup(){
        driver = getWebDriver();
        driver.get("http://the-internet.herokuapp.com/download");
    }

    @Test
    void test1(){
        Faker f = new Faker();
        List<WebElement> downloadLinks = driver.findElements(By.cssSelector(".example a"));
        Random r = new Random();
        if(downloadLinks.size() > 0 ){
            WebElement singleDownloadLink = downloadLinks.get(f.random().nextInt(0, downloadLinks.size()-1));
            singleDownloadLink.click();

        }
    }

    @AfterClass
    void wrapUp(){
        quitWebdriver();
    }
}
