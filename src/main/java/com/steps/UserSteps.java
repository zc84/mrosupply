package com.steps;

import com.data.DataProvider;
import com.data.Product;
import com.pages.AbstractPage;
import com.pages.AccountPage;
import com.pages.BrandPage;
import com.pages.HomePage;
import com.utils.Math;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.pages.Pages;
import net.thucydides.core.pages.WebElementFacade;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

/**
 * @author Dmitry Sherstobitov
 */

@SuppressWarnings("serial")
public class UserSteps extends BasicSteps {

    public UserSteps(Pages pages) {
        super(pages);
    }

    HomePage homePage;
    BrandPage brandPage;
    AccountPage accPage;

    @Step
    public void open_home(String enviromentUrl) {
        getDriver().manage().deleteAllCookies();
        homePage.open();
        AbstractPage.setCurrentPage(getPages().get(HomePage.class));
    }

    @Step
    public void logout() throws Exception {
        Actions action = new Actions(getDriver());
        action.moveToElement(get_element("My account link")).click(get_element("Signout link")).build().perform();
    }

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
            List<WebElementFacade> availableProducts = brandPage.get_available_products();
            product = availableProducts.get(Math.randInt(0, availableProducts.size() - 1));
        }

//        TODO
        else throw new Exception("Named item selection unsupported yet");

        DataProvider.ADDED_PRODUCT = new Product();

        DataProvider.ADDED_PRODUCT.setProductElement(product);
        DataProvider.ADDED_PRODUCT.setProductName(brandPage.get_product_name(product));
        DataProvider.ADDED_PRODUCT.setProductPrice(brandPage.get_product_price(product));
        brandPage.add_to_basket(product, count);
        show_message(count + " " + DataProvider.ADDED_PRODUCT.getProductName() + " items added to basket");

    }

    @Step
    public void open_item(String productName) throws Exception {

        WebElementFacade product;
        if (productName.equals("any")) {
            List<WebElementFacade> availableProducts = brandPage.get_available_products();
            product = availableProducts.get(Math.randInt(0, availableProducts.size() - 1));
        }

//        TODO
        else throw new Exception("Named item selection unsupported yet");

        DataProvider.SELECTED_PRODUCT = new Product();

        DataProvider.SELECTED_PRODUCT.setProductElement(product);
        DataProvider.SELECTED_PRODUCT.setProductName(brandPage.get_product_name(product));
        DataProvider.SELECTED_PRODUCT.setProductPrice(brandPage.get_product_price(product));
        DataProvider.SELECTED_PRODUCT.setProductId(brandPage.get_product_id(product));
        brandPage.open_product(product);
        show_message(DataProvider.SELECTED_PRODUCT.getProductName() + " item was opened");
    }

    @Step
    public void is_delete_from_basket_appeared_for(String productName) {
        Assert.assertTrue("User uable to delete " + productName + " from basket", brandPage.is_delete_item_available(DataProvider.ADDED_PRODUCT.getProductElement()));
    }

    @Step
    public void login(String username, String password) throws Exception {
        enter_in(username, "Username field");
        enter_in(password, "Password field");
        click_on("Login button");
        currentPage.$(get_element("Login button")).shouldNotBeVisible();
    }

    @Step
    public void select_expiration_date() throws Exception {

        WebElement element = get_element("Expires card month");
        String classValue = element.getAttribute("class");
        String idValue = element.getAttribute("id");
        currentPage.evaluateJavascript("document.getElementById('" + idValue + "').className = '" + classValue.replace("-hidden", "") + "'");

        element = get_element("Expires card year");
        classValue = element.getAttribute("class");
        idValue = element.getAttribute("id");
        currentPage.evaluateJavascript("document.getElementById('" + idValue + "').className = '" + classValue.replace("-hidden", "") + "'");

        select_from_dropdown("any", "Expires card month");
        select_from_dropdown("any", "Expires card year");
    }

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
    public void select_registration_state(String state) throws Exception {

        WebElement element = get_element("State dropdown");
        String classValue = element.getAttribute("class");
        String idValue = element.getAttribute("id");
        currentPage.evaluateJavascript("document.getElementById('" + idValue + "').className = '" + classValue.replace("-hidden", "") + "'");

        select_from_dropdown(state, "State dropdown");
    }



    @Step
    public void sorting_correct_for_pages(String sortingType, String pagesCount) {

        for (int page = 1; page < Integer.parseInt(pagesCount); page++) {

            List<WebElementFacade> allProducts = brandPage.get_available_products();
            Double pruductPrice = null;

            try {
                pruductPrice = Double.parseDouble(brandPage.get_product_price(allProducts.get(0)));
            } catch (Exception e) {

            }

            if (pruductPrice != null)
                for (WebElementFacade product : allProducts) {
                    double curProductPrice = Double.parseDouble(brandPage.get_product_price(product));

                    if (sortingType.contains("desc")) {
                        Assert.assertTrue("Is " + sortingType + " correct on " + page + " page - " + brandPage.get_product_id(product), pruductPrice > curProductPrice
                                || pruductPrice == curProductPrice);
                    } else
                        Assert.assertTrue("Is " + sortingType + " correct on " + page + " page - " + brandPage.get_product_id(product), pruductPrice < curProductPrice
                                || pruductPrice == curProductPrice);
                    pruductPrice = curProductPrice;
                }
            go_to_page(String.valueOf(page + 1));
        }
    }

    @Step
    public void products_per_page_correct_for_pages(String productCount, String pagesCount) {

        for (int page = 1; page < Integer.parseInt(pagesCount); page++) {
            Assert.assertTrue("Products per page should be " + productCount, brandPage.get_available_products().size() == Integer.parseInt(productCount));
            go_to_page(String.valueOf(page + 1));
        }
    }

    @Step
    public void correspondent_products_found(String partName, String pagesCount) {
        for (int page = 1; page < Integer.parseInt(pagesCount); page++) {
            for (WebElementFacade product : brandPage.get_available_products())
                Assert.assertTrue("Is product " + brandPage.get_product_id(product) + " has " + partName + " in title", brandPage.get_product_name(product).contains(partName));
            go_to_page(String.valueOf(page + 1));
        }
    }

    @Step
    public void go_to_page(String pageNumber) {
        brandPage.go_to_page(pageNumber);
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

    @Step
    public void delete_cards() throws Exception {

        while (accPage.getCards().size() > 0) {
            accPage.deleteCC();
            see_message("Credit Card is removed");
            refresh_page();
        }
    }
}
