package com.jbehave;

import com.data.DataProvider;
import com.steps.AdminSteps;
import com.steps.UserSteps;
import com.utils.Math;
import net.thucydides.core.annotations.Steps;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.openqa.selenium.Keys;

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
}
