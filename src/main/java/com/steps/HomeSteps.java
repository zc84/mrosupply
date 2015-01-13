package com.steps;

import com.data.DataProvider;
import com.pages.AbstractPage;
import com.pages.HomePage;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.pages.Pages;
import net.thucydides.core.pages.WebElementFacade;
import org.junit.Assert;
import org.openqa.selenium.interactions.Actions;

/**
 * Created by dys on 12.01.2015.
 */
public class HomeSteps extends BasicSteps {

    public HomeSteps(Pages pages) {
        super(pages);
    }

    HomePage homePage;

    @Step
    public void open_home(String enviromentUrl) {
        getDriver().manage().deleteAllCookies();
        homePage.open();
        AbstractPage.setCurrentPage(getPages().get(HomePage.class));
    }

    @Step
    public void login(String username, String password) throws Exception {
        enter_in(username, "Username field");
        enter_in(password, "Password field");
        click_on("Login button");
        currentPage.$(get_element("Login button")).shouldNotBeVisible();
    }

    @Step
    public void logout() throws Exception {
        Actions action = new Actions(getDriver());
        action.moveToElement(get_element("My account link")).click(get_element("Signout link")).build().perform();
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
