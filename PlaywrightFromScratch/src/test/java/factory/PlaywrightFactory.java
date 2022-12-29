package factory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Base64;
import java.util.Properties;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import utills.PropertyUtills;

public class PlaywrightFactory {

	Playwright playwright;
	Browser browser;
	BrowserContext browserContext;
	Page page;
	Properties prop;
	PropertyUtills propertyUtills = new PropertyUtills();;

	private static ThreadLocal<Browser> tlBrowser = new ThreadLocal<Browser>();
	private static ThreadLocal<BrowserContext> tlBrowserContext = new ThreadLocal<BrowserContext>();
	private static ThreadLocal<Page> tlPage = new ThreadLocal<Page>();
	private static ThreadLocal<Playwright> tlPlaywright = new ThreadLocal<Playwright>();

	public static Playwright getPlaywright() {
		return tlPlaywright.get();
	}

	public static Browser getBrowser() {
		return tlBrowser.get();
	}

	public static BrowserContext getBrowserContext() {
		return tlBrowserContext.get();
	}

	public static Page getPage() {
		return tlPage.get();
	}

	public Page initBrowser(Properties prop) {

		String browserName = prop.getProperty("browser").trim();

		String headlessModeStr = prop.getProperty("headless").trim();

		boolean headlessModeVal = true;

		if (headlessModeStr.equalsIgnoreCase("false")) {

			headlessModeVal = false;
		}

		System.out.println("browser name is : " + browserName);

		// playwright = Playwright.create();
		tlPlaywright.set(Playwright.create());

		switch (browserName.toLowerCase()) {
		case "chromium":
			tlBrowser.set(
					getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setHeadless(headlessModeVal)));
			break;
		case "firefox":
			tlBrowser.set(
					getPlaywright().firefox().launch(new BrowserType.LaunchOptions().setHeadless(headlessModeVal)));
			break;
		case "safari":
			tlBrowser
					.set(getPlaywright().webkit().launch(new BrowserType.LaunchOptions().setHeadless(headlessModeVal)));
			break;
		case "chrome":
			tlBrowser.set(getPlaywright().chromium()
					.launch(new LaunchOptions().setChannel("chrome").setHeadless(headlessModeVal)));
			break;
		case "edge":
			tlBrowser.set(getPlaywright().chromium()
					.launch(new LaunchOptions().setChannel("msedge").setHeadless(headlessModeVal)));
			break;

		default:
			System.out.println("please pass the right browser name......");
			break;
		}

		tlBrowserContext.set(getBrowser().newContext());
		tlPage.set(getBrowserContext().newPage());
		getPage().navigate(prop.getProperty("url").trim());
		return getPage();

	}

	/**
	 * this method is used to initialize the properties from config file
	 */
	public Properties init_prop() {

		try {
			prop = propertyUtills.getProperties("./src/test/resources/config/config.properties");
			String env = prop.getProperty("env");
			if (env == null)
				env = "QA";
			prop = propertyUtills.getProperties(prop, "./src/test/resources/env/" + env.toUpperCase() + ".properties");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;

	}

	/**
	 * take screenshot
	 * 
	 */

	public static String takeScreenshot() {
		String path = System.getProperty("user.dir") + "/target/screenshots/" + LocalDate.now()+"__"+System.currentTimeMillis()
				+ ".png";
		getPage().screenshot(new Page.ScreenshotOptions().setPath(Paths.get(path)).setFullPage(true));

				getPage().screenshot(new
				Page.ScreenshotOptions().setPath(Paths.get(path)).setFullPage(true));

		byte[] buffer = getPage().screenshot(new Page.ScreenshotOptions().setPath(Paths.get(path)).setFullPage(true));
		String base64Path = Base64.getEncoder().encodeToString(buffer);

		return base64Path;
	}

}
