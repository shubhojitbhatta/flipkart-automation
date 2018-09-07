package net.com.webdriver;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;

public final class WebDriverManager {

	private static final ThreadLocal<WebDriver> WEB_DRIVER = new ThreadLocal<WebDriver>();

	private WebDriverManager() {

	}

	public static WebDriver getDriver() {
		return WEB_DRIVER.get();
	}

	public static void setDriver(ITestContext ctx) throws Exception {
		String browserName = ctx.getCurrentXmlTest().getParameter("browser");
		//System.out.println("Browser set = " + browserName);
		WebDriver driver = new WebDriverSetup().getWebDriver(browserName);
		WEB_DRIVER.set(driver);
	}

}
