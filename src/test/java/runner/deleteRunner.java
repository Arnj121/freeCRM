package runner;
import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
@RunWith(Cucumber.class)
@CucumberOptions(
        features ="src/test/java/features", glue={"glueCode"},tags = {"@delete"},
        format = {"pretty","html:target/cucumber-reports/cucumber-pretty"
        ,"json:target/cucumber-reports/cucumber.json","rerun:target/cucumber-reports/rerun.txt"},
        plugin = {"json:target/cucumber-reports/cucumber.json"},monochrome = true)
public class deleteRunner{}