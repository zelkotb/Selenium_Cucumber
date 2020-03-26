package steps;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.LoginPage;
import utils.DriverFactory;

public class FillStudentForm extends DriverFactory{
	
	@Given("^navitate to login \"([^\"]*)\"$")
	public void navitate_to_login(String url) throws Throwable {
	    page.getInstance(LoginPage.class).navigate();
	    
	}

	@Given("^submit \"([^\"]*)\" and \"([^\"]*)\"$")
	public void submit_and(String username, String password) throws Throwable {
	    page.getInstance(LoginPage.class).onLogin(username, password);
	}

	@Then("^user is logged in$")
	public void user_is_logged_in() throws Throwable {
	    String expected = "Student informations s";
	    String actual = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Student')]"))).getText();
	    assertEquals(actual, expected);
	    
	}

	@Given("^user fill the form$")
	public void user_fill_the_form() throws Throwable {
	    
	    
	}

	@When("^user submit$")
	public void user_submit() throws Throwable {
	    
	    
	}

	@Then("^pop up appear with successfull$")
	public void pop_up_appear_with_successfull() throws Throwable {
	    
	    
	}

}
