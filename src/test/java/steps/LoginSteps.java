package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pom.login.LoginLogic;

public class LoginSteps {

    LoginLogic loginLogic = new LoginLogic();

    @Given("Navego a la pagina web")
    public void navegoPaginaWeb() {
        loginLogic.navegoPaginaWeb();
    }

    @When("Ingreso el correo {string} y la contraseña {string}")
    public void ingresoElCorreoYLaContrasena(String correo, String password) {
        loginLogic.ingresoCorreoYContrasena(correo, password);
    }

    @Then("Valido que me logueo de forma exitosa")
    public void validoQueMeLogueoDeFormaExitosa() {
        loginLogic.validoLoginExitoso();
    }

    @Then("Valido cerrar sesion")
    public void validoCerrarSesion() {
        loginLogic.validoCerrarSesion();
    }
}