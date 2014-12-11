package com.pages;

import net.thucydides.core.annotations.At;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.WebElementFacade;
import org.openqa.selenium.WebDriver;

import java.util.List;

/**
 * @author Dmitry Sherstobitov
 */

@At(".*brands/")
@DefaultUrl("/brands/")
public class BrandPage extends AbstractPage {

    public BrandPage(WebDriver driver) {
        super(driver);
    }

    String itemIdSelector = ".//div[@class='box image']/../a";
    String itemNameSelector = ".//h2/a";
    String itemPriceSelector = ".//strong[contains(text(), '$')]";
    String itemCountSelector = ".//input[@class = 'text qty']";
    String basketItemIconSelector = ".//a[@class = 'btn-blue add_to_cart']";
    String deleteFromBasketItemIconSelector = ".//a[@class = 'btn-blue delete_from_cart']";

    @Override
    public void init_elements() {
        elements.putAll(get_default_elements());

        elements.put("Available products", "//div[@class='results-area']//td/..");
        elements.put("Sort by dropdown", "//select[@id = 'id_sort_by']");
        elements.put("Paging", "//div[@class = 'paging']");
    }

    public List<WebElementFacade> get_available_products() {
        return findAll(elements.get("Available products"));
    }

    public void add_to_basket(WebElementFacade product, String count) {

        if (Integer.parseInt(count) > 1)
            product.then().findBy(itemCountSelector).sendKeys(count);
        product.then().findBy(basketItemIconSelector).click();
    }


    public String get_product_name(WebElementFacade product) {
        return product.then().findBy(itemNameSelector).getText().trim();
    }

    public String get_product_price(WebElementFacade product) {
        return product.then().findBy(itemPriceSelector).getText().trim().replace("$", "");
    }

    public void open_product(WebElementFacade product) {
        product.then().findBy(itemNameSelector).click();
    }

    public String get_product_id(WebElementFacade product) {
        return product.then().findBy(itemIdSelector).getText().trim();
    }

    public boolean is_delete_item_available(WebElementFacade selectedProduct) {
        return selectedProduct.then().findBy(deleteFromBasketItemIconSelector).isVisible();
    }

    public void go_to_page(String pageNumber) {
        findBy("//a[@class = 'page'][text() = '" + pageNumber + "']").click();
    }
}
