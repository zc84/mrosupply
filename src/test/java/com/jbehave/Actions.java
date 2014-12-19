package com.jbehave;

import com.data.DataProvider;
import com.steps.UserSteps;
import com.utils.Gmail;
import com.utils.Math;
import net.thucydides.core.annotations.Steps;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.openqa.selenium.Keys;

/**
 * @author Dmitry Sherstobitov
 */

public class Actions extends BasicFlow {

    @Steps
    UserSteps steps;

    @Given("user on the home page")
    public void open_home() throws Exception {
        new DataProvider();
        steps.open_home(DataProvider.ENVIROMENT_URL);
    }

    @Given("logged user on the home page")
    public void open_home_signed() throws Exception {
        open_home();
        click_on("Sign In");
        user_on_the_page("LoginPage");
        steps.login(DataProvider.USERNAME, DataProvider.PASSWORD);
        element_not_available("Sign In");
        user_on_the_page("HomePage");
    }

    @When("enter random email in '$fieldName'")
    public void enter_email(String fieldName) throws Exception {
        enter_in(Math.randInt(1, 99999) + "test" + Math.randInt(1, 99999) + "@test.com", fieldName);
    }

    @When("click logout")
    public void logout() throws Exception {
        steps.logout();
    }

    /**
     * @param productName - can be 'any' or exact name
     */

    @When("add '$count' '$productName' items to basket")
    public void add_product_to_basket(String productName, String count) throws Exception {
        steps.add_product_to_basket(productName, count);
    }

    /**
     * @param productName - can be 'any' or exact name
     */
    @When("open '$productName' item")
    public void open_item(String productName) throws Exception {
        steps.open_item(productName);
        user_on_the_page("ItemDetailsPage");
    }

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
        steps.select_state("CA");
        enter_in("90023", "Postal code field");
    }

    @When("fill credit card details")
    public void fill_credit_card_details() throws Exception {
        enter_in(DataProvider.CREDITCARD_NUMBER, "Card number field");
        enter_in("2020", "CVV number field");
        enter_in("Test order holder  ", "Cardholder field");
        steps.select_expiration_date();
        new Gmail().clearEmailBox();
    }

    @When("add credit card")
    public void add_credit_card() throws Exception {
        enter_in(DataProvider.CREDITCARD_NUMBER, "Card number field");
        enter_in("2020", "CVV number field");
        enter_in("Test order holder  ", "Cardholder field");
        steps.select_expiration_date();
        click_on("Save button");
    }

    @When("delete all creadit cards")
    public void delete_cards() throws Exception {
        steps.delete_cards();
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
        steps.select_registration_state("CA");
        enter_in("90023", "Postal code field");
    }

    @Then("delete from basket icon appeared for this item")
    public void delete_from_basket_appeared() throws Exception {
        steps.is_delete_from_basket_appeared_for(DataProvider.ADDED_PRODUCT.getProductName());
    }

    @Then("product details email was recieved after '$minsPeriod' mins")
    public void product_email_recieved(String minsPeriod) throws Exception {
        steps.wait_for_email("Product Detail: " + DataProvider.SELECTED_PRODUCT.getProductId(), minsPeriod);
    }

    @Then("products sort '$sortingType' for '$pagesCount' pages")
    public void sorting_correct_for_pages(String sortingType, String pagesCount) throws Exception {
        steps.sorting_correct_for_pages(sortingType, pagesCount);
    }

    @Then("'$productCount' producats available for '$pagesCount' pages")
    public void products_per_page_correct_for_pages(String productCount, String pagesCount) throws Exception {
        steps.products_per_page_correct_for_pages(productCount, pagesCount);
    }

    @Then("each product has '$partName' in title for '$pagesCount' pages")
    public void correspondent_products_found(String partName, String pagesCount) throws Exception {
        steps.correspondent_products_found(partName, pagesCount);
    }

    @Then("this product in recent view pool")
    public void is_product_in_recent_view_pool() throws Exception {
        steps.is_product_in_recent_view_pool(DataProvider.SELECTED_PRODUCT.getProductName());
    }
}
