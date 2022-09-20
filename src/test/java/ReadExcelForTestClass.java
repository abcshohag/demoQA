import Utils.BaseMethod;
import Utils.DriverUtils;
import Utils.ExcelUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.Driver;


public class ReadExcelForTestClass extends BaseMethod {
    static final String EXCEL_FILE_PATH = System.getProperty("user.dir") + "/resources/test_data/Mock_Data.xlsx";
    WebDriver driver;

    @BeforeClass
    void init(){
        driver = getWebDriver();
    }

    @DataProvider(name = "loadFormData")
    public static Object[][] dataLoad() throws Exception {
        return ExcelUtils.getTableArray(EXCEL_FILE_PATH, "Small Data Set");
    }

    @Test(dataProvider = "loadFormData")
    void testForm(Double str1, String str2, String str3, String str4, String str5, String str6, String str7, String str8){
        By firstCard = new By.ByXPath("//a[contains(@href, '4061200')] [contains(text(),\"Apply Now\")] [not(contains(@class,'small'))]");

    }
}
