package com.pages;

import net.thucydides.core.annotations.At;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.WebDriver;

@DefaultUrl("/accounts/account_center/")
@At(".*/accounts/account_center/")
public class AccountPage extends AbstractPage {

    public AccountPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void init_elements() {
        elements.putAll(get_default_elements());

        elements.put("New button", "//input[@value = 'New']");
        elements.put("Add credit card popup", "//span[text() = 'New Credit Card']/../..");

        elements.put("Card number field", "//input[@id = 'id_number']");
        elements.put("CVV number field", "//input[@id = 'id_preview-cvv_number']");
        elements.put("Expires card month", "//select[@id = 'id_preview-expiration_month']");
        elements.put("Expires card year", "//select[@id = 'id_preview-expiration_year']");
        elements.put("Cardholder field", "//input[@id = 'id_preview-name']");

    }
}
