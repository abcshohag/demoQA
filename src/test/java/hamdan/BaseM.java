package hamdan;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.Rectangle;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;

public class BaseM {
    public static Robot re;
    public static Select se;
    public static Alert al;
    public static Actions ac;

    public void saveFullPageScreenshot(String fullPath) throws IOException {
        System.out.println("screenshot is getting created at: " + fullPath);
        WebElement el = getWebDriver().findElement(By.cssSelector("html"));
        File mainPgaeScreenshot = (el).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(mainPgaeScreenshot, new File(fullPath));
    }


    @AfterMethod(alwaysRun = true, enabled = true)
    public void afterMethod(ITestResult result) throws Exception {
        if (result.getStatus() == ITestResult.FAILURE) {
            saveFullPageScreenshot("./src/test/resources/Reports/Images/" + result.getTestClass().getName() + "."
                    + result.getMethod().getMethodName() + ".png");
        }
        getWebDriver().quit();
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
        re = new Robot();
        re.keyPress(KeyEvent.VK_ENTER);
        re.keyRelease(KeyEvent.VK_ENTER);
    }


    /* To Press BACKSPACE Key using Robot */


    /* To Press DELETE Key using Robot */
    public void hitDelete() throws Exception {
        re = new Robot();
        re.keyPress(KeyEvent.VK_DELETE);
        re.keyRelease(KeyEvent.VK_DELETE);
    }


    /* To Select all the Text/Web Elements using ROBOT */
    public void selectAll() throws Exception {
        re = new Robot();
        re.keyPress(KeyEvent.VK_CONTROL);
        re.keyPress(KeyEvent.VK_A);
        re.keyRelease(KeyEvent.VK_CONTROL);
        re.keyRelease(KeyEvent.VK_A);
    }


    /* To Copy all the Selected Text/Web Elements using ROBOT */
    public void copy() throws Exception {
        re = new Robot();
        re.keyPress(KeyEvent.VK_CONTROL);
        re.keyPress(KeyEvent.VK_C);
        re.keyRelease(KeyEvent.VK_CONTROL);
        re.keyRelease(KeyEvent.VK_C);
    }


