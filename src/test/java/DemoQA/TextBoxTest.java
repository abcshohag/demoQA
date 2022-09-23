package DemoQA;

import Pages.BasePage;
import Pages.Element_TextBox;
import Utils.BaseMethod;
import Utils.DriverUtils;
import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TextBoxTest extends BaseMethod {
    WebDriver driver;
    Element_TextBox textBox;


    @BeforeClass
    void setup(){
        driver = getWebDriver();
        textBox = new Element_TextBox(this);
        driver.get(textBox.pageUrl);

    }

    @Test
    void testForm() throws Exception {
        By submitButton = new By.ByCssSelector("#submit");
        robotZoomOut();

        Faker f = new Faker();
        String name = f.name().name();
        String email = f.name().username() + "@gmail.com";
        String currAddress = f.address().fullAddress();

        textBox.populateFormAndClick(name, email, currAddress, currAddress);
        scrollWaitAndClickUsingJs(submitButton, 5000);
        Assert.assertTrue(driver.findElement(textBox.output).getText().contains(name));
    }

}
