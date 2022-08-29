import Pages.Element_TextBox;
import Utils.DriverUtils;
import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TextBoxTest {
    WebDriver driver;

    @BeforeClass
    void setup(){
        driver = WebDriverManager.chromedriver().create();
    }

    @Test
    void testForm(){
        Element_TextBox textBox = new Element_TextBox();
        By submitButton = new By.ByCssSelector("#submit");


        Faker f = new Faker();
        String name = f.name().name();
        String email = f.name().username() + "@gmail.com";
        String currAddress = f.address().fullAddress();

//        textBox.populateFormAndClick(driver, name, email, currAddress, currAddress);

        DriverUtils.scrollWaitAndClickUsingJs(driver, submitButton, 5000);

        Assert.assertTrue(driver.findElement(textBox.output).getText().contains(name));


    }

}
