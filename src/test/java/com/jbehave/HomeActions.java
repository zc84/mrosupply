package com.jbehave;

import com.data.DataProvider;
import com.steps.HomeSteps;
import com.steps.LoginSteps;
import net.thucydides.core.annotations.Steps;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.When;

/**
 * Created by dys on 09.01.2015.
 */
public class HomeActions extends BasicFlow {

    @Steps
    HomeSteps homeSteps;
    @Steps
    LoginSteps loginSteps;

    @Given("user on the home page")
    public void open_home() throws Exception {
        new DataProvider();
        homeSteps.open_home(DataProvider.ENVIROMENT_URL);
    }

    @Given("logged user on the home page")
    public void open_home_signed() throws Exception {
        open_home();
        click_on("Sign In");
        user_on_the_page("LoginPage");
        loginSteps.login(DataProvider.USERNAME, DataProvider.PASSWORD);
        element_not_available("Sign In");
        user_on_the_page("HomePage");
    }

    @When("click logout")
    public void logout() throws Exception {
        loginSteps.logout();
    }
}
