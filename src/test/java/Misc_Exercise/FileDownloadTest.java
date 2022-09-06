package Misc_Exercise;

import Utils.DriverUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.File;

public class FileDownloadTest {
    public static void main(String[] args) throws InterruptedException {
        String fileName = "upload-me.txt";
        WebDriver driver = WebDriverManager.chromedriver().create();
        driver.get("http://the-internet.herokuapp.com/download");
        driver.findElement(By.partialLinkText(fileName)).click();
        String fullFilePath = DriverUtils.initializeProperties().getProperty("downloadFolder") + fileName;
        System.out.println(DriverUtils.isFileExist(fullFilePath));
        new File(fullFilePath).delete();
    }
}
