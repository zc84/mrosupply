package com.steps;

import com.data.FlowDataProvider;
import com.pages.AbstractPage;
import com.pages.AdminPage;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.pages.Pages;
import net.thucydides.core.pages.WebElementFacade;
import org.junit.Assert;
import org.openqa.selenium.Keys;

/**
 * @author Dmitry Sherstobitov
 */

@SuppressWarnings("serial")
public class AdminSteps extends BasicSteps {

    public AdminSteps(Pages pages) {
        super(pages);
    }

    AdminPage adminPage;

    @Step
    public void open_admin_login(String enviromentUrl) {
        getDriver().manage().deleteAllCookies();
        adminPage.open();
        AbstractPage.setCurrentPage(getPages().get(AdminPage.class));
    }

    @Step
    public void login(String username, String password) throws Exception {
        enter_in(username, "Email filed");
        enter_in(password, "Password filed");
        click_on("Login button");
    }

    @Step
    public void open_order_in_status_createdby(String orderStatus, String customer) throws Exception {

        enter_in(customer, "Orders search field");
        press(Keys.ENTER, "Orders search field");

        for (WebElementFacade order : adminPage.get_orders()) {

            if (adminPage.get_order_creator(order).contains(customer) && adminPage.get_order_status(order).equals(orderStatus)) {
                FlowDataProvider.ADMIN_ORDER_ID = adminPage.get_order_id(order);
                adminPage.open_order(order);
                break;
            }
        }
        if (FlowDataProvider.ADMIN_ORDER_ID == null || FlowDataProvider.ADMIN_ORDER_ID.isEmpty())
            throw new Exception("Specified order wasn't found");
    }

    @Step
    public void is_order_status(String orderStatus) {
        WebElementFacade order = adminPage.get_order_by_id(FlowDataProvider.ADMIN_ORDER_ID);
        String actualStatus = adminPage.get_order_status(order);
        Assert.assertTrue("Order " + FlowDataProvider.ADMIN_ORDER_ID + " status should be " + orderStatus +
                " actual status is " + actualStatus, orderStatus.equals(actualStatus));
    }
}
