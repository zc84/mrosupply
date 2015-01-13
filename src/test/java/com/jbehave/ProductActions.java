package com.jbehave;

import com.data.DataProvider;
import com.steps.ProductSteps;
import net.thucydides.core.annotations.Steps;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

/**
 * Created by dys on 12.01.2015.
 */
public class ProductActions extends BasicFlow {

    @Steps
    ProductSteps productSteps;

    @When("select '$value' sorting")
    public void select_sorting(String value) throws Exception {
        productSteps.select_sorting(value);
    }

    @When("select '$value' items per page")
    public void select_items_onpage(String value) throws Exception {
        productSteps.select_items_onpage(value);
    }

    /**
     * @param productName - can be 'any' or exact name
     */

    @When("add '$count' '$productName' items to basket")
    public void add_product_to_basket(String productName, String count) throws Exception {
        productSteps.add_product_to_basket(productName, count);
    }

    /**
     * @param productName - can be 'any' or exact name
     */
    @When("open '$productName' item")
    public void open_item(String productName) throws Exception {
        productSteps.open_item(productName);
        user_on_the_page("ProductDetailsPage");
    }

    @Then("delete from basket icon appeared for this item")
    public void delete_from_basket_appeared() throws Exception {
        productSteps.is_delete_from_basket_appeared_for(DataProvider.ADDED_PRODUCT.getProductName());
    }

    @Then("product details email was recieved after '$minsPeriod' mins")
    public void product_email_recieved(String minsPeriod) throws Exception {
        basicSteps.wait_for_email("Product Detail: " + DataProvider.SELECTED_PRODUCT.getProductId(), minsPeriod);
    }

    @Then("products sort '$sortingType' for '$pagesCount' pages")
    public void sorting_correct_for_pages(String sortingType, String pagesCount) throws Exception {
        productSteps.sorting_correct_for_pages(sortingType, pagesCount);
    }

    @Then("'$productCount' producats available for '$pagesCount' pages")
    public void products_per_page_correct_for_pages(String productCount, String pagesCount) throws Exception {
        productSteps.products_per_page_correct_for_pages(productCount, pagesCount);
    }

    @Then("each product has '$partName' in title for '$pagesCount' pages")
    public void correspondent_products_found(String partName, String pagesCount) throws Exception {
        productSteps.correspondent_products_found(partName, pagesCount);
    }
}
