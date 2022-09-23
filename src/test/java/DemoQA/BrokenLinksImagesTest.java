package DemoQA;

import Pages.ElementsPage;
import Utils.BaseMethod;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class BrokenLinksImagesTest extends BaseMethod {
    static WebDriver driver;
    ElementsPage elementsPage;

    @BeforeClass
    void setup(){
        driver = getWebDriver();
        driver.manage().window().maximize();
        elementsPage = new ElementsPage();
        driver.get(elementsPage.pageUrl);
        scrollToElementAndClick(elementsPage.brokenLinksImages_submenu);
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

        System.out.println(driver.toString());

        driver.quit();

        System.out.println(driver.toString());

    }
}