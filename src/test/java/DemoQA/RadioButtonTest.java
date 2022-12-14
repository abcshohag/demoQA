package DemoQA;

import Pages.Element_RadioButtonPage;
import Pages.ElementsPage;
import Utils.BaseMethod;
import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class RadioButtonTest  extends BaseMethod {
    WebDriver driver;
    ElementsPage elementsPage;
    Element_RadioButtonPage rb;

    @BeforeClass
    void getReady(){
        driver = getWebDriver();
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
    }

    @Test(priority = 2)
    void testNoRadio() throws InterruptedException {
        driver.findElement(rb.noRadio).click();
        String successMessage = driver.findElement(rb.successMessage).getText();
        Assert.assertFalse(successMessage.contains("No"));
    }

    @AfterClass
    void wrapUp(){
        quitWebdriver();
    }
}
