package com.steps;

import com.pages.AbstractPage;
import com.pages.HomePage;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.pages.Pages;

/**
 * Created by dys on 09.01.2015.
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
}
