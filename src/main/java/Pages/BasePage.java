package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BasePage {
    public By mainHeader = new By.ByCssSelector(".main-header");
    public By footerText = new By.ByCssSelector("footer span");
    public By logo = new By.ByCssSelector("header img");
    public String baseUrl = "https://demoqa.com";
}
