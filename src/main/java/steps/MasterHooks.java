package steps;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vimalselvam.cucumber.listener.Reporter;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import util.DriverFactory;

/**
 * 
 * @author EL KOTB ZAKARIA
 *
 */
public class MasterHooks extends DriverFactory {

	public static final Logger log = LoggerFactory.getLogger(MasterHooks.class);

	@Before
	public void setup() {
		this.initDriver();
	}

	@After
	public void teardownAndScreenOnFailure(Scenario scenario) {
		try {
			// taking screen when scenario fails
			if (scenario.isFailed() && (DriverFactory.driver != null)) {

				String screenshotName = scenario.getName().replaceAll(" ", "_");
				// Convert web driver object to TakeScreenshot
				TakesScreenshot scrShot = ((TakesScreenshot) DriverFactory.driver);

				// Call getScreenshotAs method to create image file
				File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);

				// Move image file to new destination
				File DestFile = new File(System.getProperty("user.dir") + "\\output\\" + screenshotName + ".png");

				// Copy file at destination
				FileUtils.copyFile(SrcFile, DestFile);
				scenario.embed(FileUtils.readFileToByteArray(SrcFile), "image/png");
				Reporter.addScreenCaptureFromPath(
						System.getProperty("user.dir") + "\\output\\" + screenshotName + ".png");
				DriverFactory.driver.quit();
				DriverFactory.driver = null;
			}
			if (DriverFactory.driver != null) {
				DriverFactory.driver.manage().deleteAllCookies();
				DriverFactory.driver.quit();
				DriverFactory.driver = null;
			}
		} catch (Exception e) {
			MasterHooks.log.error(e.getMessage());
			DriverFactory.driver.quit();
		}

	}
}
