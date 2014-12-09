package com.pages;

import org.openqa.selenium.WebDriver;

/**
 * @author Dmitry Sherstobitov
 */

public class ItemDetailsPage extends AbstractPage {

    public ItemDetailsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void init_elements() {
        elements.putAll(get_default_elements());

        elements.put("Add to basket button", "//a[@class = 'btn-blue add_to_cart']");
        elements.put("Add to favorite link", "//a[@class = 'aux-btn favorite-btn']");
        elements.put("Email link", "//a[@class = 'aux-btn email-btn email_modal_open']");
        elements.put("Email field", "//input[@id = 'email']");
        elements.put("Send email button", "//span[text() = 'Send']/..");

        elements.put("Continue button", "//button[@id = 'btn_submit']");

        elements.put("Cancel login button", "//div[contains(@style, 'block')]//button[@title = 'Close']");


    }


}
