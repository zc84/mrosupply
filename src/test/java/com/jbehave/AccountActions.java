package com.jbehave;

import net.thucydides.core.annotations.Steps;
import org.jbehave.core.annotations.When;

/**
 * @author Dmitry Sherstobitov
 */

public class AccountActions extends BasicFlow {

    @Steps
    AccountActions steps;

    @When("delete all creadit cards")
    public void delete_cards() throws Exception {
        steps.delete_cards();
    }
}
