import Pages.Widget_TooltipPage;
import Utils.BaseMethod;
import Utils.DriverUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

public class TooltipTest  extends BaseMethod {
    WebDriver driver;
    Widget_TooltipPage tooltipPage;

    @BeforeClass
    void setup(){
        driver = getWebDriver();
        tooltipPage = new Widget_TooltipPage();
        driver.get(tooltipPage.pageUrl);
    }

    @Test
    void testTooltip() throws InterruptedException {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(tooltipPage.hoverButtonSelector));
        actions.build().perform();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5000));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(tooltipPage.toolTip));

        Assert.assertTrue(element.getText().contains("You hovered over the Button"));
    }

    @AfterClass
    void wrapUp(){
        quitWebdriver();
    }
}
