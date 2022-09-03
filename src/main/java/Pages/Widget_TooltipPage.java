package Pages;

import org.openqa.selenium.By;

public class Widget_TooltipPage {
    public String pageUrl = "https://demoqa.com/tool-tips";
    public By hoverButtonSelector = new By.ByCssSelector("#toolTipButton");
    public By toolTip = new By.ByCssSelector(".tooltip-inner");
}
