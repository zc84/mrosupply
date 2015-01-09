package com.jbehave;

import com.data.DataProvider;
import com.steps.ProductsSteps;
import net.thucydides.core.annotations.Steps;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

/**
 * Created by dys on 09.01.2015.
 */
public class ProductsActions extends BasicFlow {

    @Steps
    ProductsSteps productsSteps;

    @When("select '$value' sorting")
    public void select_sorting(String value) throws Exception {
        productsSteps.select_sorting(value);
    }

    @When("select '$value' items per page")
    public void select_items_onpage(String value) throws Exception {
        productsSteps.select_items_onpage(value);
    }

    /**
     * @param productName - can be 'any' or exact name
     */

    @When("add '$count' '$productName' items to basket")
    public void add_product_to_basket(String productName, String count) throws Exception {
        productsSteps.add_product_to_basket(productName, count);
    }

    /**
     * @param productName - can be 'any' or exact name
     */
    @When("open '$productName' item")
    public void open_item(String productName) throws Exception {
        productsSteps.open_item(productName);
        user_on_the_page("ItemDetailsPage");
    }

    @Then("products sort '$sortingType' for '$pagesCount' pages")
    public void sorting_correct_for_pages(String sortingType, String pagesCount) throws Exception {
        productsSteps.sorting_correct_for_pages(sortingType, pagesCount);
    }

    @Then("'$productCount' producats available for '$pagesCount' pages")
    public void products_per_page_correct_for_pages(String productCount, String pagesCount) throws Exception {
        productsSteps.products_per_page_correct_for_pages(productCount, pagesCount);
    }

    @Then("each product has '$partName' in title for '$pagesCount' pages")
    public void correspondent_products_found(String partName, String pagesCount) throws Exception {
        productsSteps.correspondent_products_found(partName, pagesCount);
    }

    @Then("delete from basket icon appeared for this item")
    public void delete_from_basket_appeared() throws Exception {
        productsSteps.is_delete_from_basket_appeared_for(DataProvider.ADDED_PRODUCT.getProductName());
    }

    @Then("this product in recent view pool")
    public void is_product_in_recent_view_pool() throws Exception {
        productsSteps.is_product_in_recent_view_pool(DataProvider.SELECTED_PRODUCT.getProductName());
    }

    @Then("product details email was recieved after '$minsPeriod' mins")
    public void product_email_recieved(String minsPeriod) throws Exception {
        productsSteps.wait_for_email("Product Detail: " + DataProvider.SELECTED_PRODUCT.getProductId(), minsPeriod);
    }
}
