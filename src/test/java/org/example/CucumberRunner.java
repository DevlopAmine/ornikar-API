package org.example;



import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty", "html:target/cucumber-reports"},
        features = {"src/test/resources/features"},
        glue = {"org.example"},
        tags = "")
public class CucumberRunner {
}
