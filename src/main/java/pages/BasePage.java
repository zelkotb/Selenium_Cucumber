package pages;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

/**
 * 
 * @author EL KOTB ZAKARIA
 *
 */
public class BasePage extends Page {

	public int count = 1;

	public static final Logger Log = LoggerFactory.getLogger(BasePage.class);

	public BasePage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
	}

	public String convertDecimal(double value) {
		DecimalFormat df = new DecimalFormat("#");
		String result = df.format(value);
		return result;
	}

	// waitVisibilityAllElement Method
	public void waitVisibilityAllElement(By elementBy) {
		BasePage.Log.info("wait visibility of element : " + elementBy.toString());
		this.wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(elementBy));
	}

	// waitVisibilityAllElement Method
	public void waitVisibilityElement(By elementBy) {
		BasePage.Log.info("wait visibility of element : " + elementBy.toString());
		this.wait.until(ExpectedConditions.visibilityOfElementLocated(elementBy));
	}

	// waitClickable Method
	public void waitClickable(By elementBy) {
		BasePage.Log.info("wait for element to be clickable : " + elementBy.toString());
		this.wait.until(ExpectedConditions.elementToBeClickable(elementBy));
	}

	// wait for the element to re-appear in the DOM
	public void waitReappear(By elementBy) {
		BasePage.Log.info("wait for the element to re-appear in the DOM : " + elementBy.toString());
		this.wait.until(ExpectedConditions.stalenessOf(this.driver.findElement(elementBy)));
	}

	// Click Method
	public void click(By elementLocation) {
		try {
			this.isElementPresent(elementLocation);
			BasePage.Log.info("click on the element " + elementLocation.toString());
			this.driver.findElement(elementLocation).click();
		} catch (NoSuchElementException e) {
			BasePage.Log.error("Element " + elementLocation + " don't exist, the method click failed");
		}

	}

	// Click Method
	public void clickexpectedElement(By elementLocation, By expectedElement) {
		BasePage.Log.info("click on the element : " + elementLocation.toString() + " the expected element is : "
				+ expectedElement.toString());
		try {
			do {
				this.driver.findElement(elementLocation).click();
				this.wait.until(ExpectedConditions.elementToBeClickable(expectedElement));
				this.count++;
			}

			while ((this.count < 5) && !this.driver.findElement(expectedElement).isEnabled()
					&& !this.driver.findElement(expectedElement).isDisplayed());
		}

		catch (NoSuchElementException e) {
			BasePage.Log.error("Element " + elementLocation + " don'r exist, the method clickexpectedElement failed");
		}
	}

	// Write Text
	public void writeText(By elementLocation, String text) {
		String textSaisie;
		try {
			do {
				BasePage.Log.info("type the value  : " + text + " in the element : " + elementLocation);
				this.isElementPresent(elementLocation);
				this.driver.findElement(elementLocation).clear();
				this.driver.findElement(elementLocation).sendKeys(text);
				BasePage.Log.info("input written in the input : "
						+ this.driver.findElement(elementLocation).getAttribute("value"));
				textSaisie = this.driver.findElement(elementLocation).getAttribute("value");
				this.count++;
			} while ((this.count < 5) && !textSaisie.equals(text));

			if (this.count == 5) {
				BasePage.Log.error("type attempt failed after " + this.count + " attempts");
			}

		} catch (NoSuchElementException e) {
			BasePage.Log.error("Element " + elementLocation + " don't exist, the method writeText failed");
		}
	}

	// Read Text
	public String readText(By elementLocation) {
		BasePage.Log.info("read text on the element " + elementLocation.toString());
		return this.driver.findElement(elementLocation).getText();
	}

	// Read Text
	public String readTextAttribute(By elementLocation, String attributeToGet) {
		BasePage.Log.info("read text on the element " + elementLocation.toString() + " has attribut " + attributeToGet);
		return this.driver.findElement(elementLocation).getAttribute(attributeToGet);
	}

	// clear Text
	public void clearText(By elementLocation) {
		BasePage.Log.info("delete the text in the element " + elementLocation.toString());
		this.driver.findElement(elementLocation).clear();
	}

	// Assert
	public void assertEquals(By elementBy, String expectedText, String attributeToGet) {
		BasePage.Log
				.info("Comparison of text in the element " + elementBy.toString() + " with the text " + expectedText);
		this.waitVisibilityAllElement(elementBy);

		if (attributeToGet.equals("value")) {
			Assert.assertEquals(this.readTextAttribute(elementBy, attributeToGet), expectedText);
		} else if (attributeToGet.equals("option")) {
			Select select = new Select(this.driver.findElement(elementBy));
			WebElement option = select.getFirstSelectedOption();
			String defaultItem = option.getText();
			// System.out.println("text option"+defaultItem);
			Assert.assertEquals(defaultItem, expectedText);

		} else {
			Assert.assertEquals(this.readText(elementBy), expectedText);
		}
	}

	public void assertInt(By elementBy, String expectedText) {
		BasePage.Log.info(
				"Comparison of the value of the element " + elementBy.toString() + " with the value " + expectedText);
		int valueElement = Integer.parseInt(this.readText(elementBy));
		int valueText = Integer.parseInt(expectedText);
		Assert.assertEquals(valueElement, valueText);
	}

	public void assertText(By elementBy, String expectedText) {
		BasePage.Log.info(
				"Comparison of the value of the element " + elementBy.toString() + " with the value " + expectedText);
		Assert.assertEquals(this.driver.findElement(elementBy).getText(), expectedText);
	}

	// Choose window to handle
	public void chooseWindow(int numWindow) {
		BasePage.Log.info("choose window using window handles");
		Set<String> AllWindowHandles = this.driver.getWindowHandles();
		String window = (String) AllWindowHandles.toArray()[numWindow];
		this.driver.switchTo().window(window);
		this.waitForLoad(this.driver);
		this.driver.manage().window().maximize();
	}

	// Close window
	public void closeWindow(int numWindow) {
		BasePage.Log.info("clode window");
		Set<String> AllWindowHandles = this.driver.getWindowHandles();
		String window = (String) AllWindowHandles.toArray()[numWindow];
		this.driver.switchTo().window(window).close();
	}

	// Verify element is Present
	public boolean isElementPresent(By elementBy) {
		BasePage.Log.info("verify the existence of the element : " + elementBy.toString());
		try {
			this.driver.findElement(elementBy);
			return true;
		} catch (NoSuchElementException e) {
			BasePage.Log.error("Element " + elementBy + " not displayed, the method isElementPresent failed");
			Assert.assertFalse(true);
		}
		return false;
	}

	public void StaleElementHandleByID(By elementBy) {
		int Counter = 0;

		do {
			try {
				if (this.driver.findElement(elementBy).isEnabled()
						&& this.driver.findElement(elementBy).isDisplayed()) {
					Counter = Counter + 1;
					this.driver.findElement(elementBy).click();
					break;
				}
			} catch (StaleElementReferenceException ex) {
				System.out.println("Caught Stale Element here " + elementBy + "for the " + Counter + " time");
			}
		} while (Counter < 200);
	}

	public void selectDropDownList(By elementBy, String Value) {
		BasePage.Log.info("Selection de la dropdownlist : " + elementBy.toString());
		Select dropDownList = new Select(this.driver.findElement(elementBy));
		dropDownList.selectByVisibleText(Value);
	}

	public void waitForLoad(WebDriver driver) {
		new WebDriverWait(driver, 30).until((ExpectedCondition<Boolean>) wd -> ((JavascriptExecutor) wd)
				.executeScript("return document.readyState").equals("complete"));
	}

	public void clickJS(By elementBy) {
		JavascriptExecutor js = (JavascriptExecutor) this.driver;
		js.executeScript("arguments[0].click();", this.driver.findElement(elementBy));
	}

	public void scrollRight(By elementBy, int nombre) {
		int i;
		for (i = 0; i < nombre; i++) {
			this.driver.findElement(elementBy).sendKeys(Keys.RIGHT);
		}

	}

	public void scrollIntoViewClick(By elementBy) {
		JavascriptExecutor js = (JavascriptExecutor) this.driver;
		WebElement element = this.driver.findElement(elementBy);
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		js.executeScript("arguments[0].click();", element);
	}

	public String getRegex(By elementBy, String patternSentence) {
		String txt = this.readText(elementBy);
		Pattern pat = Pattern.compile(patternSentence);
		Matcher mat = pat.matcher(txt);

		if (mat.find()) {
			System.out.println("Matched Expression " + mat.group(0)); // whole matched expression
		}
		return mat.group(0);
	}

	// if string contains '.' substring from 0 to '.'
	public String substringToDot(String str) {
		if (str.contains(".")) {
			return str.substring(0, str.indexOf("."));
		}
		return str;
	}

	// pour la confirmation de la recherche
	public String isConfirmeNumberExiste(By elementBy, String patternSentence) {
		String txt = this.readText(elementBy);
		String pattern = this.substringToDot(patternSentence);
		if (txt.contains(pattern)) {
			return pattern;
		} else {
			throw new RuntimeException("conforming number is missing");
		}
	}

	public List<String> getRegexNew(By elementBy, String patternSentence, String attributeToGet) {

		String txt = this.readTextAttribute(elementBy, attributeToGet);
		List<String> allMatches = new ArrayList<String>();
		Matcher m = Pattern.compile(patternSentence).matcher(txt);
		while (m.find()) {
			allMatches.add(m.group());
		}
		return allMatches;

	}

	public List<String> getRegexFromCell(String value, String patternSentence) {
		List<String> allMatches = new ArrayList<String>();
		Matcher m = Pattern.compile(patternSentence).matcher(value);
		while (m.find()) {
			allMatches.add(m.group());
		}
		return allMatches;

	}

	public String randomNumber() {

		int random = (int) ((Math.random() * ((900000 - 100000) + 1)) + 100000);

		String randomvalue = String.valueOf(random);

		return randomvalue;
	}

}
