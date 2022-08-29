package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Element_TextBox {

    By userName = new By.ByCssSelector("#userName");
    By userEmail = new By.ByCssSelector("#userEmail");
    By currentAddress = new By.ByCssSelector("#currentAddress");
    By permanantAddress = new By.ByCssSelector("#permanentAddress");

    public By submitButton = new By.ByCssSelector("#submit");

    public By output = new By.ByCssSelector("#output");

    public String pageUrl = "https://demoqa.com/text-box";



    public void populateFormAndClick(WebDriver driver, String name, String email, String currAdd, String perAdd){
        driver.findElement(userName).sendKeys(name);
        driver.findElement(userEmail).sendKeys(email);
        driver.findElement(currentAddress).sendKeys(currAdd);
        driver.findElement(permanantAddress).sendKeys(perAdd);
        driver.findElement(submitButton).click();
    }
}
