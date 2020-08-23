package runners;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;


@RunWith(Cucumber.class)
@CucumberOptions(
		features = "classpath:features",
		glue = "stepdefinitions",
		plugin = {"pretty", "html:target/cucumber-html-report"},
		tags = {"@RegressionTest"},
		monochrome = true
		)
public class RegressionRunner {

}
