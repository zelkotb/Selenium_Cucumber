package pages;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import util.ExcelUtil;

/**
 * 
 * @author EL KOTB ZAKARIA
 *
 */
public class WelcomPage extends BasePage {

	public static final Logger log = LoggerFactory.getLogger(WelcomPage.class);

	public WelcomPage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
		// TODO Auto-generated constructor stub
	}

	private String monCompte_button = "//div[text()=\"Mon compte\"]";

	public void navigate(XSSFRow row) throws IOException {
		WelcomPage.log.info("navigate to welcom page");
		this.driver.get(ExcelUtil.getCellDataByName(row.getRowNum(), "URL"));
	}

	public void goToLoginPage() {
		WelcomPage.log.info("go to login page method");
		this.click(By.xpath(this.monCompte_button));
	}

}
