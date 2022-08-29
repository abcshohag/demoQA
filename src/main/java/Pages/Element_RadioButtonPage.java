package Pages;

import org.openqa.selenium.By;

public class Element_RadioButtonPage {
    public By yesRadio = new By.ByCssSelector("label[for='yesRadio']");
    public By successMessage = new By.ByCssSelector(".text-success");

    public By noRadio = new By.ByXPath("//label[@for='noRadio']");
}
