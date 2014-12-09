package com.pages;

import net.thucydides.core.annotations.At;
import org.openqa.selenium.WebDriver;

/**
 * @author Dmitry Sherstobitov
 */

@At(".*quotes/")
public class QuotesPage extends AbstractPage {

    public QuotesPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void init_elements() {
        elements.putAll(get_default_elements());

        elements.put("Checkout now button", "//a[@class = 'btn checkout-btn']");
    }
}
