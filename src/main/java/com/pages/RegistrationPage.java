package com.pages;

import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.WebDriver;

/**
 * @author Dmitry Sherstobitov
 */

@DefaultUrl(".*accounts/register/")
public class RegistrationPage extends AbstractPage {

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void init_elements() {

        elements.putAll(get_default_elements());

        elements.put("Email field", "//input[@id = 'id_reg_1-email']");
        elements.put("Password field", "//input[@id = 'id_reg_1-password1']");
        elements.put("Confirm password field", "//input[@id = 'id_reg_1-password2']");
        elements.put("First name field", "//input[@id = 'id_reg_1-first_name']");
        elements.put("Last name field", "//input[@id = 'id_reg_1-last_name']");
        elements.put("Phone number field", "//input[@id = 'id_reg_1-phone']");

        elements.put("First name 1 field", "//input[@id = 'id_reg_2-first_name']");
        elements.put("Last name 1 field", "//input[@id = 'id_reg_2-last_name']");
        elements.put("Phone 1 field", "//input[@id = 'id_reg_2-phone']");
        elements.put("Email 1 field", "//input[@id = 'id_reg_2-email']");
        elements.put("Address 1 field", "//input[@id = 'id_reg_2-address_1']");
        elements.put("City field", "//input[@id = 'id_reg_2-city']");
        elements.put("State dropdown", "//select[@id = 'id_reg_2-state']");
        elements.put("Postal code field", "//input[@id = 'id_reg_2-postal_code']");


    }


}
