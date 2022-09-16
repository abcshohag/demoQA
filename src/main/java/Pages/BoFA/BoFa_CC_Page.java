package Pages.BoFA;

import org.openqa.selenium.By;
import java.util.Map;
import static java.util.Map.entry;

public class BoFa_CC_Page {
    public String pageUrl = "https://www.bankofamerica.com/credit-cards/#filter";
    public By applyButton1 = new By.ByXPath("//a[contains(@href, '4061200')] [contains(text(),'Apply Now')] [not(contains(@class,'small'))]");

    public Map<String, String> cardsList = Map.ofEntries(
            entry("Bank of America Customized Cash Rewards", "4060812"),
            entry("Bank of America Unlimited Cash Rewards", "4060592"),
            entry("BankAmericard", "4060759"),
            entry("Bank of America Travel Rewards", "4061182"),
            entry("Bank of America Premium Rewards", "4060800"),
            entry("Bank of America Premium Rewards Elite", "4061200"),
            entry("Bank of America Customized Cash Rewards for Students", "4060817"),
            entry("Bank of America Unlimited Cash Rewards for Students", "4060606"),
            entry("BankAmericard for Students", "4060769"),
            entry("Bank of America Travel Rewards for Students", "4061189"),
//            entry("Bank of America Customized Cash Rewards Secured", "4060576"),
//            entry("Bank of America Unlimited Cash Rewards Secured", "4060603"),
//            entry("BankAmericard Secured", "4060781"),
            entry("Alaska Airlines Visa", "4061419"),
            entry("Susan G. Komen Customized Cash Rewards credit card", "4060021"),
            entry("Free Spirit Travel More World Elite Mastercard", "4059951"),
            entry("Allegiant World Mastercard", "4059968"),
            entry("Royal Caribbean Visa Signature", "4059947"),
            entry("Norwegian Cruise Line World Mastercard", "4059944")
    );
}
