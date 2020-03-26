package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * 
 * @author Zakaria
 * 
 * all methods that are common between pages
 *
 */
public class BasePage extends Page{

	public BasePage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
	}

	public void write(String xpath, String s) {
		driver.findElement(By.xpath(xpath)).sendKeys(s);
	}
	
	public void click(String xpath) {
		driver.findElement(By.xpath(xpath)).click();
	}
	
	public void waitForElement(String xpath) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
	}
}
