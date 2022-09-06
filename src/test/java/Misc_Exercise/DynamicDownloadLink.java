package Misc_Exercise;

import Utils.DriverUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.util.List;

public class DynamicDownloadLink {
    WebDriver driver;

    @BeforeClass
    void setup(){
        driver = DriverUtils.getWebDriver();
        driver.get("http://the-internet.herokuapp.com/download");
    }

    @Test
    void testDownload() throws InterruptedException {
//        WebElement ele = driver.findElement(By.cssSelector("a:nth-child(66)"));
//        ele.click();
//        Thread.sleep(5000);
//        String downloadedFilePath = DriverUtils.initializeProperties().getProperty("downloadFolder") + ele.getText();
//        System.out.println(downloadedFilePath);
//        File downloadedFile = new File(downloadedFilePath);
//        Assert.assertTrue(downloadedFile.exists());


        List<WebElement> elements = driver.findElements(By.cssSelector(".example a"));
        elements.get(elements.size()-1).click();
        Thread.sleep(5000);
        String downloadedFilePath1 = DriverUtils.initializeProperties().getProperty("downloadFolder") + elements.get( elements.size()-1 ).getText();
        System.out.println(downloadedFilePath1);
        File downloadedFile1 = new File(downloadedFilePath1);
        Assert.assertTrue(downloadedFile1.exists());

        driver.navigate().back();

    }

    @AfterClass
    void wraup(){
        driver.quit();
    }
}
