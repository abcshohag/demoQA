import Pages.Element_TextBox;
import Utils.DriverUtils;
import org.apache.poi.ss.usermodel.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class TextBoxTest_UsingExcel {
    static final String EXCEL_FILE_PATH = System.getProperty("user.dir") + "/resources/test_data/TestDataForTextBox.xls";
    WebDriver driver;
    Element_TextBox textBox;

    @BeforeClass
    void setup(){
        driver = DriverUtils.getWebDriver();
        textBox = new Element_TextBox();
        driver.get("https://demoqa.com/text-box");
    }

    @Test
    void testForm() throws IOException {
        Workbook workbook = WorkbookFactory.create(new FileInputStream(EXCEL_FILE_PATH));
        Sheet sheet = workbook.getSheetAt(0);
        Row record1 = sheet.getRow(1);

        String name = record1.getCell(0).toString();
        String email = record1.getCell(1).toString();
        String currAddress = record1.getCell(2).toString();
        String permAddress = record1.getCell(3).toString();
        textBox.populateFormAndClick(driver, name, email, currAddress, permAddress);
        Assert.assertTrue(driver.findElement(textBox.output).getText().contains(name));
    }

    @AfterClass
    void wrapUp(){
        driver.quit();
    }
}
