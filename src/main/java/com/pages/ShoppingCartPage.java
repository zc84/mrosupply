package com.pages;

import com.data.Product;
import net.thucydides.core.annotations.At;
import net.thucydides.core.pages.WebElementFacade;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

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

        elements.put("Product list", "//div[@class='results-area']//tbody/tr");
        elements.put("Products quantity field", "//input[contains(@id, 'id_cart')][contains(@id, 'qty')]");
        elements.put("Calculated total price", "//dd[@id='subtotal']");

        elements.put("First name field", "//input[@id = 'id_order_info-first_name']");
        elements.put("Last name field", "//input[@id = 'id_order_info-last_name']");
        elements.put("Email field", "//input[@id = 'id_order_info-email']");
        elements.put("Phone number field", "//input[@id = 'id_order_info-phone']");

        elements.put("Shipping Address 1 field", "//input[@id = 'id_order_info-shipping_address_1']");
        elements.put("Shipping city field", "//input[@id = 'id_order_info-shipping_city']");
        elements.put("State dropdown", "//select[@id = 'id_order_info-shipping_state']");
        elements.put("Billing state dropdown", "//select[@id = 'id_order_info-billing_state']");
        elements.put("Postal code field", "//input[@id = 'id_order_info-shipping_postal_code']");
        elements.put("Products shipping section", "//table[@class='shipping_price_table']");

        elements.put("Total price on location", "//strong[@class='total']/span");

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
        elements.put("Total price section", "//aside[@class = 'checkout_overview']");


    }


    public List<Product> get_products() {

        List<Product> products = new ArrayList<>();

        for (WebElementFacade prodElement : findAll(elements.get("Product list"))) {
            Product pr = new Product();
            Integer produtQuantity = Integer.parseInt(prodElement.then().findBy("." + elements.get("Products quantity field")).getAttribute("value"));
            pr.setProductPrice(Integer.parseInt(prodElement.then().findBy("./td/strong[@class='mro_red']")
                    .getText().replaceAll("[^0-9]+", "")) * produtQuantity);
            products.add(pr);
        }
        return products;
    }

    public Integer get_shipping_total_price() {

        Integer total = 0;
        for (WebElementFacade shipSection : findAll(elements.get("Products shipping section"))) {
            total += Integer.parseInt(shipSection.then().findBy(".//div[@class='shipping_price']//span[@class='price']").
                    getText().replaceAll("[^0-9]+", ""));
        }
        return total;
    }

    public Integer get_subtotal_from_payment() {
        return Integer.parseInt(findBy(elements.get("Total price section")).then().findBy(".//div[contains(text(), 'Subtotal:')]/strong").
                getText().replaceAll("[^0-9]+", ""));
    }

    public Integer get_shipping_from_payment() {
        return Integer.parseInt(findBy(elements.get("Total price section")).then().findBy(".//div[contains(text(), 'Shipping:')]/strong").
                getText().replaceAll("[^0-9]+", ""));
    }

    public Integer get_tax_payment() {
        return Integer.parseInt(findBy(elements.get("Total price section")).then().findBy(".//div[contains(text(), 'Sales tax:')]/strong").
                getText().replaceAll("[^0-9]+", ""));
    }

    public Integer get_grand_payment() {
        return Integer.parseInt(findBy(elements.get("Total price section")).then().findBy(".//div[contains(text(), 'Grand total:')]/strong").
                getText().replaceAll("[^0-9]+", ""));
    }
}
