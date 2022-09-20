import Pages.HomePage;
import Utils.BaseMethod;
import Utils.DriverUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.annotations.Test;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class DemoQAHomePageTest extends BaseMethod {
    WebDriver driver;
    HomePage homePage;

    @BeforeClass
    void setup(){
        driver = getWebDriver();
        driver.manage().window().maximize();
        homePage = new HomePage();
        driver.get(homePage.pageUrl);
    }

    @Test (priority = 2)
    void testTitle(){
        Assert.assertEquals(driver.getTitle(), "ToolsQA");
    }

    @Test (priority = 3)
    void varifyBannerIsVisible(){
        WebElement el = driver.findElement( homePage.banner );
        Assert.assertTrue(el.isDisplayed());
    }

    @Test (priority = 4)
    void verifyIfTheLogoIsVisible(){
        WebElement el = driver.findElement( homePage.logo );
        el.getText();
        Assert.assertTrue(el.isDisplayed());
    }

    @Test (priority = 5)
    void validateFooter(){
        WebElement el = driver.findElement( homePage.footerText );
        String f = el.getText();
        System.out.println( f.contains("ALL RIGHTS RESERVED") );
        Assert.assertTrue( f.contains("ALL RIGHTS RESERVED"));
    }

    @Test(priority = 1)
    void validateAllMenuPresent() throws IOException, InterruptedException {
        var wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        for(String menuName : homePage.menuItems.keySet()){
            scrollToElementAndClick(homePage.menuItems.get(menuName));
            Assert.assertEquals(driver.findElement(homePage.mainHeader).getText(), menuName);
            driver.navigate().back();
        }
    }

    @AfterClass
    void wrapUp(){
        quitWebdriver();
    }
}
