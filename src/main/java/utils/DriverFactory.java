package utils;

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
				if (driver == null) {
					System.setProperty(Constants.CHROME_DRIVER, Constants.CHROME_DRIVER_PATH);
					driver = new ChromeDriver();
					wait = new WebDriverWait(driver, Integer.parseInt(ConfigReader.getTimeOut(properties)));
					driver.manage().window().maximize();
					page = new Page(driver,wait);
				}
				break;
			case "ie":

				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
}
