package com.jbehave;

import com.data.DataProvider;
import com.steps.AdminSteps;
import com.utils.Gmail;
import net.thucydides.core.annotations.Steps;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

/**
 * @author Dmitry Sherstobitov
 */

public class AdminActions extends BasicFlow {

    @Steps
    AdminSteps steps;

    @Given("admin on the home page")
    public void open_home_signed() throws Exception {
        new DataProvider();
        steps.open_admin_login(DataProvider.ENVIROMENT_URL);
        steps.login(DataProvider.ADMIN_USERNAME, DataProvider.ADMIN_PASSWORD);
        element_not_available("Login button");
    }

    @When("open '$orderStatus' order created by '$customer'")
    public void open_order_in_status_createdby(String orderStatus, String customer) throws Exception {
        new Gmail().clearEmailBox();
        steps.open_order_in_status_createdby(orderStatus, customer);
    }

    @Then("status is '$expectedStatus' for this order")
    public void is_order_status(String orderStatus) throws Exception {
        steps.is_order_status(orderStatus);
    }
}
