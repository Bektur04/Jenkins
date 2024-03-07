package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"html:target/cucumber.html", "json:target/report.json"},
        features = "src/test/resources/feature",
        glue = "step_definitions",
        tags = "@seller",
        dryRun = false // make it true if u want to run dry to get implementation for steps
)
public class CukesRunner {
}
