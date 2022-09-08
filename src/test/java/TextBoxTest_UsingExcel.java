import Pages.Element_TextBox;
import Utils.BaseMethod;
import Utils.DriverUtils;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class TextBoxTest_UsingExcel extends BaseMethod {
    WebDriver driver;
    Element_TextBox textBox;
    static final String EXCEL_FILE_PATH = System.getProperty("user.dir") + "/resources/test_data/TestDataForTextBox.xlsx";


    @BeforeMethod
    void setup() throws Exception {
        driver = DriverUtils.getWebDriver();
        textBox = new Element_TextBox();
        driver.get(textBox.pageUrl);
        driver.manage().window().maximize();
    }


    @DataProvider(name = "loadFormData")
    public static Object[][] dataLoad() throws Exception {
        return utils.ExcelUtils.getTableArray(EXCEL_FILE_PATH);
    }

    @Test(dataProvider = "loadFormData")
    void testForm(String str1, String str2, String str3, String str4) throws Exception {
        driver.findElement(textBox.userName).sendKeys(str1);
        driver.findElement(textBox.userEmail).sendKeys(str2);
        driver.findElement(textBox.currentAddress).sendKeys(str3);
        driver.findElement(textBox.permanantAddress).sendKeys(str4);
        robotZoomOut();
        robotZoomOut();
        DriverUtils.scrollWaitAndClickUsingJs(driver, textBox.submitButton, 5000);
        Assert.assertTrue( driver.findElement(textBox.output).getText().contains(str1));
        driver.navigate().refresh();
    }
}
