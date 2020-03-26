package pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LoginPage extends BasePage{
	
	public static final Logger log = LoggerFactory.getLogger(LoginPage.class);

	public LoginPage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
		// TODO Auto-generated constructor stub
	}
	
	private String username = "//*[@id='mat-input-0']";
	private String password = "//*[@id='mat-input-1']";
	private String submit_btn = "//*[text()='Submit']";
	
	public void onLogin(String username, String password) {
		log.info("onLogin method");
		write(this.username, username);
		write(this.password, password);
		click(submit_btn);
	}
	
	public void navigate() {
		driver.get("http://localhost:4200/login");
	}
	

}
