package Misc_Exercise;

import org.apache.poi.ss.usermodel.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class DP {
    static final String EXCEL_FILE_PATH = System.getProperty("user.dir")+ "/resources/test_data/Mock_Data1000.xls";

    @DataProvider(name = "data-provider")
    public Object[][] dpMethod() throws IOException {
        Workbook workbook = WorkbookFactory.create(new FileInputStream(EXCEL_FILE_PATH));
        Sheet sheet = workbook.getSheetAt(0);
        Row[][] table = null;
        int row = 0;
        int col = 0;
        for(Row r : sheet){
            table[row][col] = r;
        }
        Row record1 = sheet.getRow(1);

        return new Object[][] {{"First-Value"}, {"Second-Value"}};
    }

    @Test (dataProvider = "data-provider")
    public void myTest (Row row) {
        System.out.println("myTest");
        for(Cell c : row){
            System.out.println(c.toString());
        }
    }
}