package com.pages;

import net.thucydides.core.annotations.At;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.WebElementFacade;
import org.openqa.selenium.WebDriver;

import java.util.List;

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

//        Home
        elements.put("Orders link", "//div[contains(@class, 'dashboard')]//a[text() = 'Orders']");

//        Orders
        elements.put("Orders search field", "//input[@id = 'searchbar']");
        elements.put("Orders on page", "//table[@id = 'result_list']//tr[contains(@class, 'row')]");
        elements.put("Status dropdown", "//select[@id = 'id_status']");
        elements.put("Save button", "//input[@name = '_save']");
    }

    public List<WebElementFacade> get_orders() {
        return findAll(elements.get("Orders on page"));
    }

    public String get_order_creator(WebElementFacade order) {
        return order.then().findBy("./td[6]").getText().replace("  ", " ").trim();
    }

    public String get_order_status(WebElementFacade order) {
        return order.then().findBy("./td[8]").getText().replace("  ", " ").trim();
    }

    public void open_order(WebElementFacade order) {
        order.then().findBy(".//a").click();
    }

    public String get_order_id(WebElementFacade order) {
        return order.then().findBy(".//a").getText().replace("  ", " ").trim();
    }

    public WebElementFacade get_order_by_id(String adminOrderId) {
        return findBy(elements.get("Orders on page") + "//a[text() = '"+adminOrderId+"']/../..");
    }
}
