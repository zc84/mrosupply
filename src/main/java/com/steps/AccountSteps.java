package com.steps;

import com.pages.AccountPage;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.pages.Pages;
import org.openqa.selenium.WebElement;

/**
 * Created by dys on 12.01.2015.
 */
public class AccountSteps extends BasicSteps {

    public AccountSteps(Pages pages) {
        super(pages);
    }

    AccountPage accPage;

    @Step
    public void delete_cards() throws Exception {
        while (accPage.getCards().size() > 0) {
            accPage.deleteCC();
            see_message("Credit Card is removed");
            refresh_page();
        }
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
    public void select_registration_state(String state) throws Exception {

        WebElement element = get_element("State dropdown");
        String classValue = element.getAttribute("class");
        String idValue = element.getAttribute("id");
        currentPage.evaluateJavascript("document.getElementById('" + idValue + "').className = '" + classValue.replace("-hidden", "") + "'");

        select_from_dropdown(state, "State dropdown");
    }
}
