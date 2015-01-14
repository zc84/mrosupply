package com.pages;

import net.thucydides.core.annotations.At;
import org.openqa.selenium.WebDriver;

/**
 * @author Dmitry Sherstobitov
 */

@At(".*cart.*")
public class ShoppingCartPage extends AbstractPage {

    public ShoppingCartPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void init_elements() {
        elements.putAll(get_default_elements());

        elements.put("Product quantitly field", "//input[@id='id_cart-0-qty']");
        elements.put("Continue button", "//*[text() = 'Continue']/..");
        elements.put("Checkout button", "//span[text() = 'Checkout']/..");

        elements.put("Products quantity field", "//input[contains(@id, 'id_cart')][contains(@id, 'qty')]");

        elements.put("First name field", "//input[@id = 'id_order_info-first_name']");
        elements.put("Last name field", "//input[@id = 'id_order_info-last_name']");
        elements.put("Email field", "//input[@id = 'id_order_info-email']");
        elements.put("Phone number field", "//input[@id = 'id_order_info-phone']");

        elements.put("Shipping Address 1 field", "//input[@id = 'id_order_info-shipping_address_1']");
        elements.put("Shipping city field", "//input[@id = 'id_order_info-shipping_city']");
        elements.put("State dropdown", "//select[@id = 'id_order_info-shipping_state']");
        elements.put("Billing state dropdown", "//select[@id = 'id_order_info-billing_state']");
        elements.put("Postal code field", "//input[@id = 'id_order_info-shipping_postal_code']");

        elements.put("Card number field", "//input[@id = 'id_preview-number']");
        elements.put("CVV number field", "//input[@id = 'id_preview-cvv_number']");
        elements.put("Expires card month", "//select[@id = 'id_preview-expiration_month']");
        elements.put("Expires card year", "//select[@id = 'id_preview-expiration_year']");
        elements.put("Cardholder field", "//input[@id = 'id_preview-name']");
        elements.put("Pay button", "//button[@id = 'pay_cc_btn']");

        elements.put("Pay with paypal section", "//h3[text() = 'Pay with Paypal']");
        elements.put("Pay via paypal button", "//button[@id = 'pay_paypal_btn']");
        elements.put("Pay with my PayPal account link", "//input[@id = 'loadLogin']");
        elements.put("Paypal email field", "//input[@id = 'login_email']");
        elements.put("Paypal password field", "//input[@id = 'login_password']");
        elements.put("Paypal login button", "//input[@id = 'submitLogin']");
        elements.put("Paypal pay now button", "//input[@id = 'continue']");

    }


}
