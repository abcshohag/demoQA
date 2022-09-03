import Utils.DriverUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class FileUploadTest {
    WebDriver driver;

    @BeforeClass
    void setup(){
        driver = DriverUtils.getWebDriver();
        driver.get("http://the-internet.herokuapp.com/upload");
    }

    @Test
    void fileUploadTest() throws InterruptedException {
        WebElement fileUpload = driver.findElement(By.cssSelector("#file-upload"));
        String absolutePath = DriverUtils.initializeProperties().getProperty("projectPath") + "resources/test_data/image.png";
        fileUpload.sendKeys(absolutePath);
        Thread.sleep(2000);

        driver.findElement(By.cssSelector("#file-submit")).click();
        Thread.sleep(2000);

        Assert.assertTrue(driver.findElement(By.cssSelector("#uploaded-files")).getText().contains("image.png"));
        Thread.sleep(5000);
        driver.quit();
    }
}
