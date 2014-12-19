package com.pages;

import net.thucydides.core.annotations.At;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.WebDriver;

@DefaultUrl("/accounts/login/")
@At(".*/accounts/login/.*")
public class LoginPage extends AbstractPage {

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	@Override
	public void init_elements() {

		elements.putAll(get_default_elements());
		elements.put("Username field", "//input[@id = 'id_username']");
		elements.put("Password field", "//input[@id = 'id_password']");
		elements.put("Login button", "//input[@value = 'Login']");
		elements.put("Forgot password link", "//a[contains(@href, 'password_reset')]");
		elements.put("Reset email field", "//input[@id = 'id_email']");
		elements.put("Reset button", "//input[@value = 'Reset']");
	}
}
