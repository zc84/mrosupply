package com.steps;

import com.data.DataProvider;
import com.data.Product;
import com.pages.AbstractPage;
import com.pages.BrandPage;
import com.pages.HomePage;
import com.utils.Math;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.pages.Pages;
import net.thucydides.core.pages.WebElementFacade;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

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

    @Step
    public void open_home(String enviromentUrl) {
        getDriver().manage().deleteAllCookies();
        homePage.open();
        AbstractPage.setCurrentPage(getPages().get(HomePage.class));
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
}
