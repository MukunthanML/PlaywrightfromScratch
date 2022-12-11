package pages;

import com.microsoft.playwright.Page;

public class LoginPage {

	private Page page;

	private String usernameFldLocator = "[data-test=\"username\"]";
	private String passwordFldLocator = "[data-test=\"password\"]";
	private String loginBtnLocator = "[data-test=\"login-button\"]";
	private String productsLblLocator = "Products";

	public LoginPage(Page page) {
		this.page = page;
	}

	public boolean login(String username, String password) {

		page.navigate("https://www.saucedemo.com/");
		page.locator(usernameFldLocator).click();
		page.locator(usernameFldLocator).fill(username);
		page.locator(passwordFldLocator).click();
		page.locator(passwordFldLocator).fill(password);
		page.locator(loginBtnLocator).click();
		return page.getByText(productsLblLocator).isVisible();
	}

}
