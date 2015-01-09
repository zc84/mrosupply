package com.steps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.pages.Pages;
import org.openqa.selenium.interactions.Actions;

/**
 * Created by dys on 09.01.2015.
 */
public class LoginSteps extends BasicSteps {

    public LoginSteps(Pages pages) {
        super(pages);
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
}
