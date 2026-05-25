package runner;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@SelectPackages("features")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "steps")
@ConfigurationParameter(key = "cucumber.plugin", value = "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm,pretty")
@ConfigurationParameter(key = "cucumber.monochrome", value = "true")
@ConfigurationParameter(key = "cucumber.snippets", value = "CAMELCASE")
@ConfigurationParameter(key = "cucumber.filter.tags", value = "@All") // AQUI

public class TestRunner {
    
}

/*
    Ejecuta los tests desde maven con el siguiente comando:

    mvn test "-Dcucumber.filter.tags=@All"

    Luego el reporte de Allure se genera automáticamente en la carpeta target/allure-results con el historial de ejecuciones.

    Para visualizar el reporte de Allure ejecuta uno de los siguientes comandos

    1)  Servidor local: allure serve target/allure-results

    o

    2)  Crear un html: allure generate target/allure-results -o target/allure-report --clean
        Abre el reporte con: allure open target/allure-report
*/    
