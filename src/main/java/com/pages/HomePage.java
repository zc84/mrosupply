package com.pages;

import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.WebElementFacade;
import org.openqa.selenium.WebDriver;

import java.util.List;

/**
 * @author Dmitry Sherstobitov
 */

@DefaultUrl("/")
public class HomePage extends AbstractPage {

    private WebElementFacade[] _products_from_recent_pool;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void init_elements() {
        elements.putAll(get_default_elements());

        elements.put("Submit email field", "//input[@id = 'banner_mail']");
        elements.put("Submit email button", "//form//input[@value='SUBMIT']");
        elements.put("My account link", "//a[@title='My Account']");
        elements.put("Subscribe bunner appeared", "//img[contains(@href, 'BannerAck2.png')]");
    }


    public List<WebElementFacade> get_products_from_recent_pool() {
        return findAll(elements.get("Products in recent view pool"));
    }
}
