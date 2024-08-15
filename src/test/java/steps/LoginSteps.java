package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pom.login.LoginLogic;

public class LoginSteps {

    LoginLogic loginLogic = new LoginLogic();

    @Given("Inicio sesion en Futuro")
    public void inicioSesionEnFuturo() {
        loginLogic.loginFuturo();
    }
}
