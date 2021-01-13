package pages;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import util.ExcelUtil;

/**
 * 
 * @author EL KOTB ZAKARIA
 *
 */
public class LoginPage extends BasePage {

	public static final Logger log = LoggerFactory.getLogger(LoginPage.class);

	public LoginPage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
		// TODO Auto-generated constructor stub
	}

	private String xPub_button = "/html/body/div[5]/div[2]/div/div[1]";
	private String email_field = "//*[@id='loginEmail']";
	private String password_field = "//*[@id='loginPassword']";
	private String login_button = "//span[text()='Se connecter']";
	private String error_sentence = "//div[contains(text(),'mot de passe incorrect')]";

	public LoginPage closePopUp() {
		LoginPage.log.info("wait pop up apear");
		this.waitVisibilityElement(By.xpath(this.xPub_button));
		WelcomPage.log.info("close pop up");
		this.click(By.xpath(this.xPub_button));
		return this;
	}

	public LoginPage login(XSSFRow row) throws IOException {
		LoginPage.log.info("wait pop up apear");
		this.writeText(By.xpath(this.email_field), ExcelUtil.getCellDataByName(row.getRowNum(), "EMAIL"));
		this.writeText(By.xpath(this.password_field), ExcelUtil.getCellDataByName(row.getRowNum(), "PASSWORD"));
		this.click(By.xpath(this.login_button));
		return this;
	}

	public LoginPage checkError() {
		String errorMsg = this.readText(By.xpath(this.error_sentence));
		errorMsg.trim();
		Assert.assertEquals(errorMsg, "Utilisateur/e-mail inconnu ou mot de passe incorrect !");
		return this;
	}
}
