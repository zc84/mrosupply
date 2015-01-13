package com.jbehave;

import com.data.DataProvider;
import com.steps.CheckoutSteps;
import com.utils.Gmail;
import net.thucydides.core.annotations.Steps;
import org.jbehave.core.annotations.When;
import org.openqa.selenium.Keys;

/**
 * Created by dys on 12.01.2015.
 */
public class CheckoutActions extends BasicFlow {

    @Steps
    CheckoutSteps checkoutSteps;

    @When("fill personal info section")
    public void fill_peronal_info_section() throws Exception {
        enter_in("automation", "First name field");
        waitabit("1000");
        press(Keys.TAB, "First name field");
        enter_in("user", "Last name field");
        waitabit("1000");
        press(Keys.TAB, "Last name field");
        enter_in(DataProvider.USER_EMAIL, "Email field");
        waitabit("1000");
        press(Keys.TAB, "Email field");
        enter_in("5555555555", "Phone number field");
        waitabit("1000");
        press(Keys.TAB, "Phone number field");
    }

    @When("fill shipping info section")
    public void fill_shipping_info_section() throws Exception {
        enter_in("2915 E Washington Blvd", "Shipping Address 1 field");
        enter_in("Los Angeles", "Shipping city field");
        checkoutSteps.select_state("CA");
        enter_in("90023", "Postal code field");
    }

    @When("fill paypal details")
    public void fill_paypal_details() throws Exception {

        new Gmail().clearEmailBox();
        click_on("Pay with my PayPal account link");
        basicSteps.wait_while_paypal_loading();
        waitabit("15000");
        enter_in("ddd2@ddd.ddd", "Paypal email field");
        enter_in("dddddddd", "Paypal password field");
        click_on("Paypal login button");
        basicSteps.wait_while_paypal_loading();
        waitabit("1000");
        click_on("Paypal pay now button");
        basicSteps.wait_while_paypal_loading();
        waitabit("1000");
    }

}
