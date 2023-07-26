package cucumber.Optionsdatadriven;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/java/Featuresfile/datadriven",plugin="json:target/jsonReports/cucumber-report.json",glue= {"stepDefinition/datadriven"})
public class TestRunner {

}
