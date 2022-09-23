package DemoQA;

import Pages.BasePage;
import Utils.BaseMethod;
import Utils.DriverUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class FileUploadTest extends BaseMethod {
    WebDriver driver;

    @BeforeClass
    void setup(){
        driver = getWebDriver();
        driver.get("http://the-internet.herokuapp.com/upload");
    }

    @Test
    void fileUploadTest() throws InterruptedException {
        WebElement fileUpload = driver.findElement(By.cssSelector("#file-upload"));
        String absolutePath = System.getProperty("user.dir") + "/resources/test_data/image.png";
        fileUpload.sendKeys(absolutePath);
        Thread.sleep(500);

        driver.findElement(By.cssSelector("#file-submit")).click();
        Assert.assertTrue(driver.findElement(By.cssSelector("#uploaded-files")).getText().contains("image.png"));
    }
}
