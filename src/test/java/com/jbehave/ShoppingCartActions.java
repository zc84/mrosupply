package com.jbehave;

import com.data.DataProvider;
import com.data.FlowDataProvider;
import com.steps.CheckoutSteps;
import com.utils.Gmail;
import net.thucydides.core.annotations.Steps;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.openqa.selenium.Keys;

/**
 * Created by dys on 12.01.2015.
 */
public class ShoppingCartActions extends BasicFlow {

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
        enter_in("invalidemail_bla@gmail.com", "Email field");
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

    @Then("product quantity is correct")
    public void product_quantity_is_correct() throws Exception {
        element_attribute_value_is("Product quantitly field", "value", DataProvider.PRODUCT_MIN_QUANTITY);
    }

    @Then("basket subtotal price calculated correctly")
    public void basket_subtotal_price_correct() throws Exception {
        checkoutSteps.calculate_products_total_price();
        Integer totalPriceOnPage = Integer.parseInt(checkoutSteps.get_displayed_total_price());
        basicSteps.check_isTrue("Total products price is " + totalPriceOnPage + ", should be " +
                FlowDataProvider.ORDERED_PRODUCT_TOTAL_PRICE, FlowDataProvider.ORDERED_PRODUCT_TOTAL_PRICE.equals(totalPriceOnPage));
    }

    @Then("total price on location calculated correctly")
    public void location_subtotal_price_correct() throws Exception {
        Integer totalPriceOnPage = Integer.parseInt(checkoutSteps.get_displayed_total_price_on_location());
        basicSteps.check_isTrue("Total products price is " + totalPriceOnPage + ", should be " +
                FlowDataProvider.ORDERED_PRODUCT_TOTAL_PRICE, FlowDataProvider.ORDERED_PRODUCT_TOTAL_PRICE.equals(totalPriceOnPage));
    }

    @When("get shipment price")
    public void get_shipment_price() throws Exception {
        FlowDataProvider.ORDERED_PRODUCT_SHIPMENT_PRICE = checkoutSteps.get_shipping_total_price();
    }

    @Then("total price on payment calculated correctly")
    public void payment_subtotal_price_correct() throws Exception {

        Integer price = checkoutSteps.get_subtotal_from_payment();
        basicSteps.check_isTrue("Subtotal price is " + price + ", should be " +
                FlowDataProvider.ORDERED_PRODUCT_TOTAL_PRICE, FlowDataProvider.ORDERED_PRODUCT_TOTAL_PRICE.equals(price));

        price = checkoutSteps.get_shipping_price_from_payment();
        basicSteps.check_isTrue("Shipping price is " + price + ", should be " +
                FlowDataProvider.ORDERED_PRODUCT_SHIPMENT_PRICE, FlowDataProvider.ORDERED_PRODUCT_SHIPMENT_PRICE.equals(price));

        price = checkoutSteps.get_tax_payment();

        Integer grandPrice = checkoutSteps.get_grand_payment();
        Integer expectedPrice = price + FlowDataProvider.ORDERED_PRODUCT_SHIPMENT_PRICE + FlowDataProvider.ORDERED_PRODUCT_TOTAL_PRICE;

        basicSteps.check_isTrue("Grand total is " + grandPrice + ", should be " + expectedPrice, expectedPrice.equals(grandPrice));


    }

}
