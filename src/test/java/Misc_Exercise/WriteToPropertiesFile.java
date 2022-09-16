package Misc_Exercise;

import Utils.DriverUtils;
import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class WriteToPropertiesFile {
    public static void main(String[] args) throws InterruptedException, IOException {
        WebDriver driver = WebDriverManager.chromedriver().create();
        Faker faker = new Faker();
        driver.get("https://demoqa.com/register");
        driver.manage().window().maximize();
//        DriverUtils.zoomOutToPercentage(driver, .50);
        driver.findElement(By.cssSelector("#firstname")).sendKeys(faker.name().firstName());
        driver.findElement(By.cssSelector("#lastname")).sendKeys(faker.name().lastName());


        String userName = faker.pokemon().name().replaceAll(" ", "");
        String password = "Abc1234$$";


//        driver.findElement(By.cssSelector("#userName")).sendKeys(userName);
//        driver.findElement(By.cssSelector("#password")).sendKeys(password);

//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.cssSelector("iframe[title=reCAPTCHA]")));
//        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".recaptcha-checkbox"))).click();

        Thread.sleep(3000);
        DriverUtils.scrollToElementAndClick(By.cssSelector("#register"));
        System.out.println((driver.findElement(By.cssSelector("#userName")).getAttribute("class")));


//        Properties prop = DriverUtils.initializeProperties();
//        prop.setProperty("userName", userName);
//        prop.setProperty("password", password);
//        DriverUtils.storeProperties(prop);


        //properties test
//        Properties prop = DriverUtils.initializeProperties();
//        prop.setProperty("userName", "sidKhannnnnn");
//        prop.setProperty("password", "bleh123$$");
//        DriverUtils.storeProperties(prop);
    }
}
