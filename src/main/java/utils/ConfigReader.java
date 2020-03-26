package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

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
	
	public static String getBrowser(Properties properties){
		if(properties.getProperty("BROWSER_NAME")==null) return "";
		else return properties.getProperty("BROWSER_NAME");
	}
	
	public static String getTimeOut(Properties properties){
		if(properties.getProperty("TIME_OUT_SECONDS")==null) return "";
		else return properties.getProperty("TIME_OUT_SECONDS");
	}
}
