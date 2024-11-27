package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Step;
import pom.login.LoginLogic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class LoginSteps {

    LoginLogic loginLogic = new LoginLogic();

    @Step("Abro la pagina web")
    @Given("Navego a la pagina web")
    public void navegoPaginaWeb() {
        loginLogic.navegoPaginaWeb();
    }

    @Step("Ingreso el correo y la contraseña")
    @When("Ingreso el correo {string} y la contraseña {string}")
    public void ingresoElCorreoYLaContrasena(String correo, String password) {
        loginLogic.ingresoCorreoYContrasena(correo, password);
    }

    @Step("El coco")
    @Severity(SeverityLevel.CRITICAL)
    @Then("Valido que me logueo de forma exitosa")
    public void validoQueMeLogueoDeFormaExitosa() {
        loginLogic.validoLoginExitoso();
    }

    @Then("Valido cerrar sesion")
    public void validoCerrarSesion() {
        loginLogic.validoCerrarSesion();
    }
}