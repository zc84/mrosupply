package com.jbehave;

import com.data.DataProvider;
import com.steps.AccountSteps;
import com.utils.Gmail;
import net.thucydides.core.annotations.Steps;
import org.jbehave.core.annotations.When;

/**
 * Created by dys on 12.01.2015.
 */
public class AccountActions extends BasicFlow {

    @Steps
    AccountSteps accountSteps;

    @When("fill credit card details")
    public void fill_credit_card_details() throws Exception {
        enter_in(DataProvider.CREDITCARD_NUMBER, "Card number field");
        enter_in("2020", "CVV number field");
        enter_in("Test order holder  ", "Cardholder field");
        accountSteps.select_expiration_date();
        new Gmail().clearEmailBox();
    }

    @When("add credit card")
    public void add_credit_card() throws Exception {
        enter_in(DataProvider.CREDITCARD_NUMBER, "Card number field");
        enter_in("2020", "CVV number field");
        enter_in("Test order holder  ", "Cardholder field");
        accountSteps.select_expiration_date();
        click_on("Save button");
    }

    @When("delete all creadit cards")
    public void delete_cards() throws Exception {
        accountSteps.delete_cards();
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
        accountSteps.select_registration_state("CA");
        enter_in("90023", "Postal code field");
    }
}
