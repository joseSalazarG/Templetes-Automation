package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import pom.register.RegisterLogic;

public class RegisterSteps {

    RegisterLogic registerLogic = new RegisterLogic();

    @And("Ingreso a registro 1x10")
    public void ingresoARegistroX() {
        registerLogic.ingresoRegistro1x10();
    }

    @Then("Valido que estoy en el registro")
    public void validoQueEstoyEnElRegistro() {
        registerLogic.validoRegistro();
    }

    @And("Registro la cedula {string}")
    public void registroLaCedula(String cedula) {
        registerLogic.registroCedula(cedula);
    }

    @And("Registro un telefono {string}")
    public void registroUnTelefono(String telefono) {
        registerLogic.registroTelefono(telefono);
    }

    @Then("Valido que se vea el mensaje {string}")
    public void validoQueSeVeaElMensaje(String mensaje) {
        registerLogic.validoVeaMensaje(mensaje);
    }

    @And("Elimino la cedula {string}")
    public void eliminoLaCedula(String cedula) {
        registerLogic.eliminoCedula(cedula);
    }

    @And("Registro un telefono sin ingresarlo")
    public void registroUnTelefonoSinIngresarlo() {
        registerLogic.registroTelefonoSinIngresarlo();
    }

    @And("Registro la cedula sin ingresar el tipo de documento y dni")
    public void registroLaCedulaSinIngresarElTipoDeDocumentoYDni() {
        registerLogic.registroCedulaSinIngresarTipoDocDni();
    }

    @And("Alcanzo el limite de registro")
    public void alcanzoElLimiteDeRegistro() {
        registerLogic.alcanzoLimiteRegistro();
    }

    @And("Descargo el pdf")
    public void descargoElPdf() {
        registerLogic.descargoPdf();
    }

    @Then("Valido que se visualiza el archivo pdf")
    public void validoQueSeVisualizaElArchivoPdf() {
        registerLogic.validoVisualizaArchivoPdf();
    }
}