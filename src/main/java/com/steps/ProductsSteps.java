package com.steps;

import com.data.DataProvider;
import com.data.Product;
import com.pages.HomePage;
import com.pages.ProductsPage;
import com.utils.Math;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.pages.Pages;
import net.thucydides.core.pages.WebElementFacade;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by dys on 09.01.2015.
 */
public class ProductsSteps extends BasicSteps {

    public ProductsSteps(Pages pages) {
        super(pages);
    }

    ProductsPage productsPage;
    HomePage homePage;

    @Step
    public void select_sorting(String value) throws Exception {
        WebElement element = get_element("Sort by dropdown");
        String classValue = element.getAttribute("class");
        String idValue = element.getAttribute("id");
        currentPage.evaluateJavascript("document.getElementById('" + idValue + "').className = '" + classValue.replace("-hidden", "") + "'");
        select_from_dropdown(value, "Sort by dropdown");
    }

    @Step
    public void select_items_onpage(String value) throws Exception {
        WebElement element = get_element("Items per page dropdown");
        String classValue = element.getAttribute("class");
        String idValue = element.getAttribute("id");
        currentPage.evaluateJavascript("document.getElementById('" + idValue + "').className = '" + classValue.replace("-hidden", "") + "'");
        select_from_dropdown(value, "Items per page dropdown");
    }

    @Step
    public void add_product_to_basket(String productName, String count) throws Exception {

        WebElementFacade product;

        if (productName.equals("any")) {
            List<WebElementFacade> availableProducts = productsPage.get_available_products();
            product = availableProducts.get(com.utils.Math.randInt(0, availableProducts.size() - 1));
        }

//        TODO
        else throw new Exception("Named item selection unsupported yet");

        DataProvider.ADDED_PRODUCT = new Product();

        DataProvider.ADDED_PRODUCT.setProductElement(product);
        DataProvider.ADDED_PRODUCT.setProductName(productsPage.get_product_name(product));
        DataProvider.ADDED_PRODUCT.setProductPrice(productsPage.get_product_price(product));
        productsPage.add_to_basket(product, count);
        show_message(count + " " + DataProvider.ADDED_PRODUCT.getProductName() + " items added to basket");

    }

    @Step
    public void open_item(String productName) throws Exception {

        WebElementFacade product;
        if (productName.equals("any")) {
            List<WebElementFacade> availableProducts = productsPage.get_available_products();
            product = availableProducts.get(Math.randInt(0, availableProducts.size() - 1));
        }

//        TODO
        else throw new Exception("Named item selection unsupported yet");

        DataProvider.SELECTED_PRODUCT = new Product();

        DataProvider.SELECTED_PRODUCT.setProductElement(product);
        DataProvider.SELECTED_PRODUCT.setProductName(productsPage.get_product_name(product));
        DataProvider.SELECTED_PRODUCT.setProductPrice(productsPage.get_product_price(product));
        DataProvider.SELECTED_PRODUCT.setProductId(productsPage.get_product_id(product));
        productsPage.open_product(product);
        show_message(DataProvider.SELECTED_PRODUCT.getProductName() + " item was opened");
    }

    @Step
    public void sorting_correct_for_pages(String sortingType, String pagesCount) {

        for (int page = 1; page < Integer.parseInt(pagesCount); page++) {

            List<WebElementFacade> allProducts = productsPage.get_available_products();
            Double pruductPrice = null;

            try {
                pruductPrice = Double.parseDouble(productsPage.get_product_price(allProducts.get(0)));
            } catch (Exception e) {

            }

            if (pruductPrice != null)
                for (WebElementFacade product : allProducts) {
                    double curProductPrice = Double.parseDouble(productsPage.get_product_price(product));

                    if (sortingType.contains("desc")) {
                        Assert.assertTrue("Is " + sortingType + " correct on " + page + " page - " + productsPage.get_product_id(product), pruductPrice > curProductPrice
                                || pruductPrice == curProductPrice);
                    } else
                        Assert.assertTrue("Is " + sortingType + " correct on " + page + " page - " + productsPage.get_product_id(product), pruductPrice < curProductPrice
                                || pruductPrice == curProductPrice);
                    pruductPrice = curProductPrice;
                }
            go_to_page(String.valueOf(page + 1));
        }
    }

    @Step
    public void products_per_page_correct_for_pages(String productCount, String pagesCount) {

        for (int page = 1; page < Integer.parseInt(pagesCount); page++) {
            Assert.assertTrue("Products per page should be " + productCount, productsPage.get_available_products().size() == Integer.parseInt(productCount));
            go_to_page(String.valueOf(page + 1));
        }
    }

    @Step
    public void correspondent_products_found(String partName, String pagesCount) {
        for (int page = 1; page < Integer.parseInt(pagesCount); page++) {
            for (WebElementFacade product : productsPage.get_available_products())
                Assert.assertTrue("Is product " + productsPage.get_product_id(product) + " has " + partName + " in title", productsPage.get_product_name(product).contains(partName));
            go_to_page(String.valueOf(page + 1));
        }
    }

    @Step
    public void go_to_page(String pageNumber) {
        productsPage.go_to_page(pageNumber);
    }

    @Step
    public void is_delete_from_basket_appeared_for(String productName) {
        Assert.assertTrue("User uable to delete " + productName + " from basket", productsPage.is_delete_item_available(DataProvider.ADDED_PRODUCT.getProductElement()));
    }

    @Step
    public void is_product_in_recent_view_pool(String productName) {

        boolean isFound = false;
        for (WebElementFacade productInPool : homePage.get_products_from_recent_pool()) {
            if (productInPool.then().findBy(".//a[@data-product_pk]").getAttribute("data-product_pk").equals(DataProvider.SELECTED_PRODUCT.getProductId())) {
                isFound = true;
                break;
            }
            Assert.assertTrue("Is " + DataProvider.SELECTED_PRODUCT.getProductName() + " in recent pool", isFound);
        }
    }
}
