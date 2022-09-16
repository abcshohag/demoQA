package Misc_Exercise;

import Pages.BoFA.BoFa_CC_Page;
import Pages.BoFA.CC_ApplicationPage;
import Utils.BaseMethod;
import Utils.DriverUtils;
import Utils.ExcelUtils;
import com.github.javafaker.Address;
import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;


public class BOFA_CCApplicationTest extends BaseMethod {
    static final String EXCEL_FILE_PATH = System.getProperty("user.dir") + "/resources/test_data/Mock_Data.xlsx";
    WebDriver driver;
    BoFa_CC_Page bofaCCPage;
    CC_ApplicationPage applicationPage;

    @BeforeMethod
    void init() throws IOException {
        driver = DriverUtils.getWebDriver();
        driver.manage().window().maximize();
        bofaCCPage = new BoFa_CC_Page();
        applicationPage = new CC_ApplicationPage();
        DriverUtils.setTimeout(60000);
    }

    @DataProvider(name = "loadFormData")
    public static Object[][] dataLoad() throws Exception {
        Object[] [] sheet = ExcelUtils.getTableArray(EXCEL_FILE_PATH, "Small Data Set");
        System.out.println(sheet.length);
        System.out.println(sheet[0].length);
        return sheet;
    }

    @Test(dataProvider = "loadFormData")
    void testForm(Double id, String firstName, String lastName, String gender, String dob, String ssn, String phone, String email) throws InterruptedException {
        System.out.println(firstName + " - " + lastName + " - " + gender + " - " + dob  + " - " + ssn + " - " + phone + " - " + email);
        Faker f = new Faker(new Locale("en-us"));
        Random r = new Random();

        driver.get(bofaCCPage.pageUrl);

        List<String> keysAsArray = new ArrayList<String>(bofaCCPage.cardsList.keySet());
        String cardId = bofaCCPage.cardsList.get(keysAsArray.get(r.nextInt(keysAsArray.size())));
        System.out.println("Applying to card: " + bofaCCPage.cardsList.get(cardId));
        WebElement randomCardApply = driver.findElement(By.cssSelector("div[class*='card-info visible'] a[id*='home_" + cardId + "' ]"));

        Address address = f.address();
        javaScriptExecutorClick(randomCardApply);

        //Start of Page #1
        sendKeysToElement(applicationPage.firstName, firstName);
        sendKeysToElement(applicationPage.lastName, lastName);
        sendKeysToElement(applicationPage.address1, address.streetAddress());
        sendKeysToElement(applicationPage.address1City, address.city());
        selectByVisibleText(applicationPage.address1State, address.state());
        String selectedState = driver.findElement(applicationPage.address1State).getAttribute("data-val");
        sendKeysToElement(applicationPage.address1Zip, f.address().zipCodeByState(selectedState));
        sendKeysToElement(applicationPage.phone, phone);
        javaScriptExecutorClick(applicationPage.mobilePhoneRadio);
        sendKeysToElement(applicationPage.emailSelector, email);
        DriverUtils.scrollToElementAndClick(applicationPage.submitAndContinue);

        // Page #2
        javaScriptExecutorClick(applicationPage.yesUSCitizen);
        sendKeysToElement(applicationPage.ssnNumberInput, ssn);
        javaScriptExecutorClick(applicationPage.dualCitizenshipNo);
        selectByVisibleText(applicationPage.countrySelect, "United States");
        sendKeysToElement(applicationPage.dobInput, dob);
        javaScriptExecutorClick(applicationPage.submitAndContinue);

        // Page #3
        selectByVisibleText(applicationPage.employmentStatus, "Employed");
        selectByVisibleText(applicationPage.occupationSelect, "Engineer");
        sendKeysToElement(applicationPage.annualIncome, "120000");
        selectByVisibleText(applicationPage.incomeSource, "Employment");
        sendKeysToElement(applicationPage.monthlyHousingCost, "1400");
        click(applicationPage.submitAndContinue);

        //Page #4
        javaScriptExecutorClick(applicationPage.termAndConditionCheck);
        javaScriptExecutorClick(applicationPage.submitAndContinue);

        //Review Application Page and Assertion
        String reviewApplicationSectionText = driver.findElement(applicationPage.reviewContainer).getText();
//        String actualCardName = driver.findElement(applicationPage.finalPageCardName).getText().replaceAll("®", "");
        Assert.assertTrue(reviewApplicationSectionText.contains(firstName));
        Assert.assertTrue(reviewApplicationSectionText.contains(lastName));
        String maskedEmail = email.charAt(0) + "***" + email.substring(email.indexOf("@") - 1);
        Assert.assertTrue(reviewApplicationSectionText.contains(maskedEmail));
        Assert.assertTrue(reviewApplicationSectionText.contains(phone.substring(7)));
//        Assert.assertEquals(actualCardName, bofaCCPage.cardsList.get(cardId));
        Thread.sleep(2000);
     }
}
