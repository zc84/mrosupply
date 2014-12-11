package com.steps;

import com.data.DataProvider;
import com.data.Product;
import com.pages.AbstractPage;
import com.pages.AdminPage;
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
public class AdminSteps extends BasicSteps {

    public AdminSteps(Pages pages) {
        super(pages);
    }

    AdminPage adminPage;

    @Step
    public void open_admin_login(String enviromentUrl) {
        getDriver().manage().deleteAllCookies();
        adminPage.open();
        AbstractPage.setCurrentPage(getPages().get(AdminPage.class));
    }

    public void login(String username, String password) throws Exception {
        enter_in(username, "Email filed");
        enter_in(password, "Password filed");
        click_on("Login button");
    }
}
