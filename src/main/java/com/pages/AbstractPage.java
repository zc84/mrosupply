package com.pages;

import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Dmitry Sherstobitov QA LSE at intetics.tshop skype: dmitry_sherstobitov
 */
public class AbstractPage extends PageObject {

    public static Map<String, String> elements;
    private static Object currentPage;

    public AbstractPage(WebDriver driver) {
        super(driver, 35000);
    }

    public static Object getCurrentPage() {
        return currentPage;
    }

    public static void setCurrentPage(AbstractPage currentPage) {
        elements = new HashMap<String, String>();
        AbstractPage.currentPage = currentPage;
        currentPage.init_elements();
    }

    public void init_elements() {
    }

    public Map<String, String> get_default_elements() {

        Map<String, String> elements = new HashMap<>();

        elements.put("Choose file button", "//input[@type='file']");
        elements.put("Submit button", "//input[@value = 'Submit']");

        elements.put("Search field", "//input[@id = 'search-fld']");
        elements.put("My account link", "//a[title() = 'My Account']");
        elements.put("Signout link", "//a[text() = 'Signout']");
        elements.put("Sign In", "//a[text() = 'Sign In']");
        elements.put("Basket button", "//div[@class = 'cart_bg']//a");
        elements.put("Available products", "//div[@class='results-area']//td/..");
        elements.put("Products in recent view pool", "//div[@class='recent']//div[@class = 'recent_item']");

        elements.put("Yes pop button", "//span[@class='ui-button-text'][text()='Yes']");

        return elements;
    }

}
