import Utils.DriverUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SelectableGridPageTest {
    static WebDriver driver;

    @BeforeClass
    void setup(){
        driver = DriverUtils.getWebDriver();
        driver.get("https://demoqa.com/");

        //Scrolling down to Interaction and menu and clicking
        DriverUtils.scrollAndClick(driver, ".top-card:nth-child(5) .card-body");
        DriverUtils.scrollAndClick(driver, ".element-group:nth-child(5) #item-1");
        //clciking on the grid tab
        driver.findElement(By.cssSelector("#demo-tab-grid")).click();
    }

    @Test
    void testGrid() throws InterruptedException {
        testABox("#row1 li:nth-child(1)");
        testABox("#row1 li:nth-child(2)");
        testABox("#row1 li:nth-child(3)");

        testABox("#row2 li:nth-child(1)");
        testABox("#row2 li:nth-child(2)");
        testABox("#row2 li:nth-child(3)");

        testABox("#row3 li:nth-child(1)");
        testABox("#row3 li:nth-child(2)");
        testABox("#row3 li:nth-child(3)");
    }

    @Test
    void anotherTest(){
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(false);
    }

    void testABox(String selector) throws InterruptedException {
        //box ONE
        WebElement box =  driver.findElement(By.cssSelector(selector));
        box.click();
        Assert.assertTrue( box.getAttribute("class").contains("active") );
        box.click();
        Assert.assertFalse( box.getAttribute("class").contains("active") );
    }

    @AfterClass
    void wrapUp(){
        System.out.println("calling after class");
        driver.quit();
    }
}
