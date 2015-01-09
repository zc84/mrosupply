package com.steps;

import com.pages.AccountPage;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.pages.Pages;

/**
 * @author Dmitry Sherstobitov
 */

@SuppressWarnings("serial")
class AccountSteps extends BasicSteps {

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
}
