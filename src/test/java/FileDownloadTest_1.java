import Utils.DriverUtils;
import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.util.List;

public class FileDownloadTest_1 {
    WebDriver driver;

    @BeforeClass
    void setup(){
        driver = DriverUtils.getWebDriver();
        driver.get("http://the-internet.herokuapp.com/download");
    }

    @Test
    void testDownload() throws InterruptedException {
        List<WebElement> elements = driver.findElements(By.cssSelector(".example a"));
        int randNum = new Faker().random().nextInt(0, elements.size()-1);
        elements.get(randNum).click();
        Thread.sleep(5000);
        String fileName = elements.get(randNum).getText();
        String downloadedFilePath = DriverUtils.initializeProperties().getProperty("downloadFolder") + fileName;
        File downloadedFile = new File(downloadedFilePath);
        System.out.println(downloadedFile.getAbsolutePath());

        Assert.assertTrue(downloadedFile.exists());
        downloadedFile.delete();
        driver.quit();
    }
}
