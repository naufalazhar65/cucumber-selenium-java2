package StepDefinitions;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)	
@CucumberOptions(
	features = "src/test/resources/Features/checkout.feature", 
	glue = { "StepDefinitions" }, 
	dryRun = false,
	plugin = { "pretty", "json:target/cucumber.json" },
	monochrome = true
	//publish = true
//	tags = "@validcheckout"

)

public class TestRunner {

}