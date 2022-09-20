package Misc_Exercise;

import Utils.BaseMethod;
import Utils.DriverUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.File;

public class FileDownloadTest extends BaseMethod {
    public static void main(String[] args){
        String fileName = "upload-me.txt";
        WebDriver driver = WebDriverManager.chromedriver().create();
        driver.get("http://the-internet.herokuapp.com/download");
        driver.findElement(By.partialLinkText(fileName)).click();
        BaseMethod baseMethod = new BaseMethod();
        String fullFilePath = baseMethod.initializeProperties().getProperty("downloadFolder") + fileName;
        System.out.println(baseMethod.isFileExist(fullFilePath));
        new File(fullFilePath).delete();
    }
}
