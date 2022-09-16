package hamdan;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

public class baseUtils {
   public static Properties prop;

    public static void setChromePath() {
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver");
    }




   public static Properties initializeProperties() {
        if (prop != null)
           return prop;
     prop= new Properties();
        try {
           FileInputStream ip = new FileInputStream("demoqa.properties");
           prop.load(ip);
       } catch (Exception e) {
           System.out.println("Exception occurred during config initialization. " + e.getMessage());
            Reporter.log("Failed to load properties file. Error: " + e.getMessage());
       }
       return prop;
    }

    public static WebDriver getWebDriver(){
        if(prop == null)
            initializeProperties();
        String browser =prop.getProperty("browser");
        WebDriver driver;
       if(browser == null || browser.equalsIgnoreCase("Chrome")){
           driver = WebDriverManager.chromedriver().create();
        } else if (browser.equalsIgnoreCase("headless")) {
            System.out.println("Setting headless browser");
           var chromeOptions = new ChromeOptions();
          chromeOptions.addArguments("--headless");
            chromeOptions.addArguments("--disable-gpu");
           chromeOptions.addArguments("--window-size=1280,800");
           chromeOptions.addArguments("--allow-insecure-localhost");
            WebDriverManager.chromedriver().setup();
           driver = new ChromeDriver(chromeOptions);
       }else if (browser.equalsIgnoreCase("safari")) {
            driver = WebDriverManager.safaridriver().create();
        }else if (browser.equalsIgnoreCase("firefox") || browser.equalsIgnoreCase("mozilla")) {
          driver = WebDriverManager.firefoxdriver().create();
        }else{
           driver = WebDriverManager.chromedriver().create();
       }
        return driver;
    }
    public static void scrollAndClick(WebDriver driver, By selector) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(selector));
        driver.findElement(selector).click();
    }

    public static void waitAndClick(WebDriver driver, By elementSelector, int ms){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(ms));
        wait.until(ExpectedConditions.elementToBeClickable(elementSelector));
        baseUtils.scrollAndClick (driver, elementSelector);
    }
    public static void scroll(WebDriver driver, By selector) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(selector));
        driver.findElement(selector)
        ;
    }
    public static void clickUsingJS(WebDriver driver, WebElement element){
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }
    public static void scrollToElement(WebDriver driver, WebElement el){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", el);
    }


}

















