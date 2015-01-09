package com.jbehave;

import com.data.DataProvider;
import com.steps.ShoppingCartSteps;
import com.utils.*;
import com.utils.Math;
import net.thucydides.core.annotations.Steps;
import org.jbehave.core.annotations.When;
import org.openqa.selenium.Keys;

/**
 * Created by dys on 09.01.2015.
 */
public class ShoppingCartActions extends BasicFlow {

    @Steps
    ShoppingCartSteps shoppingSteps;

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
        shoppingSteps.select_state("CA");
        enter_in("90023", "Postal code field");
    }

    @When("fill credit card details")
    public void fill_credit_card_details() throws Exception {
        enter_in(DataProvider.CREDITCARD_NUMBER, "Card number field");
        enter_in("2020", "CVV number field");
        enter_in("Test order holder  ", "Cardholder field");
        shoppingSteps.select_expiration_date();
        new Gmail().clearEmailBox();
    }

    @When("add credit card")
    public void add_credit_card() throws Exception {
        enter_in(DataProvider.CREDITCARD_NUMBER, "Card number field");
        enter_in("2020", "CVV number field");
        enter_in("Test order holder  ", "Cardholder field");
        shoppingSteps.select_expiration_date();
        click_on("Save button");
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

    @When("fill first registration form")
    public void fill_first_reg_form() throws Exception {

        enter_email("Email field");
        enter_in("password", "Password field");
        enter_in("password", "Confirm password field");
        enter_in("test name", "First name field");
        enter_in("test surname", "Last name field");
        enter_in("555-555-5555", "Phone number field");
    }

    @When("fill second registration form")
    public void fill_second_reg_form() throws Exception {

        enter_in("test name", "First name 1 field");
        enter_in("test surname", "Last name 1 field");
        enter_in("555-555-5555", "Phone 1 field");
        enter_email("Email 1 field");
        enter_in("2915 E Washington Blvd", "Address 1 field");
        enter_in("Los Angeles", "City field");
        shoppingSteps.select_registration_state("CA");
        enter_in("90023", "Postal code field");
    }

    @When("enter random email in '$fieldName'")
    public void enter_email(String fieldName) throws Exception {
        enter_in(com.utils.Math.randInt(1, 99999) + "test" + Math.randInt(1, 99999) + "@test.com", fieldName);
    }
}
