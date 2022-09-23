package DemoQA;

import Utils.BaseMethod;
import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Locale;

public class PracticePracticeForm  extends BaseMethod {
    WebDriver driver;

    @BeforeClass
    void getReady(){
        driver = getWebDriver();
        setTimeout(10000);
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/automation-practice-form");
        zoomOutToPercentage(.50);
    }



    @Test
    void populateForm() throws InterruptedException {
        Faker faker = new Faker(new Locale("us-US"));
        driver.findElement(By.cssSelector("#firstName")).sendKeys(faker.name().firstName());
        driver.findElement(By.cssSelector("#lastName")).sendKeys(faker.name().lastName());
        driver.findElement(By.cssSelector("#userEmail")).sendKeys(faker.name().username()+"@gmail.com");
        driver.findElement(By.cssSelector("#firstName")).sendKeys(faker.name().firstName());
        int randomBetween1to3 = faker.random().nextInt(1,3);
        WebElement randomGender = driver.findElement(By.cssSelector("input#gender-radio-" + randomBetween1to3 ));
        clickUsingJS(randomGender);
        driver.findElement(By.cssSelector("#userNumber")).sendKeys(faker.phoneNumber().cellPhone().replaceAll("[^0-9]", ""));
        selectDob();

        driver.findElement(By.cssSelector("#subjectsInput")).sendKeys("Math" + Keys.TAB);
        WebElement randomHobby = driver.findElement(By.cssSelector("input#hobbies-checkbox-" + randomBetween1to3));
        clickUsingJS(randomHobby);
        driver.findElement(By.cssSelector("#uploadPicture")).sendKeys(System.getProperty("user.dir") +"/resources/test_data/image.png");
        driver.findElement(By.cssSelector("#currentAddress")).sendKeys(faker.address().fullAddress());
        driver.findElement(By.cssSelector("#react-select-3-input")).sendKeys("NC" + Keys.ENTER);
        driver.findElement(By.cssSelector("#react-select-4-input")).sendKeys("Noi" + Keys.ENTER);
        scrollWaitAndClickUsingJs(By.cssSelector("button[type='submit']"), 5000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated (By.cssSelector(".modal-title")));
        String outputMessage = driver.findElement(By.cssSelector(".modal-title")).getText();
        Assert.assertEquals(outputMessage, "Thanks for submitting the form");
    }

    private void selectDob() {
        Faker faker = new Faker(new Locale("us-US"));
        //year select
        Select year = new Select(driver.findElement(By.cssSelector(".react-datepicker__year-select")));
        year.selectByValue( String.valueOf(faker.random().nextInt(1920, 2020)) );

        //month select
        Select month = new Select( driver.findElement(By.cssSelector(".react-datepicker__month-select")) );
        month.selectByValue(String.valueOf( faker.random().nextInt(0,11) ));

        Integer d = faker.random().nextInt(1, 28);
        String day = "";
        if(d < 10){
            day = "0" + d; //05
        }else{
            day = String.valueOf(d);
        }
        scrollWaitAndClickUsingJs(By.cssSelector(".react-datepicker__day--0" + day ), 5000);
    }

    @AfterClass
    void wrapUp(){
        quitWebdriver();
    }
}