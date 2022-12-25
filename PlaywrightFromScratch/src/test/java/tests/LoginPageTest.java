package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import utills.ExcelUtills;



public class LoginPageTest extends BaseTest {
	
	LoginPage loginPage; 
	ExcelUtills excelUtills;

	@Test(priority = 1)
	public void loginPageNavigationTest() {
		loginPage = new LoginPage(page);
		boolean status = loginPage.login(prop.getProperty("username"),prop.getProperty("password"));
		Assert.assertTrue(status);
		logger.info("AUT Login was successfull");
	}
	
	@DataProvider(name = "logindata")
    public Object[][] Authentication() throws Exception{
		
		excelUtills = new ExcelUtills(prop.getProperty("excelpath"),prop.getProperty("excelsheet")); 

         Object[][] testObjArray = excelUtills.getTableArray();

         return (testObjArray);

		}
	
	
	@Test(dataProvider="logindata")
	public void loginPageNavigationTestWithExcel(String username, String password) {
		loginPage = new LoginPage(page);
		boolean status = loginPage.login(username,password);
		Assert.assertTrue(status);
	}
	

}

