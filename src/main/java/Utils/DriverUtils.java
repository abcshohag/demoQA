package Utils;
import com.google.common.collect.ImmutableMap;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.Command;
import org.openqa.selenium.remote.CommandExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.Response;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class DriverUtils {
    private DriverUtils(){}

    public static ChromeOptions enableChromeIncognito(){
        ChromeOptions option = new ChromeOptions();
        option.addArguments("--incognito");
        DesiredCapabilities d = new DesiredCapabilities();
        d.setCapability(ChromeOptions.CAPABILITY, option);
        return option.merge(d);
    }

    public static void setChromePath(){
        System.setProperty("webdriver.chrome.driver", "resources/mac_chromedriver/chromedriver");
    }

    //pass a value between 1 to 100 denoting percentage.
    public static void zoomOutToPercentage(WebDriver driver, double percentage){
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("document.body.style.zoom = '" + percentage  + "'");
    }

    public static void scrollToElement(WebDriver driver, WebElement el){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", el);
    }

    public static void scrollToElementAndClick(WebDriver driver, By selector){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(selector));
        driver.findElement(selector).click();
    }

    /*
        Some possible params for downloadThroughput and uploadThroughput are: (slowest to fastest)
                { 23000, 11000 }
                { 30000, 15000 }
                { 40000, 20000 }
                { 50000, 20000 }
                { 75000, 20000 }
                { 100000, 20000 }    */
    public static void setNetworkThrottle(WebDriver driver, int downThroughput, int upThroughput, int additionalLatency) throws IOException {
        if (downThroughput > 0 && upThroughput > 0) {
            CommandExecutor executor = ((ChromeDriver) driver).getCommandExecutor();
            Map map = new HashMap();
            map.put("offline", false);
            map.put("latency", 5 + additionalLatency);

            map.put("download_throughput", downThroughput);
            map.put("upload_throughput", upThroughput);
            Response response = executor.execute(
                    new Command(((ChromeDriver) driver).getSessionId(),
                            "setNetworkConditions",
                            ImmutableMap.of("network_conditions", ImmutableMap.copyOf(map))));
        }
    }


    public static void setTimeout(WebDriver d, int ms){
        //This timeout is used to specify the amount of time the driver
        // should wait while searching for an element if it is not immediately present.
        d.manage().timeouts().implicitlyWait(Duration.ofMillis(ms));

        //This is used to set the amount of time the WebDriver must wait for an asynchronous
        // script to finish execution before throwing an error.
        d.manage().timeouts().scriptTimeout(Duration.ofMillis(ms));

        //This sets the time to wait for a page to load completely before throwing an error.
        // If the timeout is negative, page loads can be indefinite.
        d.manage().timeouts().pageLoadTimeout(Duration.ofMillis(ms));
    }
}
