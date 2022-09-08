package Misc_Exercise;

import Pages.BoFa_CC_Page;
import Utils.BaseMethod;
import Utils.DriverUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class BOFA_CCApplicationTest extends BaseMethod {
    WebDriver driver;
    BoFa_CC_Page ccPage;
    @BeforeClass
    void setup(){
        driver = DriverUtils.getWebDriver();
        ccPage = new BoFa_CC_Page();
    }

    @Test
    void applyCC() throws InterruptedException {
        driver.get("https://www.bankofamerica.com/credit-cards/#filter");
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//        wait.until(ExpectedConditions.elementToBeClickable(ccPage.applyButton1)).click();
        Thread.sleep(3000);
        List<WebElement> buttons = driver.findElements(By.xpath("//div[ starts-with ( @ data-product-name,'Bank of America Customized Cash Rewards' ) ] [contains(@class, 'visible')]"));
        System.out.println(buttons.size());
    }

    @AfterClass
    void wrapUp(){

    }
}
