package com.steps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.pages.Pages;
import org.openqa.selenium.WebElement;

/**
 * Created by dys on 12.01.2015.
 */
public class CheckoutSteps extends BasicSteps {

    public CheckoutSteps(Pages pages) {
        super(pages);
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

}
