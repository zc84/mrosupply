package com.steps;

import com.data.DataProvider;
import com.data.Product;
import com.pages.ProductsPage;
import com.utils.Math;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.pages.Pages;
import net.thucydides.core.pages.WebElementFacade;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by dys on 12.01.2015.
 */
public class ProductSteps extends BasicSteps {

    public ProductSteps(Pages pages) {
        super(pages);
    }

    ProductsPage productPage;

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
            List<WebElementFacade> availableProducts = productPage.get_available_products();
            product = availableProducts.get(com.utils.Math.randInt(0, availableProducts.size() - 1));
        }

//        TODO
        else throw new Exception("Named item selection unsupported yet");

        DataProvider.ADDED_PRODUCT = new Product();

        DataProvider.ADDED_PRODUCT.setProductElement(product);
        DataProvider.ADDED_PRODUCT.setProductName(productPage.get_product_name(product));
        DataProvider.ADDED_PRODUCT.setProductPrice(productPage.get_product_price(product));
        productPage.add_to_basket(product, count);
        show_message(count + " " + DataProvider.ADDED_PRODUCT.getProductName() + " items added to basket");

    }

    @Step
    public void open_item(String productName) throws Exception {

        WebElementFacade product = null;
        List<WebElementFacade> availableProducts = productPage.get_available_products();
        if (productName.equals("any")) {
            product = availableProducts.get(Math.randInt(0, availableProducts.size() - 1));
        } else {
            if (productName.split(" ").length > 1) {
                for (WebElementFacade prd : availableProducts) {
                    if (productPage.get_product_name(prd).contains(productName)) {
                        product = prd;
                        break;
                    }
                }
            } else {
                for (WebElementFacade prd : availableProducts) {
                    if (productPage.get_product_id(prd).contains(productName)) {
                        product = prd;
                        break;
                    }
                }
            }
        }

        if (product == null)
            throw new Exception(productName + " product wasn't found");

        DataProvider.SELECTED_PRODUCT = new Product();

        DataProvider.SELECTED_PRODUCT.setProductElement(product);
        DataProvider.SELECTED_PRODUCT.setProductName(productPage.get_product_name(product));
        DataProvider.SELECTED_PRODUCT.setProductPrice(productPage.get_product_price(product));
        DataProvider.SELECTED_PRODUCT.setProductId(productPage.get_product_id(product));
        productPage.open_product(product);
        show_message(DataProvider.SELECTED_PRODUCT.getProductName() + " item was opened");
    }

    @Step
    public void is_delete_from_basket_appeared_for(String productName) {
        Assert.assertTrue("User uable to delete " + productName + " from basket", productPage.is_delete_item_available(DataProvider.ADDED_PRODUCT.getProductElement()));
    }

    @Step
    public void sorting_correct_for_pages(String sortingType, String pagesCount) {

        for (int page = 1; page < Integer.parseInt(pagesCount); page++) {

            List<WebElementFacade> allProducts = productPage.get_available_products();
            Double pruductPrice = null;

            try {
                pruductPrice = Double.parseDouble(productPage.get_product_price(allProducts.get(0)));
            } catch (Exception e) {

            }

            if (pruductPrice != null)
                for (WebElementFacade product : allProducts) {
                    double curProductPrice = Double.parseDouble(productPage.get_product_price(product));

                    if (sortingType.contains("desc")) {
                        Assert.assertTrue("Is " + sortingType + " correct on " + page + " page - " + productPage.get_product_id(product), pruductPrice > curProductPrice
                                || pruductPrice == curProductPrice);
                    } else
                        Assert.assertTrue("Is " + sortingType + " correct on " + page + " page - " + productPage.get_product_id(product), pruductPrice < curProductPrice
                                || pruductPrice == curProductPrice);
                    pruductPrice = curProductPrice;
                }
            go_to_page(String.valueOf(page + 1));
        }
    }

    @Step
    public void products_per_page_correct_for_pages(String productCount, String pagesCount) {

        for (int page = 1; page < Integer.parseInt(pagesCount); page++) {
            Assert.assertTrue("Products per page should be " + productCount, productPage.get_available_products().size() == Integer.parseInt(productCount));
            go_to_page(String.valueOf(page + 1));
        }
    }

    @Step
    public void correspondent_products_found(String partName, String pagesCount) {
        for (int page = 1; page < Integer.parseInt(pagesCount); page++) {
            for (WebElementFacade product : productPage.get_available_products())
                Assert.assertTrue("Is product " + productPage.get_product_id(product) + " has " + partName + " in title", productPage.get_product_name(product).contains(partName));
            go_to_page(String.valueOf(page + 1));
        }
    }

    @Step
    public void go_to_page(String pageNumber) {
        productPage.go_to_page(pageNumber);
    }

    @Step
    public void manufacturer_minimum_quantity_set() throws Exception {
        see_message("Manufacturer Minimum Quantity");
        DataProvider.PRODUCT_MIN_QUANTITY = productPage.get_product_minimum_quantity();
    }
}
