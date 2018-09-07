package net.com.test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import net.com.pages.ProductPage;
import net.com.pages.ProductsListPage;
import net.com.pages.SearchPage;
import net.com.webdriver.WebDriverManager;

import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;
import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseTest {

	SearchPage searchPage;
	ProductsListPage productsListPage;
	ProductPage productPage;

	ExtentReports report = new ExtentReports(System.getProperty("user.dir")
			+ "\\test-output\\FlipkartTest.html");
	ExtentTest logger;

	@BeforeMethod
	public void classSetup(ITestContext ctx) {

		//Initialize logger
		logger = report.startTest("Verify Flipkart Add Product To Cart");

		// Initialize web driver
		String browserName = ctx.getCurrentXmlTest().getParameter("browser");
		//this.driver = new WebDriverSetup().getWebDriver(browserName);
		try {
			WebDriverManager.setDriver(ctx);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		if (WebDriverManager.getDriver() == null) {
			try {
				throw new Exception("unable to fetch browser for: " + browserName);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@AfterMethod
	public void classTeardown() {

        report.flush();
        try {
              if (WebDriverManager.getDriver() != null) {
              WebDriverManager.getDriver().quit();
          }
        } catch (Exception e) {
		  e.printStackTrace(); }
	}

    protected void passStep(String message) {
        logger.log(LogStatus.PASS, message);
    }

    protected void failStep(String message) throws IOException {

        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        String screenShotPath = GetScreenShot.capture(WebDriverManager.getDriver(), timeStamp);
        //logger.log(LogStatus.FAIL, result.getThrowable());
        logger.log(LogStatus.FAIL, "Snapshot below: " + logger.addScreenCapture(screenShotPath));

	    logger.addScreenCapture(System.getProperty("user.dir")
                        + "\\test-output\\FlipkartTestFaillureScreenshot.html");
        logger.log(LogStatus.FAIL, message);
    }
}
