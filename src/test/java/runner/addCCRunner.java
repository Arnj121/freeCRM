package runner;
import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import io.cucumber.testng.AbstractTestNGCucumberTests;
@RunWith(Cucumber.class)
@CucumberOptions(features ="src/test/java/features",glue={"glueCode"},tags = {"@AdCC"},plugin = {"pretty","json:target/report.json"})
public class addCCRunner {}