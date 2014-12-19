package com.steps;

import com.pages.AbstractPage;
import com.utils.DAO;
import com.utils.Gmail;
import com.utils.LoadProperties;
import com.utils.Parser;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.pages.PageObject;
import net.thucydides.core.pages.Pages;
import net.thucydides.core.pages.WebElementFacade;
import net.thucydides.core.steps.ScenarioSteps;
import org.jsoup.nodes.Document;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.mail.Message;
import javax.mail.MessagingException;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @author Dmitry Sherstobitov QA LSE at intetics.tshop skype: dmitry_sherstobitov
 */
@SuppressWarnings("serial")
public class BasicSteps extends ScenarioSteps {

    public Message email;
    AbstractPage currentPage;
    String PAGE_PATH = "com.pages.";
    String color = "2px groove red";

    public BasicSteps(Pages pages) {
        super(pages);
        getPages().setDefaultBaseUrl(LoadProperties.loadProperty("url"));
        currentPage = (AbstractPage) AbstractPage.getCurrentPage();
    }

    @Step
    public void open_page(String page) throws ClassNotFoundException {

        @SuppressWarnings("unchecked") Class<PageObject> pageObjectClass = (Class<PageObject>) Class.forName(PAGE_PATH + page);
        getPages().get(pageObjectClass).open();
        AbstractPage.setCurrentPage((AbstractPage) getPages().get(pageObjectClass));
    }

    @Step
    public void click_on(String elementName) throws Exception {
        WebElement element = get_element(elementName);
        currentPage.$(element).waitUntilVisible();
        try {
            String xpath = AbstractPage.elements.get(elementName);
            currentPage.evaluateJavascript("(document.evaluate(\"" + xpath + "\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue)" +
                    ".style.border=\"" + color + "\";");
        } catch (Throwable e) {

        }
        currentPage.clickOn(element);
    }

    @Step
    public void click_on_link(String linktext) {
        String xpath = "(//*[@style = 'display: block;']//a[text()='" + linktext + "'])|(//a[text()='" + linktext + "'])";
        WebElement element = currentPage.findBy(xpath);
        currentPage.evaluateJavascript("(document.evaluate(\"" + xpath + "\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue)" +
                ".style.border=\"" + color + "\";");
        element.click();
    }

    @Step
    public void click_on_button(String buttonText) {
        String xpath = "(//*[@style = 'display: block;']//button[contains(text(), '" + buttonText + "')])|(//button[contains(text(), '" + buttonText + "')])";
        WebElement element = currentPage.findBy(xpath);
        currentPage.evaluateJavascript("(document.evaluate(\"" + xpath + "\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue)" +
                ".style.border=\"" + color + "\";");
        element.click();
    }

    @Step
    public void enter_in(String text, String elementName) throws Exception {

        WebElement element = get_element(elementName);
        String xpath = AbstractPage.elements.get(elementName);
        try {
            currentPage.evaluateJavascript("(document.evaluate(\"" + xpath + "\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue)" +
                    ".style.border=\"" + color + "\";");
        } catch (Exception e) {

        }

        if (!text.isEmpty()) {
            currentPage.typeInto(element, text);
        } else element.clear();
    }

    @Step
    public void is_element_available(String elementName) throws Exception {
        WebElement element = get_element(elementName);
        (currentPage.$(element)).waitUntilVisible();
    }

    @Step
    public void is_element_not_available(String elementName) throws Exception {
        WebElement element = get_element(elementName);
        (currentPage.$(element)).shouldNotBeVisible();
    }

    @Step
    public void select_from_dropdown(String itemName, String elementName) throws Exception {

        WebElementFacade dropdown = currentPage.$(get_element(elementName));
        String xpath = AbstractPage.elements.get(elementName);
        try {
            currentPage.evaluateJavascript("(document.evaluate(\"" + xpath + "\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue)" +
                    ".style.border=\"" + color + "\";");
        } catch (Throwable e) {

        }

        if (!itemName.equals("any")) {
            String optionText = get_full_text_from_element(itemName, elementName);
            dropdown.selectByVisibleText(optionText);
            show_message(currentPage.$(get_element(elementName)).getSelectedValue() + " was selected");

        } else {
            dropdown.selectByIndex(2);
            show_message(currentPage.$(get_element(elementName)).getSelectedValue() + " was selected");
        }
        new Gmail().clearEmailBox();
    }

    public void is_enabled(String elementName) throws Exception {
        WebElement element = get_element(elementName);
        if (!element.getTagName().equals("a")) {
            currentPage.$(get_element(elementName)).shouldBeEnabled();
        } else Assert.assertTrue(element.getAttribute("disabled") == null);
    }

    @Step
    public void is_disabled(String elementName) throws Exception {
        WebElement element = get_element(elementName);

        if (!element.getTagName().equals("a")) {
            Assert.assertTrue(elementName + " is enabled, but shouldn't", element.getAttribute("class").contains("disable") || element.getAttribute("class").contains("inactive"));
        } else {
            Assert.assertTrue(element.getAttribute("disabled").contains("true"));
        }

    }

    @Step
    public void is_checked(String elementName) throws Exception {
        WebElement element = get_element(elementName);
        Assert.assertTrue("" + elementName + " should be checked!", element.getAttribute("checked") != null);
    }

    @Step
    public void is_active(String elementName) throws Exception {
        WebElement element = get_element(elementName);
        Assert.assertTrue("" + elementName + " should be active!", element.getAttribute("class").equals("active"));

    }

