import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class ReadExcelForTestClass {
    static final String EXCEL_FILE_PATH = System.getProperty("user.dir") + "/resources/test_data/TestDataForTextBox.xlsx";

    @DataProvider(name = "loadFormData")
    public static Object[][] dataLoad() throws Exception {
        return utils.ExcelUtils.getTableArray(EXCEL_FILE_PATH);
    }

    @Test(dataProvider = "loadFormData")
    void testForm(String str1, String str2, String str3, String str4){
        System.out.println("1: " + str1);
        System.out.println("1: " + str2);
        System.out.println("1: " + str3);
        System.out.println("1: " + str4);
    }
}
