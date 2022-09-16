import Pages.BasePage;
import Pages.Element_TextBox;
import Utils.DriverUtils;
import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TextBoxTest extends BasePage {
    WebDriver driver;
    Element_TextBox textBox;


    @BeforeClass
    void setup(){
        driver = DriverUtils.getWebDriver();
        textBox = new Element_TextBox();
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
        DriverUtils.scrollWaitAndClickUsingJs(submitButton, 5000);
        Assert.assertTrue(driver.findElement(textBox.output).getText().contains(name));
    }

}