    @Step
    public void press(Keys buttonName, String elementName) throws Exception {
        get_element(elementName).sendKeys(buttonName);
    }

    @Step
    public void press_key(int keyArg) throws AWTException {
        Robot robot = new Robot();
        robot.keyPress(keyArg);
        robot.keyRelease(keyArg);
    }

    @Step
    public void press_double_key(int keyArg1, int keyArg2) throws AWTException {
        Robot robot = new Robot();
        robot.keyPress(keyArg1);
        robot.keyPress(keyArg2);
        robot.keyRelease(keyArg1);
        robot.keyRelease(keyArg2);
    }

    @Step
    public void mouse_click(int keyArg1) throws AWTException {
        Robot robot = new Robot();
        robot.mousePress(keyArg1);
        robot.mouseRelease(keyArg1);
    }

    @Step
    public void move_mouse_to(int x, int y) throws AWTException {
        Robot robot = new Robot();
        robot.mouseMove(x, y);
    }

    @Step
    public void mouse_scroll(int times) throws AWTException {
        Robot robot = new Robot();
        robot.mouseWheel(times);
    }

    @Step
    public void set_clipboard_data(String string) {
        StringSelection stringSelection = new StringSelection(string);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
    }

    @Step
    public void has_options(String dropDown, String[] optionList) throws Exception {

        String error = "";
        List<String> options = currentPage.$(get_element(dropDown)).getSelectOptions();
        for (String option : optionList) {
            if (!options.contains(option.trim()))
                error += option + " wasn't found in the " + dropDown + Arrays.asList(options) + ";";
        }
        if (!error.isEmpty()) throw new Exception(error);
    }

    @Step
    public void element_attribute_value_is(String elementName, String attr, String expectedValue) throws Exception {
        WebElement element = get_element(elementName);
        if (attr.equals("text"))
            Assert.assertTrue(elementName + " test is " + element.getText() + ", should be " + expectedValue, element.getText().contains(expectedValue));
        else
            Assert.assertTrue(elementName + " " + attr + " value is " + element.getAttribute(attr) + ", should be " + expectedValue, element.getAttribute(attr).contains(expectedValue));
    }

    @Step
    public void see_message(String message) throws Exception {
        currentPage.waitForAllTextToAppear(message);
    }

    @Step
    public void wait_for_email(String subj, String waitInMin) throws Exception {
        new Gmail().waitForNewEmail(Integer.parseInt(waitInMin));
        email = Gmail.getGoalMessage(subj);
    }

    @Step
    public void clear_email_box() throws MessagingException {
        new Gmail().clearEmailBox();
    }

    @Step
    public void check_email_for_text(String text) throws InterruptedException, IOException, MessagingException {
        String email_text = Gmail.read_email_mime(email);
        Document content = Parser.getContent(email_text);
        Assert.assertTrue(text + " not found in " + content.text(), content.select("*:containsOwn(" + text + ")").size() > 0);
    }

    @Step
    public void go_back() {
        getDriver().navigate().back();
    }

    @Step
    public void refresh_page() {
        getDriver().navigate().refresh();
    }

    @Step
    public void update_db(String querry) throws Exception {
        new DAO().execute(querry);
    }

    @Step
    public void show_message(String message) {
    }

    @Step
    public void is_alert_display(String alertText) {
        Assert.assertTrue(currentPage.getAlert().getText().replace("\n", "").equals(alertText));
        currentPage.getAlert().accept();
    }

    @Step
    public void set_page(String pageName) throws ClassNotFoundException {

        @SuppressWarnings("unchecked") final Class<PageObject> pageObjectClass = (Class<PageObject>) Class.forName(PAGE_PATH + pageName);
        AbstractPage.setCurrentPage((AbstractPage) getPages().get(pageObjectClass));
        try {
            ExpectedCondition<Boolean> e = new ExpectedCondition<Boolean>() {
                @Override
                public Boolean apply(WebDriver driver) {
                    return (getPages().isCurrentPageAt(pageObjectClass));
                }
            };
            new WebDriverWait(getDriver(), 10).until(e);
        } catch (Exception e) {
            throw new IllegalStateException("Current " + pageName + " is not according to the expected url, current url is " + getDriver().getCurrentUrl());
        }
    }

    @Step
    public void wait_while_loading() {
        currentPage.waitForAbsenceOf("//div[@id = 'glass_pane']");
    }

    @Step
    public void wait_while_paypal_loading() {
        currentPage.waitForAbsenceOf("//img[@alt = 'Loading..']");
    }

    @Step
    public void validate(String errMsg) throws Exception {
        if (!errMsg.isEmpty()) throw new Exception(errMsg);
    }


    public WebElement get_element(String elementName) throws Exception {

        String locator = AbstractPage.elements.get(elementName);
        if (locator == null)
            throw new Exception("Element specified by '" + elementName + "' is not added in the " + AbstractPage.getCurrentPage().getClass().getName() + " class");

        WebElement element;
        try {
            element = currentPage.findBy(locator);
            return element;
        } catch (Throwable e) {
            return null;
        }
    }

    private String get_full_text_from_element(String text, String elementName) throws Exception {

        WebElementFacade element = currentPage.$(get_element(elementName)).findBy(By.xpath(".//*[contains(text(),'" + text + "')]"));
        String fullText = element.getText();

        if (fullText.isEmpty()) {
            throw new Exception("Option '" + text + "' not found in element '" + elementName + "'");
        } else {
            return fullText;
        }
    }
}
