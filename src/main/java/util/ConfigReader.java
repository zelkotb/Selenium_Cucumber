package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * 
 * @author EL KOTB ZAKARIA
 *
 */
public class ConfigReader {

	public static Properties readConfigFile() {

		File file = new File(Constants.CONFIG_FILE_PATH);
		Properties properties = new Properties();
		try (InputStream in = new FileInputStream(file)) {
			properties.load(in);
			return properties;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public static String getReportConfigPath() {
		String reportConfigPath = Constants.REPOT_CONFIG_FILE_PATH;
		if (reportConfigPath != null) {
			return reportConfigPath;
		} else {
			throw new RuntimeException(
					"Report Config Path not specified in the Configuration.properties file for the Key:reportConfigPath");
		}
	}

	public static String getBrowser(Properties properties) {
		if (properties.getProperty("BROWSER_NAME") == null) {
			return "";
		} else {
			return properties.getProperty("BROWSER_NAME");
		}
	}

	public static String getTimeOut(Properties properties) {
		if (properties.getProperty("TIME_OUT_SECONDS") == null) {
			return "";
		} else {
			return properties.getProperty("TIME_OUT_SECONDS");
		}
	}
}
