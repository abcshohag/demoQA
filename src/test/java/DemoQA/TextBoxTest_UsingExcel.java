package DemoQA;

import Pages.Element_TextBox;
import Utils.BaseMethod;
import Utils.DriverUtils;
import Utils.ExcelUtils;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class TextBoxTest_UsingExcel extends BaseMethod {
    WebDriver driver;
    Element_TextBox textBox;
    static final String EXCEL_FILE_PATH = System.getProperty("user.dir") + "/resources/test_data/TestDataForTextBox.xlsx";

    @BeforeClass
    void setup() throws Exception {
        driver = getWebDriver();
        textBox = new Element_TextBox(this);
        driver.get(textBox.pageUrl);
        driver.manage().window().maximize();
    }


    @DataProvider(name = "loadFormData")
    public static Object[][] dataLoad() throws Exception {
        return ExcelUtils.getTableArray(EXCEL_FILE_PATH);
    }

    @Test(dataProvider = "loadFormData")
    void testForm(String name, String str2, String str3, String str4, String gender) throws Exception {
        //populating form
        driver.findElement(textBox.userName).sendKeys(name);
        driver.findElement(textBox.userEmail).sendKeys(str2);

        driver.findElement(textBox.currentAddress).sendKeys(str3);
        driver.findElement(textBox.permanantAddress).sendKeys(str4);

        scrollWaitAndClickUsingJs(textBox.submitButton, 5000);
        Assert.assertTrue( driver.findElement(textBox.output).getText().contains(name));
        driver.navigate().refresh();
    }

    @AfterClass
    void wrapUp(){
        quitWebdriver();
    }
}
