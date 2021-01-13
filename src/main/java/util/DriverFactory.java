package util;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import pages.Page;

public class DriverFactory {

	protected static WebDriver driver;
	protected static WebDriverWait wait;
	protected static Page page;

	public void initDriver() {

		Properties properties = ConfigReader.readConfigFile();
		String BROWSER_NAME = ConfigReader.getBrowser(properties);
		try {
			switch (BROWSER_NAME) {
			case "f":

				break;
			case "chrome":
				if (DriverFactory.driver == null) {
					System.setProperty(Constants.CHROME_DRIVER, Constants.CHROME_DRIVER_PATH);
					DriverFactory.driver = new ChromeDriver();
					DriverFactory.wait = new WebDriverWait(DriverFactory.driver,
							Integer.parseInt(ConfigReader.getTimeOut(properties)));
					DriverFactory.driver.manage().window().maximize();
					DriverFactory.page = new Page(DriverFactory.driver, DriverFactory.wait);
				}
				break;
			case "ie":

				break;
			case "firefox":

				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
