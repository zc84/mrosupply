package com.jbehave;

import com.data.DataProvider;
import com.steps.BasicSteps;
import com.utils.DAO;
import com.utils.Parser;
import net.thucydides.core.annotations.Steps;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.junit.Assert;
import org.openqa.selenium.Keys;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Dmitry Sherstobitov
 *         QA LSE at intetics.com
 *         skype: dmitry_sherstobitov
 */
public class BasicFlow {

    @Steps
    BasicSteps basicSteps;

    @Given("user on the '$page' page")
    @When("go to '$page' page")
    public void user_on_specified_page(String page) throws ClassNotFoundException {
        basicSteps.open_page(page);
    }

    @Given("'$text' info message")
    public void info_message(String text) {
    }

    @When("click on '$elementName'")
    public void click_on(String elementName) throws Exception {
        basicSteps.click_on(elementName);
    }

    @When("click '$linktext' link")
    public void click_on_link(String linktext) {
        basicSteps.click_on_link(linktext);
    }

    @When("click '$buttonText' button")
    public void click_on_button(String buttonText) {
        basicSteps.click_on_button(buttonText);
    }

    @When("enter '$text' in '$elementName'")
    public void enter_in(String text, String elementName) throws Exception {
        basicSteps.enter_in(text, elementName);
    }

    @When("press '$buttonName' for '$elementName'")
    public void press(Keys buttonName, String elementName) throws Exception {
        basicSteps.press(buttonName, elementName);
    }

    @When("select file to upload")
    public void select_file_to_upload() throws AWTException, InterruptedException {
        Thread.sleep(2000);
        basicSteps.set_clipboard_data(new File(DataProvider.TEST_FILES_TO_UPLOAD).getAbsolutePath());
        basicSteps.press_double_key(KeyEvent.VK_CONTROL, KeyEvent.VK_V);
        basicSteps.press_key(KeyEvent.VK_ENTER);
        basicSteps.press_key(KeyEvent.VK_ESCAPE);
    }

    @When("select '$text' from '$elementName'")
    public void select_from_dropdown(String text, String elementName) throws Exception {
        basicSteps.select_from_dropdown(text, elementName);
    }

    @When("wait while loading")
    public void wait_while_loading() throws Exception {
        basicSteps.wait_while_loading();
    }

    @When("wait '$milsecs'")
    public void waitabit(String milsecs) {
        basicSteps.waitABit(Long.parseLong(milsecs));
    }

    @Then("see '$text' message")
    public void see_message(String message) throws Exception {
        basicSteps.see_message(message);
    }

    @Then("'$element' available")
    public void element_available(String elementName) throws Exception {
        basicSteps.is_element_available(elementName);
    }

    @Then("'$element' not available")
    public void element_not_available(String elementName) throws Exception {
        basicSteps.is_element_not_available(elementName);
    }

    @Then("'$element' enabled")
    public void element_enabled(String elementName) throws Exception {
        basicSteps.is_enabled(elementName);
    }

    @Then("'$element' disabled")
    public void element_disabled(String elementName) throws Exception {
        basicSteps.is_disabled(elementName);
    }

    @Then("'$element' checked")
    public void is_checked(String elementName) throws Exception {
        basicSteps.is_checked(elementName);
    }

    @Then("'$element' active")
    public void is_active(String elementName) throws Exception {
        basicSteps.is_active(elementName);
    }

    @Then("'$dropdownName' has '$optionList' options")
    public void dropdown_has_options(String dropDown, String optionList) throws Exception {
        basicSteps.has_options(dropDown, optionList.split(";"));
    }

    @Then("'$attr' of '$elementName' is '$expectedValue'")
    public void element_attribute_value_is(String elementName, String attr, String expectedValue) throws Exception {
        basicSteps.element_attribute_value_is(elementName, attr, expectedValue);
    }

    @Then("email '$subj' recieved after '$mins' mins")
    public void email_recieved(String subj, String mins) throws Exception {
        basicSteps.wait_for_email(subj, mins);
    }

    @Then("email contains text '$expetctedTest'")
    public void email_contains_text(String text) throws Exception {
        basicSteps.check_email_for_text(text);
    }

    @Then("go back")
    public void go_back() {
        basicSteps.go_back();
    }

    @Then("'$alertText' alert display")
    public void is_alert_display(String alertText) {
        basicSteps.is_alert_display(alertText);
    }

    @Then("user on the '$page'")
    public void user_on_the_page(String pageName) throws ClassNotFoundException {
        basicSteps.set_page(pageName);
    }

    @Then("'$columnList' columns has '$expectedValueList' value in '$goalTable' table where '$whereClause'")
    public void is_db_has_value(String columnList, String goalTable, String expectedValueList, String whereClause) throws Exception {

        Map<String, String> results = new DAO().get_results_from("SELECT " + columnList + " FROM " + goalTable + " WHERE " + whereClause, columnList);
        Iterator it = results.entrySet().iterator();
        int i = 0;
        while (it.hasNext()) {
            Map.Entry<?, ?> pairs = (Map.Entry<?, ?>) it.next();
            Assert.assertTrue(pairs.getKey() + " col value is " + pairs.getValue() + ", should be " + expectedValueList.split(",")[i],
                    pairs.getValue().equals(expectedValueList.split(",")[i]));
            i++;
        }
    }

    @Then("validate '$text'")
    public void validate(String text) throws Exception {
        basicSteps.validate(text);
    }

    public String getBuildMeta() throws Exception {

        String version = "";
        String buildDate = "";

        try {
            Document doc = Parser.getContent(Connection.Method.GET, basicSteps.getPages().getDefaultBaseUrl() + "/version.jsp");

            version = doc.select("body tr:contains(Project version)").text().replace("Project version: ", "");
            buildDate = doc.select("body tr:contains(Project build date)").text().replace("Project build date: ", "");
        } catch (Throwable e) {

        }
        return "Build# " + version + " from " + buildDate;
    }
}
