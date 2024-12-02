package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pom.login.LoginLogic;

public class LoginSteps {

    LoginLogic loginLogic = new LoginLogic();

    @Given("Navego a la pagina web de CC200")
    public void navegoALaPaginaWebDeCC() {
        loginLogic.navegoPaginaWebCC200();
    }

    @When("Ingreso el codigo de area {string} y el numero de telefono {string}")
    public void ingresoElCodigoDeAreaYElNumeroDeTelefono(String area, String numero) {
        loginLogic.ingresoCodigoAreaNumeroTelefono(area, numero);
    }

    @And("Ingreso el codigo de OTP {string}")
    public void ingresoElCodigoDeOTP(String otp) {
        loginLogic.ingresoCodigoOtp(otp);
    }

    @Then("Valido que me logueo de forma exitosa")
    public void validoQueMeLogueoDeFormaExitosa() {
        loginLogic.validoLogueoFormaExitosa();
    }

    @Then("Valido que se visualice el mensaje de error {string}")
    public void validoQueSeVisualiceElMensajeDeError(String mensaje) {
        loginLogic.validoVisualiceMensajeError(mensaje);
    }

    @Then("Valido que se visualice el mensaje de error de OTP {string}")
    public void validoQueSeVisualiceElMensajeDeErrorDeOTP(String mensaje) {
        loginLogic.validoVisualiceMensajeErrorOTP(mensaje);
    }

    @And("Cierro la sesion")
    public void cierroLaSesion() {
        loginLogic.cierroSesion();
    }

    @Then("Valido que se cerro la sesion")
    public void validoQueSeCerroLaSesion() {
        loginLogic.validoCerroSesion();
    }
}