    /* To Paste all the Selected Text/Web Elements using ROBOT */
    public void paste() throws Exception {
        re = new Robot();
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
        re = new Robot();
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
    public void robotZoomOut() throws Exception {
        re = new Robot();
        re.keyPress(KeyEvent.VK_CONTROL);
        re.keyPress(KeyEvent.VK_SUBTRACT);
        re.keyRelease(KeyEvent.VK_SUBTRACT);
        re.keyRelease(KeyEvent.VK_CONTROL);
    }


    /* To ZoomIn */
    public void robotZoomIn() throws Exception {
        re = new Robot();
        re.keyPress(KeyEvent.VK_CONTROL);
        re.keyPress(KeyEvent.VK_ADD);
        re.keyRelease(KeyEvent.VK_ADD);
        re.keyRelease(KeyEvent.VK_CONTROL);
    }


    /* To ScrollUp using ROBOT */
    public void robotScrollUp() throws Exception {
        re = new Robot();
        re.keyPress(KeyEvent.VK_PAGE_UP);
        re.keyRelease(KeyEvent.VK_PAGE_UP);
    }


    /* To ScrollDown using ROBOT */
    public void robotScrollDown() throws Exception {
        re = new Robot();
        re.keyPress(KeyEvent.VK_PAGE_DOWN);
        re.keyRelease(KeyEvent.VK_PAGE_DOWN);
    }


    /* To ScrollUp using JavaScript Executor */
    public void scrollUp() throws Exception {
        ((JavascriptExecutor) getWebDriver()).executeScript("scroll(0, -100);");
    }


    /* To ScrollDown using JavaScript Executor */
    public void scrollDown() throws Exception {
        ((JavascriptExecutor) getWebDriver()).executeScript("scroll(0, 100);");
    }


    /* To Move cursor to the Desired Location */
    public void moveCursor(int X_Position, int Y_Position) throws Exception {
        re.mouseMove(X_Position, Y_Position);
    }


    /* To Accept the Alert Dialog Message */
    public void alertAccept() throws Exception {
        WebDriverWait wait = new WebDriverWait(getWebDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.alertIsPresent()).accept();
    }


    /* To Dismiss the Alert Dialog Message */
    public void alertDismiss() throws Exception {
        al = getWebDriver().switchTo().alert();
        al.dismiss();
    }


    /* To Get the Alert Messages */
    public String getAlertText() throws Exception {
        al = getWebDriver().switchTo().alert();
        String text = al.getText();
        Thread.sleep(2000);
        alertAccept();
        return text;
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
        ac = new Actions(getWebDriver());
        ac.moveToElement(element).build().perform();
    }


    /* To Perform Select Option by Visible Text */
    public void selectByVisibleText(WebElement element, String value) {
        se = new Select(element);
        se.selectByVisibleText(value);
    }


    /* To Perform Select Option by Index */
    public void selectByIndex(WebElement element, int value) {
        se = new Select(element);
        se.selectByIndex(value);
    }


    /* To Perform Select Option by Value */
    public void selectByValue(WebElement element, String value) {
        se = new Select(element);
        se.selectByValue(value);
    }


    /* To click a certain Web Element */
    public void click(WebElement element) {
        element.click();
    }


    /* To click a certain Web Element using DOM/ JavaScript Executor */
    public void javaScriptExecutorClick(WebElement element) {
        ((JavascriptExecutor) getWebDriver()).executeScript("return arguments[0].click();", element);
    }


    /* To Type at the specified location */
    public void sendKeysToElement(WebElement element, String value) {
        element.sendKeys(value);
    }

    /* To Type at the specified location */
    public void sendKeysToElement(By selector, String value) {
        getWebDriver().findElement(selector).sendKeys(value);
    }


    /* To Clear the content in the input location */
    public void clear(WebElement element) {
        element.clear();
    }


    /* To Drag and Drop from Source Locator to Destination Locator */
    public void dragAndDrop(WebElement Source, WebElement Destination) {
        ac = new Actions(getWebDriver());
        ac.dragAndDrop(Source, Destination);
    }


    /*To Drag from the given WebElement Location and Drop at the given WebElement location */
    public void dragAndDropTo(WebElement Source, int XOffset, int YOffset) throws Exception {
        ac = new Actions(getWebDriver());
        ac.dragAndDropBy(Source, XOffset, YOffset);
    }


    /*To Open a Page in New Tab */
    public void rightClick(WebElement element) {
        ac = new Actions(getWebDriver());
        ac.contextClick(element);
        ac.build().perform();
    }


    /*To Close Current Tab */
    public void closeCurrentTab() {
        getWebDriver().close();
    }


    /*To Perform Click and Hold Action */
    public void clickAndHold(WebElement element) {
        ac = new Actions(getWebDriver());
        ac.clickAndHold(element);
        ac.build().perform();
    }


    /*To Perform Click and Hold Action */
    public void doubleClick(WebElement element) {
        ac = new Actions(getWebDriver());
        ac.doubleClick(element);
        ac.build().perform();
    }


    /*To Switch To Frame By Index */
    public void switchToFrameByIndex(int index) throws Exception {
        getWebDriver().switchTo().frame(index);
    }


    /*To Switch To Frame By Frame Name */
    public void switchToFrameByFrameName(String frameName) throws Exception {
        getWebDriver().switchTo().frame(frameName);
    }


    /*To Switch To Frame By Web Element */
    public void switchToFrameByWebElement(WebElement element) throws Exception {
        getWebDriver().switchTo().frame(element);
    }


    /*To Switch out of a Frame */
    public void switchOutOfFrame() throws Exception {
        getWebDriver().switchTo().defaultContent();
    }


    /*To Get Tooltip Text */
    public String getTooltipText(WebElement element) {
        String tooltipText = element.getAttribute("title").trim();
        return tooltipText;
    }


    /*To Close all Tabs/Windows except the First Tab */
    public void closeAllTabsExceptFirst() {
        ArrayList<String> tabs = new ArrayList<String>(getWebDriver().getWindowHandles());
        for (int i = 1; i < tabs.size(); i++) {
            getWebDriver().switchTo().window(tabs.get(i));
            getWebDriver().close();
        }
        getWebDriver().switchTo().window(tabs.get(0));
    }


    /*To Print all the Windows */
    public void printAllTheWindows() {
        ArrayList<String> al = new ArrayList<String>(getWebDriver().getWindowHandles());
        for (String window : al) {
            System.out.println(window);
        }
    }

    public static WebDriver getWebDriver() {
        return getWebDriver();
    }

    public static void hitBackspace() throws Exception {
        re = new Robot();
        re.keyPress(KeyEvent.VK_BACK_SPACE);
        re.keyRelease(KeyEvent.VK_BACK_SPACE);
    }

}

