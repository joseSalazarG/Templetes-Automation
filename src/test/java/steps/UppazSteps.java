package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pom.uppaz.UppazLogic;

public class UppazSteps {

    UppazLogic uppazLogic = new UppazLogic();

    @When("Ingreso a UPPAZ")
    public void ingresarUPPAZ() {
        uppazLogic.ingresarUPPAZ();
    }

    @And("Agrego un miembro de rol {string} con cedula 11123456")
    public void agregarMiembro(String rol) {
        uppazLogic.agregarMiembro(rol);
    }

    @And("Consulto la cedula {string}")
    public void consultoLaCedula(String cedula) {
        uppazLogic.consultarCedula(cedula);
    }

    @Then("Valido se agrego el miembro UPPAZ")
    public void validarAgregarUPPAZ() {
        uppazLogic.validarAgregarUPPAZ();
    }

    @And("Elimino al miembro de rol {string} de cedula 11123456")
    public void eliminoAlMiembroDeCedula(String rol) {
        uppazLogic.eliminarMiembro(rol);
    }

    @And("Veo el listado de miembros")
    public void verListadoMiembros() {
        uppazLogic.verListadoMiembros();
    }

    @And("Veo el listado de miembros desde un difusor")
    public void verListadoMiembrosDifusor() {
        uppazLogic.verListadoMiembrosDifusor();
    }

    @Then("Valido que estoy en el listado de miembros")
    public void validoQueEstoyEnElListadoDeMiembros() {
        uppazLogic.validoEstoyListadoMiembros();
    }

    @And("Busco al miembro de nombre {string}")
    public void buscarMiembro(String nombre) {
        uppazLogic.buscarMiembro(nombre);
    }

    @Then("Valido que se visualiza el miembro buscado")
    public void validoQueSeVisualizaElMiembroBuscado() {
        uppazLogic.validoVisualizaMiembroBuscado();
    }

    @Then("Valido que la cedula ya esta registrada")
    public void validoQueLaCedulaYaEstaRegistrada() {
        uppazLogic.validoCedulaRegistrada();
    }

    @And("Consulto el telefono +58555555")
    public void consultarTelefono() {
        uppazLogic.consultarTelefono();
    }

    @Then("Valido que el numero esta registrado")
    public void validoQueElNumeroEstaRegistrado() {
        uppazLogic.validoNumeroTelefonoRegistrado();
    }

    @And("Edito el telefono")
    public void editarTelefono() {
        uppazLogic.editarTelefono();
    }

    @Then("Valido se edito el telefono")
    public void validoEditarTelefono() {
      uppazLogic.validoEditarTelefono();
    }

    @And("Restauro el numero modificado del miembro")
    public void restauroElNumeroModificadoDelMiembro() {
        uppazLogic.restaurarNumeroModificadoMiembro();
    }
}
