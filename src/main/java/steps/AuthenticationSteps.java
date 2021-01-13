package steps;

import org.apache.poi.xssf.usermodel.XSSFRow;

import cucumber.api.java.en.Given;
import pages.LoginPage;
import pages.WelcomPage;
import util.DriverFactory;
import util.ExcelUtil;

/**
 * 
 * @author EL KOTB ZAKARIA
 *
 */
public class AuthenticationSteps extends DriverFactory {

	@Given("^navitate to welcom page$")
	public void navitate_to_welcom_page() throws Throwable {
		ExcelUtil.setFileName("Authentication.xlsx");
		ExcelUtil.setExcelFileSheet("Failed Authentication");
		int LastRow = ExcelUtil.getLastRowNum("Failed Authentication");
		for (int i = 1; i <= LastRow; i++) {
			ExcelUtil.setCellDataByName("", i, "Statut scenario");
			ExcelUtil.setRowNumber(i);
			ExcelUtil.setColumnName("Statut scenario");
			DriverFactory.page.getInstance(WelcomPage.class).navigate(ExcelUtil.getRowData(i));
			this.navigate_to_login_page();
			this.login(ExcelUtil.getRowData(i));
			this.user_is_logged_in_error();
			ExcelUtil.setCellDataByName("Success", i, "Statut scenario");
		}
	}

	// @Given("^navigate to login page$")
	public void navigate_to_login_page() throws Throwable {
		DriverFactory.page.getInstance(WelcomPage.class).goToLoginPage();
	}

	// @Given("^login$")
	public void login(XSSFRow row) throws Throwable {
		DriverFactory.page.getInstance(LoginPage.class).login(row);

	}

	// @Then("^user is logged in error$")
	public void user_is_logged_in_error() throws Throwable {
		DriverFactory.page.getInstance(LoginPage.class).checkError();
	}
}
