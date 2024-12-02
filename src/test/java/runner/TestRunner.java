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
        features = { "src/test/resources/features" },   // Ruta de los archivos .feature
        glue = { "steps" },                             // Ruta de los archivos .java con los pasos
        plugin = {"io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm" },
        monochrome = true,
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        tags = "@Login" // Etiqueta para ejecutar pruebas específicas
)

public class TestRunner {

}