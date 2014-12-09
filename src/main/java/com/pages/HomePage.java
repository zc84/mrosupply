package com.pages;

import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.WebDriver;

/**
 * @author Dmitry Sherstobitov
 */

@DefaultUrl("/")
public class HomePage extends AbstractPage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void init_elements() {
        elements.putAll(get_default_elements());

        elements.put("Submit email field", "//input[@id = 'banner_mail']");
        elements.put("Submit email button", "//form//input[@value='SUBMIT']");
        elements.put("Subscribe bunner appeared", "//img[contains(@href, 'BannerAck2.png')]");
    }


}
