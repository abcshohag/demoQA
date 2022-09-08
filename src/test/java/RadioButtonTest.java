import Pages.Element_RadioButtonPage;
import Pages.ElementsPage;
import Utils.DriverUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class RadioButtonTest {
    WebDriver driver;
    ElementsPage elementsPage;
    Element_RadioButtonPage rb;

    @BeforeClass
    void getReady(){
        driver = DriverUtils.getWebDriver();
        elementsPage = new ElementsPage();
        rb = new Element_RadioButtonPage();
        driver.get(ElementsPage.pageUrl);
        driver.findElement(elementsPage.radioButton_submenu).click();
    }

    @Test(priority = 1)
    void testYesRadio() throws InterruptedException {
        driver.findElement(rb.yesRadio).click();
        String successMessage = driver.findElement(rb.successMessage).getText();
        Assert.assertTrue(successMessage.contains("Yes"));
        Thread.sleep(3000);
    }

    @Test(priority = 2)
    void testNoRadio() throws InterruptedException {
        driver.findElement(rb.noRadio).click();
        String successMessage = driver.findElement(rb.successMessage).getText();
        Assert.assertFalse(successMessage.contains("No"));
        Thread.sleep(3000);
    }
    
    @AfterClass
    void wrapUp(){
        driver.quit();
    }
}
