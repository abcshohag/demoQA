package Misc_Exercise;

import Pages.BoFA.BoFa_CC_Page;
import Utils.BaseMethod;
import Utils.DriverUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.List;

public class ReadPropertiesPracticE extends BaseMethod {

    public static void main(String[] args) throws Exception {
//        Faker f = new Faker(new Locale("en-US"));
//        for(int i = 0; i<5; i++) {
//            System.out.println(f.phoneNumber().cellPhone());
//            String address = f.address().fullAddress().toLowerCase();
//            System.out.println(address.split("Street: " + ",")[0]);
//            System.out.println(address.split("City: " + ",")[1]);
//            System.out.println(address.split("State: " + ",")[2].split("\\s")[0]);
//            System.out.println(address.split("Zip: " + ",")[2].split("\\s")[1]);
//        }

        String cardToFind = DriverUtils.initializeProperties().getProperty("CardToApply");
        String cardId = new BoFa_CC_Page().cardsList.get(cardToFind);
        WebDriver driver = DriverUtils.getWebDriver();
        driver.get("https://www.bankofamerica.com/credit-cards/#filter");
        Thread.sleep(3000);

        System.out.println("Looking for Card: " + cardToFind);
        System.out.println("Card Id: " + cardId);
        List<WebElement> cards = driver.findElements(By.cssSelector("div[class*='card-info visible'] a[id*='home_" + cardId + "' ]"));
        System.out.println("Final css selector: " + "div[class*='card-info visible'] a[id*='home_" + cardId + "' ]");
        System.out.println("No of card found: " + cards.size());
        System.out.println("Size of cards: " + cards.size());
        if(cards.size()==1){
            cards.get(0).click();
        }


//        for(WebElement el :  cards){
//            if(el.getText().contains(cardToFind)) {
//                System.out.print("entry(\"" + el.findElement(By.cssSelector("[data-font='cnx-regular']")).getText().replaceAll("®", "") + "\", ");
//                String data_attribute = el.getAttribute("data-campaign");
//                System.out.println("\""  + data_attribute.substring(0, data_attribute.indexOf("~")) + "\")," );
//            }
//        }

//        driver.quit();


    }
}
