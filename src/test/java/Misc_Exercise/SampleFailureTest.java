package Misc_Exercise;


import Utils.DriverUtils;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

//This test will fail due to various reason on purpose.
public class SampleFailureTest {
    WebDriver driver;

    @BeforeClass
    void init(){
        driver = DriverUtils.getWebDriver();
    }


    @Test(priority = 1)
    void noDriverInitialized(){
        driver.quit();
        Assert.assertThrows(NoSuchSessionException.class,
                () -> driver.get("www.google.com"));
        driver = DriverUtils.getWebDriver();

    }



    @Test(priority = 2)
    void invalidSelector(){
        driver.get("http://www.google.com");
        Assert.expectThrows(InvalidSelectorException.class,
                () -> driver.findElement(By.xpath("#$@%@%^###$^#@$^@#$^")));
    }

    @Test(priority = 3)
    void timeoutException(){
        ((JavascriptExecutor)driver).executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        driver.get("http://twitter.com");
        List<String> windows = driver.getWindowHandles().stream().toList();
        driver.close();
        driver.switchTo().window(windows.get(windows.size()-1));
    }

    @Test(priority = 4)
    void failingTest_4(){

    }

    @AfterClass
    void wrapup(){
        DriverUtils.quitWebdriver();
    }
}
