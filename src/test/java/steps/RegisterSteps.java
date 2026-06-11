package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import pom.register.RegisterLogic;

public class RegisterSteps {

    RegisterLogic registerLogic = new RegisterLogic();

    @And("Registro el nombre {string} y la correo {string}")
    public void registroElNombreYLaCorreo(String nombre, String correo) {
        registerLogic.registroElNombreYLaCorreo(nombre, correo);
    }

    @Then("Valido se visualiza el error correo ya existe")
    public void validoSeVisualizaElErrorCorreoYaExiste() {
        registerLogic.validoSeVisualizaElErrorCorreoYaExiste();
    }

}
