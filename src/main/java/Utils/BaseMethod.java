package Utils;

import com.google.common.collect.ImmutableMap;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.Command;
import org.openqa.selenium.remote.CommandExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.Response;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.Rectangle;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class BaseMethod extends DriverUtils {
    public Robot re;
    public Select se;
    public Actions ac;
    public WebDriverWait wait;
    public Properties prop;
    private static WebDriver driver;

    @AfterMethod(alwaysRun = true, enabled = true)
    public void afterMethod(ITestResult result) throws Exception {
        if (result.getStatus() == ITestResult.FAILURE) {
            saveFullPageScreenshot("./src/test/resources/Reports/Images/" + result.getTestClass().getName() + "." + result.getMethod().getMethodName() + ".png");
        }
    }

    public BaseMethod() {
        driver = getWebDriver();
        try {
            re = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
        wait = new WebDriverWait(getWebDriver(), Duration.ofSeconds(20));
        ac = new Actions(getWebDriver());
    }

    public WebDriver getWebDriver() {
        if (prop == null) initializeProperties();
        if (driver != null && !driver.toString().contains("(null)")) return driver;
        String browser = prop.getProperty("browser");
        System.out.println("### Creating new " + browser + " web driver.");
        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "chrome_headless":
                var chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--headless");
                chromeOptions.addArguments("--disable-gpu");
                chromeOptions.addArguments("--window-size=1280,800");
                chromeOptions.addArguments("--allow-insecure-localhost");
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(chromeOptions);
                break;
            case "safari":
                driver = WebDriverManager.safaridriver().create();
                break;
            case "firefox":
            case "mozilla":
                driver = WebDriverManager.firefoxdriver().create();
                break;
            case "ie":
            case "internet explorer":
                WebDriverManager.iedriver().setup();
                driver = new InternetExplorerDriver();
                break;
            case "edge":
                driver = WebDriverManager.edgedriver().create();
                break;
            case "edge_headless":
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--headless");
                driver = new EdgeDriver(edgeOptions);
                break;
            default:
                throw new NotFoundException("Browser Not Found. Please Provide a Valid Browser in the List");
        }
        return driver;
    }

    public void quitWebdriver() {
        if (driver != null && !driver.toString().contains("(null)")) {
            System.out.println("Browser open: closing now");
            driver.quit();
        }
    }

    public static WebDriver getChromeIncognito() {
        ChromeOptions option = new ChromeOptions();
        option.addArguments("--incognito");
        DesiredCapabilities d = new DesiredCapabilities();
        d.setCapability(ChromeOptions.CAPABILITY, option);
        return new ChromeDriver(option.merge(d));
    }

    public void saveFullPageScreenshot(String fullPath) throws IOException {
        WebElement el = driver.findElement(By.cssSelector("html"));
        File mainPgaeScreenshot = (el).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(mainPgaeScreenshot, new File(fullPath));
    }


    /* To get the Website Name */
    public String getUrlTitle(String url) throws Exception {
        URL aURL = new URL(url);
        String WebName = aURL.getHost();
        String WebSiteName = WebName.toUpperCase();
        return WebSiteName;
    }


    /* To Press ENTER Key using Robot */
    public void hitEnter() throws Exception {
        re.keyPress(KeyEvent.VK_ENTER);
        re.keyRelease(KeyEvent.VK_ENTER);
    }


    /* To Press BACKSPACE Key using Robot */
    public void hitBackspace() throws Exception {
        re.keyPress(KeyEvent.VK_BACK_SPACE);
        re.keyRelease(KeyEvent.VK_BACK_SPACE);
    }


    /* To Press DELETE Key using Robot */
    public void hitDelete() throws Exception {
        re.keyPress(KeyEvent.VK_DELETE);
        re.keyRelease(KeyEvent.VK_DELETE);
    }


    /* To Select all the Text/Web Elements using ROBOT */
    public void selectAll() throws Exception {
        re.keyPress(KeyEvent.VK_CONTROL);
        re.keyPress(KeyEvent.VK_A);
        re.keyRelease(KeyEvent.VK_CONTROL);
        re.keyRelease(KeyEvent.VK_A);
    }


    /* To Copy all the Selected Text/Web Elements using ROBOT */
    public void copy() throws Exception {
        re.keyPress(KeyEvent.VK_CONTROL);
        re.keyPress(KeyEvent.VK_C);
        re.keyRelease(KeyEvent.VK_CONTROL);
        re.keyRelease(KeyEvent.VK_C);
    }


    /* To Paste all the Selected Text/Web Elements using ROBOT */
    public void paste() throws Exception {
        re.keyPress(KeyEvent.VK_CONTROL);
        re.keyPress(KeyEvent.VK_V);
        re.keyRelease(KeyEvent.VK_CONTROL);
        re.keyRelease(KeyEvent.VK_V);
    }


    /* To Capture Screenshot(Replaces if already exists) */
    /*
     * Also, Make sure that the automation in running in the foreground to
     * capture the Image of the Browser. Else, It'll capture the open Window
     */
    public void robotScreenCapture(String robotImageName) throws Exception {
        Rectangle area = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        BufferedImage bufferedImage = re.createScreenCapture(area);
        // Save as PNG
        File file = new File(robotImageName);
        if (file.exists()) {
            file.delete();
        }
        ImageIO.write(bufferedImage, "png", file);
    }


    /* To ZoomOut */
    public void robotZoomOut() {
        re.keyPress(KeyEvent.VK_CONTROL);
        re.keyPress(KeyEvent.VK_SUBTRACT);
        re.keyRelease(KeyEvent.VK_SUBTRACT);
        re.keyRelease(KeyEvent.VK_CONTROL);
    }


    /* To ZoomIn */
    public void robotZoomIn() {
        re.keyPress(KeyEvent.VK_CONTROL);
        re.keyPress(KeyEvent.VK_ADD);
        re.keyRelease(KeyEvent.VK_ADD);
        re.keyRelease(KeyEvent.VK_CONTROL);
    }


    /* To ScrollUp using ROBOT */
    public void robotScrollUp() {
        re.keyPress(KeyEvent.VK_PAGE_UP);
        re.keyRelease(KeyEvent.VK_PAGE_UP);
    }


    /* To ScrollDown using ROBOT */
    public void robotScrollDown() {
        re.keyPress(KeyEvent.VK_PAGE_DOWN);
        re.keyRelease(KeyEvent.VK_PAGE_DOWN);
    }


    /* To ScrollUp using JavaScript Executor */
    public void scrollUp() {
        ((JavascriptExecutor) driver).executeScript("scroll(0, -100);");
    }

    /* To ScrollDown using JavaScript Executor */
    public void scrollDown() {
        ((JavascriptExecutor) driver).executeScript("scroll(0, 100);");
    }

    public WebElement scrolltoElementUsingJS(By locator) {
        WebElement el = driver.findElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", el);
        return el;
    }

    public WebElement scrolltoElementUsingJS(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        return element;
    }

    public WebElement scrolltoElement(By locator) {
        WebElement el = driver.findElement(locator);
        ac.moveToElement(el).build().perform();
        return el;
    }

    public WebElement scrolltoElement(WebElement element) {
        ac.moveToElement(element).build().perform();
        return element;
    }


    /* To Move cursor to the Desired Location */
    public void moveCursor(int X_Position, int Y_Position) {
        re.mouseMove(X_Position, Y_Position);
    }


    /* To Accept the Alert Dialog Message */
    public void alertAccept() {
        wait.until(ExpectedConditions.alertIsPresent()).accept();
    }


    /* To Dismiss the Alert Dialog Message */
    public void alertDismiss() {
        wait.until(ExpectedConditions.alertIsPresent()).dismiss();
    }


    /* To Get the Alert Messages */
    public String getAlertText() {
        return wait.until(ExpectedConditions.alertIsPresent()).getText();
    }


    /* To Upload a File using JAVA AWT ROBOT */
    public void fileUpload(String FileToUpload) throws Exception {
        Thread.sleep(5000);
        StringSelection filetocopy = new StringSelection(FileToUpload);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(filetocopy, null);
        Thread.sleep(1000);
        re = new Robot();
        re.keyPress(KeyEvent.VK_CONTROL);
        re.keyPress(KeyEvent.VK_V);
        re.keyRelease(KeyEvent.VK_V);
        re.keyRelease(KeyEvent.VK_CONTROL);
        re.keyPress(KeyEvent.VK_ENTER);
        re.keyRelease(KeyEvent.VK_ENTER);
    }


    /* To Perform a WebAction of Mouse Over */
    public void mouseHover(WebElement element) {
        ac = new Actions(driver);
        ac.moveToElement(element).build().perform();
    }


    /* To Perform Select Option by Visible Text */
    public void selectByVisibleText(WebElement element, String value) {
        se = new Select(element);
        se.selectByVisibleText(value);
    }


    /* To Perform Select Option by Visible Text */
    public void selectByVisibleText(By selector, String value) {
        se = new Select(driver.findElement(selector));
        se.selectByVisibleText(value);
    }

    /* To Perform Select Option by Index */
    public void selectByIndex(WebElement element, int value) {
        se = new Select(element);
        se.selectByIndex(value);
    }

    public void selectByIndex(By selector, int value) {
        se = new Select(driver.findElement(selector));
        se.selectByIndex(value);
    }


    /* To Perform Select Option by Value */
    public void selectByValue(WebElement element, String value) {
        se = new Select(element);
        se.selectByValue(value);
    }

    public void selectByValue(By selector, String value) {
        se = new Select(driver.findElement(selector));
        se.selectByValue(value);
    }


    /* To click a certain Web Element */
    public void click(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public void click(By selector) {
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(selector))).click();
    }


    /* To click a certain Web Element using DOM/ JavaScript Executor */
    public void javaScriptExecutorClick(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(); return arguments[0].click();", element);
    }

    public void javaScriptExecutorClick(By locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(); return arguments[0].click();", driver.findElement(locator));
    }

    /* To Type at the specified location */
    public void sendKeysToElement(WebElement element, String value) {
        element.sendKeys(value);
    }

    /* To Type at the specified location */
    public void sendKeysToElement(By selector, String value) {
        driver.findElement(selector).sendKeys(value);
    }


    /* To Clear the content in the input location */
    public void clear(WebElement element) {
        element.clear();
    }


    /* To Drag and Drop from Source Locator to Destination Locator */
    public void dragAndDrop(WebElement Source, WebElement Destination) {
        ac = new Actions(driver);
        ac.dragAndDrop(Source, Destination);
    }

    /*To Drag from the given WebElement Location and Drop at the given WebElement location */
    public void dragAndDropTo(WebElement Source, int XOffset, int YOffset) throws Exception {
        ac = new Actions(driver);
        ac.dragAndDropBy(Source, XOffset, YOffset);
    }

    /*To Open a Page in New Tab */
    public void rightClick(WebElement element) {
        ac = new Actions(driver);
        ac.contextClick(element);
        ac.build().perform();
    }


    /*To Close Current Tab */
    public void closeCurrentTab() {
        driver.close();
    }


    /*To Perform Click and Hold Action */
    public void clickAndHold(WebElement element) {
        ac = new Actions(driver);
        ac.clickAndHold(element);
        ac.build().perform();
    }


    /*To Perform Click and Hold Action */
    public void doubleClick(WebElement element) {
        ac = new Actions(driver);
        ac.doubleClick(element);
        ac.build().perform();
    }


    /*To Switch To Frame By Index */
    public void switchToFrameByIndex(int index) throws Exception {
        driver.switchTo().frame(index);
    }


    /*To Switch To Frame By Frame Name */
    public void switchToFrameByFrameName(String frameName) throws Exception {
        driver.switchTo().frame(frameName);
    }


    /*To Switch To Frame By Web Element */
    public void switchToFrameByWebElement(WebElement element) throws Exception {
        driver.switchTo().frame(element);
    }


    /*To Switch out of a Frame */
    public void switchOutOfFrame() throws Exception {
        driver.switchTo().defaultContent();
    }


    /*To Get Tooltip Text */
    public String getTooltipText(WebElement element) {
        String tooltipText = element.getAttribute("title").trim();
        return tooltipText;
    }


    /*To Close all Tabs/Windows except the First Tab */
    public void closeAllTabsExceptFirst() {
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        for (int i = 1; i < tabs.size(); i++) {
            driver.switchTo().window(tabs.get(i));
            driver.close();
        }
        driver.switchTo().window(tabs.get(0));
    }


    /*To Print all the Windows */
    public void printAllTheWindows() {
        ArrayList<String> al = new ArrayList<String>(driver.getWindowHandles());
        for (String window : al) {
            System.out.println(window);
        }
    }

    public void waitAndClick(By elementSelector, int ms) {
        new WebDriverWait(driver, Duration.ofSeconds(ms)).until(ExpectedConditions.elementToBeClickable(elementSelector));
        scrollToElementAndClick(elementSelector);
    }

    public void scrollAndClick(String cssSelector) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.cssSelector(cssSelector)));
        driver.findElement(By.cssSelector(cssSelector)).click();
    }

    //Use this method to click element that are almost impossible to click
    public void scrollWaitAndClickUsingJs(By elementSelector, int ms) {
        //1. Scroll using JS
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(elementSelector));

        //2.Wait for the element to be clickable
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(ms));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(elementSelector));

        String currentZoom = (String) ((JavascriptExecutor) driver).executeScript("return document.body.style.zoom;");

        //3. Zoom out to 50%
        zoomOutToPercentage(.50);

        //4. click using JS
        clickUsingJS(element);
        //5. Set old Zoom percentage
        if (currentZoom.length() > 0) {
            ((JavascriptExecutor) driver).executeScript("document.body.style.zoom=" + currentZoom);
        }
    }

    public Boolean isFileExist(String absoluteFilePath) {
        File tempFile = new File(absoluteFilePath);
        return tempFile.exists();
    }

    public Properties initializeProperties() {
        if (prop != null) {
            System.out.println("prop is not null");
            return prop;
        }
        prop = new Properties();
        try {
            FileInputStream ip = new FileInputStream("demoqa.properties");
            prop.load(ip);
        } catch (Exception e) {
            System.out.println("Exception occurred during config initialization. " + e.getMessage());
            Reporter.log("Failed to load properties file. Error: " + e.getMessage());
        }
        return prop;
    }

    public static void storeProperties(Properties property) throws IOException {
        property.store(new FileOutputStream("demoqa.properties"), null);
    }

    public void clickUsingJS(WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }

    //pass a value between 1 to 100 denoting percentage.
    public void zoomOutToPercentage(double percentage) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("document.body.style.zoom = '" + percentage + "'");
    }


    public void scrollToElementAndClick(By selector) {
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
            Response response = executor.execute(new Command(((ChromeDriver) driver).getSessionId(), "setNetworkConditions", ImmutableMap.of("network_conditions", ImmutableMap.copyOf(map))));
        }
    }


    public void setTimeout(int ms) {
        //This timeout is used to specify the amount of time the driver
        // should wait while searching for an element if it is not immediately present.
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(ms));

        //This is used to set the amount of time the WebDriver must wait for an asynchronous
        // script to finish execution before throwing an error.
        driver.manage().timeouts().scriptTimeout(Duration.ofMillis(ms));

        //This sets the time to wait for a page to load completely before throwing an error.
        // If the timeout is negative, page loads can be indefinite.
        driver.manage().timeouts().pageLoadTimeout(Duration.ofMillis(ms));
    }
}
