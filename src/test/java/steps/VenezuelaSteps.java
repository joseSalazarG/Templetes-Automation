package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pom.venezuela.VenezuelaLogic;

public class VenezuelaSteps {

    VenezuelaLogic venezuelaLogic = new VenezuelaLogic();

    @When("Ingreso a todos por venezuela")
    public void ingresoATodosPorVenezuela() {
        venezuelaLogic.ingresarTodosPorVenezuela();
    }

    @And("Agrego un elector")
    public void agregarElector() {
        venezuelaLogic.agregarElector();
    }

    @And("Agrego un miembro con numero de telefono {string}")
    public void agregarMiembroConNumero(String telefono) {
        venezuelaLogic.agregarMiembroConNumero(telefono);
    }

    @And("Consulto en base de datos la cedula {string}")
    public void consultarCedula(String cedula) {
        venezuelaLogic.consultarCedula(cedula);
    }

    @Then("Valido se agrego el elector")
    public void validarAgregarElector() {
        venezuelaLogic.validarAgregarElector();
    }

    @Then("Valido que estoy en el listado de electores")
    public void validoQueEstoyEnElListadoDeElectores() {
        venezuelaLogic.validoListadoElector();
    }

    @And("Busco al elector de nombre {string}")
    public void buscarElector(String nombre) {
        venezuelaLogic.buscarElector(nombre);
    }

    @Then("Valido que se encontro al elector")
    public void validoQueSeEncontroAlElector() {
        venezuelaLogic.validoEncontroElector();
    }

    @And("Elimino al elector agregado")
    public void eliminarElector() {
        venezuelaLogic.eliminarElector();
    }

    @Then("Valido no encontrar al elector agregado")
    public void validoNoEncontrarElector() {
        venezuelaLogic.validoNoEncontrarElector();
    }

    @And("Edito el numero de telefono de un miembro")
    public void editarTelefono() {
        venezuelaLogic.editarTelefono();
    }

    @Then("Valido se modifico el telefono")
    public void validoEditarNumeroDeTelefono() {
        venezuelaLogic.validoEditarTelefono();
    }

    @And("Restauro el numero modificado del elector")
    public void restauroElNumeroModificadoDelElector() {
        venezuelaLogic.restaurarNumeroModificadoElector();
    }

    @Then("Valido esta cedula ya esta registrada")
    public void validarCedulaRegistrada() {
        venezuelaLogic.validarCedulaRegistrada();
    }

    @Then("Valido este telefono ya esta registrado")
    public void validarTelefonoRegistrado() {
        venezuelaLogic.validarTelefonoRegistrado();
    }

    @Then("Valido que el numero de telefono es invalido")
    public void validoQueElNumeroDeTelefonoEsInvalido() {
        venezuelaLogic.validoNumeroTelefonoInvalido();
    }
}