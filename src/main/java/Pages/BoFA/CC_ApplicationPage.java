package Pages.BoFA;

import org.openqa.selenium.By;

public class CC_ApplicationPage {
    public By firstName = new By.ById("customerFirstName");

    public By lastName = new By.ById("customerLastName");

    public By address1 = new By.ByCssSelector("input[name='customerResidentialAddressOne']");

    public By address1City = new By.ByCssSelector("input[name='customerAddressCity']");

    public By address1State = new By.ById("customerAddressState");

    public By address1Zip = new By.ById("customerAddressInput");

    public By phone = new By.ById("customerPrimaryPhoneNumber");

    public By mobilePhoneRadio = new By.ById("phoneNumberMobile");

    public By homePhoneRadio = new By.ById("phoneNumberHome");

    public By emailSelector = new By.ById("customerEmailAddress");

    public By yesUSCitizen = new By.ByCssSelector("#customerUsCitizenYes");


    public By ssnNumberInput = new By.ByCssSelector("#customerSocialSecurityNumber");

    public By dualCitizenshipYes = new By.ByCssSelector("#customerDualCitizenshipYes");

    public By dualCitizenshipNo = new By.ByCssSelector("#customerDualCitizenshipNo");

    public By countrySelect = new By.ByCssSelector("#customerCountryOfResidence");

    public By dobInput = new By.ByCssSelector("#customerBirthDate");

    public By submitAndContinue = new By.ByCssSelector("#icaiContinueButton");

    public By employmentStatus = new By.ByCssSelector("#employmentStatus");

    public By annualIncome = new By.ByCssSelector("#annualSalary");

    public By incomeSource = new By.ByCssSelector("#incomeSource");

    public By monthlyHousingCost = new By.ByCssSelector("#monthlyHousingPayment");

    public By occupationSelect = new By.ByCssSelector("#occupation");

    public By termAndConditionCheck = new By.ByCssSelector("#termsAndConditionsCheckBox");

    public By reviewContainer = new By.ByCssSelector("div.reviewEditWrapper");

    public By finalPageCardName = new By.ByCssSelector("span.show-for-medium-up");

}
