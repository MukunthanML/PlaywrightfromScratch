package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;



public class LoginPageTest extends BaseTest {
	
	LoginPage loginPage; 

	@Test(priority = 1)
	public void loginPageNavigationTest() {
		loginPage = new LoginPage(page);
		boolean status = loginPage.login(prop.getProperty("username"),prop.getProperty("password"));
		Assert.assertTrue(status);
	}

}

