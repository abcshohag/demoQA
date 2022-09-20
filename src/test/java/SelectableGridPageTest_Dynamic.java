import Utils.BaseMethod;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class SelectableGridPageTest_Dynamic extends BaseMethod {
    static WebDriver driver;

    @BeforeClass
    void setup(){
        driver = getWebDriver();
        driver.get("https://demoqa.com/");

        //Scrolling down to Interaction and menu and clicking
        scrollAndClick(".top-card:nth-child(5) .card-body");
        scrollAndClick(".element-group:nth-child(5) #item-1");
        //clciking on the grid tab
        driver.findElement(By.cssSelector("#demo-tab-grid")).click();
    }

    @Test(threadPoolSize = 3, invocationCount = 6)
    void testGrid() throws InterruptedException {
        // This dynamic method should work even if there are more or less than 9 grid.
        List<WebElement> boxes = driver.findElements(By.cssSelector(".grid-container li"));
        for(int i = 0; i<boxes.size(); i++) {
            testABox(boxes.get(i));
        }

        /*
            A better way to write the above for loop:
            for(WebElement el : boxes) {
                testABox(el);
            }
            This way of writing for loop known as enhanced for loop
        */
    }

    @Test
    void anotherTest(){
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(false);
    }

    void testABox(WebElement box) throws InterruptedException {
        //box ONE
        box.click();
        Assert.assertTrue( box.getAttribute("class").contains("active") );
        box.click();
        Assert.assertFalse( box.getAttribute("class").contains("active") );
    }

    @AfterClass
    void wrapUp(){
        quitWebdriver();
    }
}
