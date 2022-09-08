package Pages;

import org.openqa.selenium.By;
import java.util.Map;

public class HomePage extends BasePage{
    public String pageUrl = "https://demoqa.com/";
    public By banner =  new By.ByCssSelector("img.banner-image");
    public Map<String, By> menuItems =  Map.of(
            "Elements", new By.ByCssSelector(".top-card:nth-child(1)"),
            "Forms", new By.ByCssSelector(".top-card:nth-child(2)"),
            "Alerts, Frame & Windows", new By.ByCssSelector(".top-card:nth-child(3)"),
            "Widgets", new By.ByCssSelector(".top-card:nth-child(4)"),
            "Interactions", new By.ByCssSelector(".top-card:nth-child(5)"),
            "Book Store", new By.ByCssSelector(".top-card:nth-child(6)")
    );

}
