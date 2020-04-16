package co.com.santander.integration.steps;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features/",
        plugin = {"pretty", "html:target/cucumber/samtel"},
        glue = "com.samtel.integration.steps")
public class AcceptanceIT {
}
