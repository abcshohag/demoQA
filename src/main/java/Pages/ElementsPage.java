package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ElementsPage extends BasePage{
    public By textBox_submenu = new By.ByCssSelector(".element-group:first-child li:nth-child(1)");
    public By checkbox_submenu = new By.ByCssSelector(".element-group:first-child li:nth-child(2)");
    public By radioButton_submenu = new By.ByCssSelector(".element-group:first-child li:nth-child(3)");
    public By webTables_submenu = new By.ByCssSelector(".element-group:first-child li:nth-child(4)");
    public By Buttons_submenu = new By.ByCssSelector(".element-group:first-child li:nth-child(5)");
    public By links_submenu = new By.ByCssSelector(".element-group:first-child li:nth-child(6)");
    public By brokenLinksImages_submenu = new By.ByCssSelector(".element-group:first-child li:nth-child(7)");
    public By uploadAndDownload_submenu = new By.ByCssSelector(".element-group:first-child li:nth-child(8)");
    public By dynamicProperties_submenu = new By.ByCssSelector(".element-group:first-child li:nth-child(9)");
    public String pageUrl = "https://demoqa.com/elements";
}
