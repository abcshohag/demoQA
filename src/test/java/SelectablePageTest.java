import Utils.DriverUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SelectablePageTest {
    static WebDriver driver;

    @BeforeClass
    void setup(){
        driver = DriverUtils.getWebDriver();
        driver.get("https://demoqa.com/");
        //Scrolling down to Interaction and menu and clicking
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.cssSelector(".top-card:nth-child(5) .card-body")));
        driver.findElement(By.cssSelector(".top-card:nth-child(5) .card-body")).click();

        //scrolling and clicking on the submenu Selectable
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.cssSelector(".element-group:nth-child(5) #item-1")));
        driver.findElement(By.cssSelector(".element-group:nth-child(5) #item-1")).click();

        //clciking on the grid tab
        driver.findElement(By.cssSelector("#demo-tab-grid")).click();
    }


    @Test
    void testASingleGrid() throws InterruptedException {
        testGrid("#row1 li:nth-child(1)");
        testGrid("#row1 li:nth-child(2)");
        testGrid("#row1 li:nth-child(3)");


        testGrid("#row2 li:nth-child(1)");
        testGrid("#row2 li:nth-child(2)");
        testGrid("#row2 li:nth-child(3)");


        testGrid("#row3 li:nth-child(1)");
        testGrid("#row3 li:nth-child(2)");
        testGrid("#row3 li:nth-child(3)");
    }

    void testGrid(String selector) throws InterruptedException {
        //box ONE
        WebElement box =  driver.findElement(By.cssSelector(selector));
        box.click();
        Thread.sleep(1000);
        Assert.assertTrue( box.getAttribute("class").contains("active") );
        box.click();
        Thread.sleep(1000);
        Assert.assertFalse( box.getAttribute("class").contains("active") );
        Thread.sleep(1000);
    }





}
