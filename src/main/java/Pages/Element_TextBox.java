package Pages;

import Utils.BaseMethod;
import Utils.DriverUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Element_TextBox{
    BaseMethod bm;
    public Element_TextBox(BaseMethod bm){
        this.bm = bm;
    }

    public By userName = new By.ByCssSelector("#userName");
    public By userEmail = new By.ByCssSelector("#userEmail");
    public By currentAddress = new By.ByCssSelector("#currentAddress");
    public By permanantAddress = new By.ByCssSelector("#permanentAddress");

    public By submitButton = new By.ByCssSelector("#submit");

    public By output = new By.ByCssSelector("#output");

    public String pageUrl = "https://demoqa.com/text-box";



    public void populateFormAndClick(String name, String email, String currAdd, String perAdd){
        bm.sendKeysToElement(userName, name);
        bm.sendKeysToElement(userEmail, email);
        bm.sendKeysToElement(currentAddress, currAdd);
        bm.sendKeysToElement(permanantAddress, perAdd);
        bm.scrollWaitAndClickUsingJs(submitButton, 5000);
    }
}
