package com.pages;

import net.thucydides.core.annotations.At;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.WebElementFacade;
import org.openqa.selenium.WebDriver;

import java.util.List;

@DefaultUrl("/accounts/account_center/")
@At(".*/accounts/account_center/")
public class AccountPage extends AbstractPage {

    public AccountPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void init_elements() {
        elements.putAll(get_default_elements());

        elements.put("Existing credit cards", "//table[@class='table_cc']//tr[@data-obj_id]");

        elements.put("New button", "//input[@value = 'New']");
        elements.put("Add credit card popup", "//span[text() = 'New Credit Card']/../..");

        elements.put("Card number field", "//input[@id = 'id_number']");
        elements.put("CVV number field", "//input[@id = 'id_cvv_number']");
        elements.put("Expires card month", "//select[@id = 'id_expiration_month']");
        elements.put("Expires card year", "//select[@id = 'id_expiration_year']");
        elements.put("Cardholder field", "//input[@id = 'id_name']");
        elements.put("Save button", "//span[text() = 'Save']/..");

        elements.put("File name field", "//input[@id = 'id_name']");
    }

    public List<WebElementFacade> getCards() {
        return findAll(elements.get("Existing credit cards"));
    }

    public void deleteCC() {
        findBy(elements.get("Existing credit cards") + "//a[@title='Remove']").click();
        findBy(elements.get("Yes pop button")).waitUntilVisible();
        findBy(elements.get("Yes pop button")).click();

    }
}
