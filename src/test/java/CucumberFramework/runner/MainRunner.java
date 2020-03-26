package CucumberFramework.runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@RunWith(Cucumber.class) //setting up a Cucumber runner class
@CucumberOptions( //basically settings for tests
		//location of features
		features = "src/test/java/CucumberFramework/features/" 
				// Location of step files
		,glue = {"steps"} 
		//Flexibility to target specific tests
		//,tags = {"@FuntionalTest" , "~@SmokeTest" , "~@End2EndTest"} 
		,tags = {"@important"}
		// check feature files have matching step definition
		,dryRun = false 
		// alters the output of the console window(readable/non readable)
		,monochrome = true 
		//for report generation
		,plugin = {"pretty","html:target/cucumber","json:target/cucumber.json"
				,"com.vimalselvam.cucumber.listener.ExtentCucumberFormatter:output/report.html"}
		)
public class MainRunner extends AbstractTestNGCucumberTests{

}
