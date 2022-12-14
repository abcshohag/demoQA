package BoFA;

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
import org.openqa.selenium.support.ui.ExpectedConditions;
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
        driver = getWebDriver();
        //driver.manage().window().fullscreen();
        bofaCCPage = new BoFa_CC_Page();
        applicationPage = new CC_ApplicationPage();
        setTimeout(15000);
    }

    /*
        1. The following test method applies for the credit name that is mentioned in the Properties file.
        2. If no credit card is mentioned there, it will randomly select Credit Cards and applies for them.
        3. List of supported credit card can be found under BoFa_CC_Page class.
        4. Credit Card name has to match exactly with any one of the option from BoFa_CC_Page's cardsList MAP.
     */

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
        driver.get(bofaCCPage.pageUrl);

        String cardId = getCardId();
        WebElement randomCardApply = driver.findElement(By.cssSelector("div[class*='card-info visible'] a[id*='home_" + cardId + "' ]"));

        Address address = f.address();
        javaScriptExecutorClick(randomCardApply);

        Boolean multiPageApplication = driver.findElements(applicationPage.termAndConditionCheck).isEmpty();

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

        if(multiPageApplication){
            scrollToElementAndClick(applicationPage.submitAndContinue);
        }

        // Page #2
        javaScriptExecutorClick(applicationPage.yesUSCitizen);
        sendKeysToElement(applicationPage.ssnNumberInput, ssn);
        javaScriptExecutorClick(applicationPage.dualCitizenshipNo);
        selectByVisibleText(applicationPage.countrySelect, "United States");
        sendKeysToElement(applicationPage.dobInput, dob);

        if(multiPageApplication){
            scrollToElementAndClick(applicationPage.submitAndContinue);
        }

        // Page #3
        if(driver.findElement(applicationPage.employmentStatus).isEnabled()){
            selectByVisibleText(applicationPage.employmentStatus, "Employed");
        }
        selectByVisibleText(applicationPage.occupationSelect, "Engineer");
        sendKeysToElement(applicationPage.annualIncome, "120000");
        selectByVisibleText(applicationPage.incomeSource, "Employment");
        sendKeysToElement(applicationPage.monthlyHousingCost, "1400");

        if(multiPageApplication) {
            scrollToElementAndClick(applicationPage.submitAndContinue);
        }

        //Page #4
        javaScriptExecutorClick(applicationPage.termAndConditionCheck);
        javaScriptExecutorClick(applicationPage.submitAndContinue);

        //Review Application Page and Assertion
        String reviewApplicationSectionText = driver.findElement(applicationPage.reviewContainer).getText();
        Assert.assertTrue(reviewApplicationSectionText.contains(firstName));
        Assert.assertTrue(reviewApplicationSectionText.contains(lastName));
        String maskedEmail = email.charAt(0) + "***" + email.substring(email.indexOf("@") - 1);
        Assert.assertTrue(reviewApplicationSectionText.contains(maskedEmail));
        Assert.assertTrue(reviewApplicationSectionText.contains(phone.substring(7)));
     }

     private String getCardId(){
         String cardId;
         String cardNameFromProperties = initializeProperties().getProperty("CardToApply");
         List<String> cardNames = new ArrayList<String>(bofaCCPage.cardsList.keySet());

         // 1. First check the properties file to see if a card name mentioned in there
         // 2. if the following occurs, get a random card and apply
         //     a. Card name is null or missing from properties
         //     b. Card name mentioned is not one of the card name listed under page object.

         if(cardNameFromProperties == null || cardNameFromProperties.isEmpty() || !bofaCCPage.cardsList.containsKey(cardNameFromProperties.trim())){
             System.out.println("List of all Cards: " + cardNames);
             int randomNumber = new Random().nextInt(cardNames.size());
             cardId = bofaCCPage.cardsList.get(cardNames.get(randomNumber));
             System.out.println("A random card Id: " + cardId);
             System.out.println("Applying to card: " + cardNames.get(randomNumber));
         }else{
             cardId = bofaCCPage.cardsList.get(cardNameFromProperties.trim());
         }
         return cardId;
     }



}
