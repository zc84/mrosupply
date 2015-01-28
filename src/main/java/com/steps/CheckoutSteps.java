package com.steps;

import com.data.FlowDataProvider;
import com.data.Product;
import com.pages.ShoppingCartPage;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.pages.Pages;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

/**
 * Created by dys on 12.01.2015.
 */
public class CheckoutSteps extends BasicSteps {

    public CheckoutSteps(Pages pages) {
        super(pages);
    }

    ShoppingCartPage cartPage;

    @Step
    public void select_state(String state) throws Exception {

        WebElement element = get_element("State dropdown");
        String classValue = element.getAttribute("class");
        String idValue = element.getAttribute("id");
        currentPage.evaluateJavascript("document.getElementById('" + idValue + "').className = '" + classValue.replace("-hidden", "") + "'");

        element = get_element("Billing state dropdown");
        classValue = element.getAttribute("class");
        idValue = element.getAttribute("id");
        currentPage.evaluateJavascript("document.getElementById('" + idValue + "').className = '" + classValue.replace("-hidden", "") + "'");

        select_from_dropdown(state, "State dropdown");
        select_from_dropdown(state, "Billing state dropdown");
    }


    @Step
    public void calculate_products_total_price() {
        for (Product pr : cartPage.get_products())
            FlowDataProvider.ORDERED_PRODUCT_TOTAL_PRICE += Integer.parseInt(pr.getProductPrice());
    }

    @Step
    public String get_displayed_total_price() throws Exception {
        return get_element("Calculated total price").getText().replaceAll("[^0-9]+", "");
    }

    @Step
    public void is_total_price_correct(Integer totalPriceOnPage) {
        Assert.assertTrue("Total products price is " + totalPriceOnPage + ", should be " + FlowDataProvider.ORDERED_PRODUCT_TOTAL_PRICE, FlowDataProvider.ORDERED_PRODUCT_TOTAL_PRICE == totalPriceOnPage);
    }
}
