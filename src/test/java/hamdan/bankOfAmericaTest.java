package hamdan;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.*;


import java.util.List;
import java.util.Locale;

public class bankOfAmericaTest {
    WebDriver driver;

    static final String ExcelFilePath = System.getProperty("user.dir") + "/resources/Mock_Data.xlsx";

    @DataProvider
            (name = "loadFromData")
    public static Object[][] dataLoad() throws Exception {
        return ExcelUtils.getTableArray(ExcelFilePath, "Small Data Set");
    }


    @BeforeClass
    void setup() throws InterruptedException {
        driver =baseUtils.getWebDriver();

    }

    @Test(dataProvider = "loadFromData")
    void HamdanTestFrom(double id, String FirstName, String lastName, String gender, String DoB, String SSN, String Phone, String email) throws Exception {
        driver.get("https://www.bankofamerica.com/credit-cards/#filter");
        Thread.sleep(2000);
        driver.manage().window().fullscreen();
        Thread.sleep(1000);
        driver.findElement(new By.ByXPath("//*[@id=\"home_4060812~NV~en_US\"]")).click();
        driver.manage().window().fullscreen();
        Thread.sleep(5000);
        baseUtils.scroll(driver, By.cssSelector("#customerFirstName"));
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("#customerFirstName")).sendKeys(FirstName);
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("#customerLastName")).sendKeys(lastName);
        Thread.sleep(1000);
        baseUtils.scroll(driver, By.cssSelector("#customerResidentialAddressOne"));
        Thread.sleep(1000);
        Faker f = new Faker(new Locale("en-US"));
        driver.findElement(By.cssSelector("#customerResidentialAddressOne")).sendKeys(f.address().buildingNumber() + " wood ave");
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("#customerAddressCity")).sendKeys(f.address().city());
        Thread.sleep(1000);


        driver.findElement(By.cssSelector("#customerAddressState")).sendKeys(f.address().state());
        String state = driver.findElement(By.cssSelector("#customerAddressState")).getAttribute("data-val");
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("#customerAddressInput")).sendKeys(f.address().zipCodeByState(state));
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("#customerPrimaryPhoneNumber")).sendKeys(Phone);
        Thread.sleep(2000);
        WebElement Phonetype = driver.findElement(By.cssSelector("[for='phoneNumberMobile']"));
        baseUtils.clickUsingJS(driver, Phonetype);
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("#customerEmailAddress")).sendKeys(email);
        Thread.sleep(2000);

        driver.findElement(By.xpath("//*[@id=\"icaiContinueButton\"]")).click();
        Thread.sleep(5000);
        driver.manage().window().fullscreen();
        Thread.sleep(2000);
        baseUtils.scrollAndClick(driver, By.cssSelector("#customerUsCitizenYes"));
        Thread.sleep(2000);
        baseUtils.clickUsingJS(driver, driver.findElement(By.cssSelector("#customerUsCitizenYes")));
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("#customerSocialSecurityNumber")).sendKeys(SSN);
        Thread.sleep(2000);
        baseUtils.scrollAndClick(driver, By.cssSelector("#customerDualCitizenshipNo"));
        Thread.sleep(2000);
        WebElement Country = driver.findElement(By.cssSelector("#customerCountryOfResidence"));
        Select n = new Select(Country);
        n.selectByIndex(1);
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("#customerBirthDate")).sendKeys(DoB);
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"icaiContinueButton\"]")).click();
        Thread.sleep(2000);
        WebElement employment = driver.findElement(By.cssSelector("#employmentStatus"));
        Select m = new Select(employment);
        m.selectByIndex(1);
        Thread.sleep(2000);
        WebElement occupation = driver.findElement(By.cssSelector("#occupation"));
        Select occu = new Select(occupation);
        occu.selectByIndex(6);
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("#annualSalary")).sendKeys(f.numerify("200000"));
        Thread.sleep(2000);
        WebElement sourceOfIncome = driver.findElement(By.cssSelector("#incomeSource"));
        Select soi = new Select(sourceOfIncome);
        soi.selectByIndex(1);
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("#monthlyHousingPayment")).sendKeys(f.numerify("500"));
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"icaiContinueButton\"]")).click();
        Thread.sleep(2000);
        baseUtils.scroll(driver, By.cssSelector("#termsAndConditionsAcknowledgement"));
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("#termsAndConditionsCheckBox")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"icaiContinueButton\"]")).click();
        Thread.sleep(1000);

        Assert.assertTrue(driver.findElement(By.cssSelector("#sectionHeader > div > div > h2")).getText().contains("Review your information"));
        Thread.sleep(2000);
    }

    @AfterClass
    void Quit() {
        driver.quit();
    }
}

