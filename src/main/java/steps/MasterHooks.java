package steps;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import utils.DriverFactory;


public class MasterHooks extends DriverFactory{
	
	public static final Logger log = LoggerFactory.getLogger(MasterHooks.class);

	@Before
	public void setup() {
		initDriver();
	}
	
	@After
	public void teardownAndScreenOnFailure(Scenario scenario) {
		try {
			//taking screen when scenario fails
			if(scenario.isFailed() && driver != null) {
				scenario.embed(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES), "image/png");
				driver.quit();
				driver = null;
			}
			if(driver != null) {
				driver.manage().deleteAllCookies();
				driver.quit();
				driver = null;
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
	}
}
