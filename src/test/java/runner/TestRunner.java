package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

/**
 * Clase TestRunner para ejecutar pruebas de Cucumber con JUnit.
 * Anotada con @RunWith y @CucumberOptions para configurar cómo se ejecutarán
 * las pruebas.
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        features = { "src/test/resources/features" },
        glue = { "steps" },
        plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:", "json:target/cucumber-reports.json" },
        monochrome = true,
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        tags = "@login"
)

public class TestRunner {

}