package com.pages;

import net.thucydides.core.annotations.At;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.WebDriver;

/**
 * @author Dmitry Sherstobitov
 */

@DefaultUrl("/admin")
@At(".*admin.*")
public class AdminPage extends AbstractPage {

    public AdminPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void init_elements() {

//        Login
        elements.put("Email filed", "//input[@id = 'id_username']");
        elements.put("Password filed", "//input[@id = 'id_password']");
        elements.put("Login button", "//input[@value = 'Log in']");

//        Orders
        elements.put("Status dropdown", "//select[@id = 'id_status']");
        elements.put("Save button", "//input[@name = '_save']");
    }
}
