package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pom.login.LoginLogic;

public class LoginSteps {

    LoginLogic loginLogic = new LoginLogic();

    @Given("Inicio sesion")
    public void inicioSesion() {
        loginLogic.login();
    }

    @When("Ingreso el codigo otp {string}")
    public void Ingreso_el_codigo_otp(String s) {
        // Write code here that turns the phrase above into concrete actions
    }

    @Then("Valido que inicio sesion satisfactoriamente")
    public void Valido_que_inicio_sesion_satisfactoriamente() {
        // Write code here that turns the phrase above into concrete actions
    }

    @When("Ingreso el codigo de area {string} y el numero de telefono {string}")
    public void Ingreso_el_codigo_de_area_y_el_numero_de_telefono(String s, String s2) {
        // Write code here that turns the phrase above into concrete actions
    }
}
