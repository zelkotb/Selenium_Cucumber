package runner;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.junit.AfterClass;
import org.junit.runner.RunWith;

import com.vimalselvam.cucumber.listener.Reporter;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import util.ConfigReader;

/**
 * 
 * @author EL KOTB ZAKARIA
 *
 */
@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/features", glue = { "steps" }, // for report generation
		plugin = { "pretty", "html:target/cucumber", "json:target/cucumber.json",
				"com.vimalselvam.cucumber.listener.ExtentCucumberFormatter:output/report.html" })
public class MainRunner {

	@AfterClass
	public static void writeExtentReport() throws UnknownHostException {
		Reporter.loadXMLConfig(new File(ConfigReader.getReportConfigPath()));
		Reporter.setSystemInfo("User Name", System.getProperty("user.name"));
		Reporter.setSystemInfo("Time Zone", System.getProperty("user.timezone"));
		Reporter.setSystemInfo("Machine", System.getProperty("os.name"));
		Reporter.setSystemInfo("Selenium", "3.141.59");
		Reporter.setSystemInfo("Maven", "3.6.3");
		Reporter.setSystemInfo("Java Version", System.getProperty("java.version"));
		Reporter.setSystemInfo("IP@", InetAddress.getLocalHost().toString());
	}
}
