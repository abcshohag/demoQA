import Utils.DriverUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.annotations.Test;

public class DemoQAHomePageTest {
    static WebDriver driver;

    @BeforeClass
    void setup(){
        DriverUtils.setChromePath();
        driver = new ChromeDriver();
        driver.get("https://demoqa.com/");
    }

    @Test (priority = 1)
    void testTitle(){
        Assert.assertEquals(driver.getTitle(), "ToolsQA");
    }

    @Test (priority = 2)
    void varifyBannerIsVisible(){
        WebElement el = driver.findElement(By.cssSelector("img.banner-image"));
        Assert.assertTrue(el.isDisplayed());
    }

    @Test (priority = 3)
    void verifyIfTheLogoIsVisible(){
        WebElement el = driver.findElement(By.cssSelector("header img"));
        el.getText();
        Assert.assertTrue(el.isDisplayed());
    }

    @Test (priority = 4)
    void validateFooter(){
        WebElement el = driver.findElement(By.cssSelector("footer span"));
        String footerText = el.getText();
        System.out.println(footerText);
        Assert.assertTrue( footerText.contains("ALL RIGHTS RESERVED.") );
    }

    @AfterClass
    void wrapUp(){
        driver.quit();
    }
}
