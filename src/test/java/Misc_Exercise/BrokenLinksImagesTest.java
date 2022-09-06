package Misc_Exercise;

import Pages.ElementsPage;
import Pages.HomePage;
import Utils.DriverUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class BrokenLinksImagesTest {
    static WebDriver driver;
    ElementsPage elementsPage;

    @BeforeClass
    void setup(){
        driver = DriverUtils.getWebDriver();
        driver.manage().window().maximize();
        elementsPage = new ElementsPage();
        driver.get(elementsPage.pageUrl);
        DriverUtils.scrollToElementAndClick(driver, elementsPage.brokenLinksImages_submenu);
    }

    @Test
    void brokenImageTest(){
        //this is a good image
        WebElement goodImage = driver.findElement(By.cssSelector("div:nth-child(2) > img:nth-child(2)"));

        if(goodImage.getAttribute("naturalWidth").equals("0")){
            Assert.fail("Good image is broken");
        }else{
            Assert.assertTrue(true);
        }

        //this is a broken image
        WebElement brokenImage = driver.findElement(By.cssSelector("div:nth-child(2) > img:nth-child(6)"));

        if(brokenImage.getAttribute("naturalWidth").equals("0")){
            Assert.assertTrue(true);
        }else{
            Assert.fail("Broken image isn't broken");
        }

        driver.quit();

    }
}
