package base;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.microsoft.playwright.Page;

import factory.PlaywrightFactory;

public class BaseTest {

	PlaywrightFactory pf;
	protected Page page;
	protected Properties prop;
	protected Logger logger;

	@BeforeTest
	public void setup() {
		pf = new PlaywrightFactory();

		prop = pf.init_prop();

		page = pf.initBrowser(prop);

		logger = LoggerFactory.getLogger(BaseTest.class);
		logger.info("Playwright Automation Logger started");
		logger.info(prop.getProperty("browser") + " browser was selected");

	}

	@AfterTest
	public void tearDown() {
		page.context().browser().close();
		logger.info("Playwright Automation Logger stopped\n");
	}

}
