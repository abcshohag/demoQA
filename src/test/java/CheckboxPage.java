import Pages.ElementsPage;
import Utils.DriverUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.io.IOException;

public class CheckboxPage {
    static WebDriver driver;
    static ElementsPage elementsPage;

    @BeforeClass
    void setup() throws IOException {
        driver = DriverUtils.getWebDriver();
        driver.manage().window().maximize();
        elementsPage = new ElementsPage();
        driver.get(elementsPage.pageUrl);
        driver.findElement(elementsPage.checkbox_submenu).click();
    }

    @Test
    void testCheckboxPositive() throws InterruptedException {
        driver.findElement(By.cssSelector("#tree-node>ol>li>span>button")).click(); // expand home
        driver.findElement(By.cssSelector("#tree-node>ol>li>ol>li:nth-child(2)>span>button")).click(); //Expand Document
        driver.findElement(By.cssSelector("li:nth-child(2)>ol>li:last-child button")).click(); // Expand office

        driver.findElement(By.cssSelector("[for='tree-node-private']")).click(); //click private file
        WebElement el = driver.findElement(By.cssSelector("#result"));
        Assert.assertTrue( el.getText().contains("private") );

        //click general and assert the result again
        DriverUtils.scrollToElementAndClick(driver, By.cssSelector("[for='tree-node-general']")); //click general file

        Assert.assertTrue( el.getText().contains("private") && el.getText().contains("general") );
    }



    @AfterClass
    void wrapUp(){
        driver.quit();
    }

}
