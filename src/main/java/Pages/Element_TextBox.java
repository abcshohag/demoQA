package Pages;

import Utils.DriverUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Element_TextBox extends ElementsPage{

    public By userName = new By.ByCssSelector("#userName");
    public By userEmail = new By.ByCssSelector("#userEmail");
    public By currentAddress = new By.ByCssSelector("#currentAddress");
    public By permanantAddress = new By.ByCssSelector("#permanentAddress");

    public By submitButton = new By.ByCssSelector("#submit");

    public By output = new By.ByCssSelector("#output");

    public String pageUrl = "https://demoqa.com/text-box";



    public void populateFormAndClick(String name, String email, String currAdd, String perAdd){
        sendKeysToElement(userName, name);
        sendKeysToElement(userEmail, email);
        sendKeysToElement(currentAddress, currAdd);
        sendKeysToElement(permanantAddress, perAdd);
        DriverUtils.scrollWaitAndClickUsingJs(submitButton, 5000);
    }
}
