package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pom.api.apiLogic;

public class ApiSteps {
    
    apiLogic apiLogic = new apiLogic();

    @Given("las credenciales de un usuario cliente")
    public void CredencialesUsuarioCliente() {
        apiLogic.credencialesUsuarioCliente();
    }

    @Then("valido autenticarme exitosamente")
    public void validoAuntenticarmeExitosamente() {
        apiLogic.validoAuntenticarmeExitosamente();
    }

    @Given("las credenciales de un administrador")
    public void CredencialesAdministrador() {
        apiLogic.credencialesSuperAdmin(); 
    }

    @When("solicito la lista de todos los médicos activos")
    public void solicitarListaMedicosActivos() {
        apiLogic.solicitarListaMedicosActivos();
    }

    @Then("valido ver unicamente a los médicos activos")
    public void validoVerUnicamenteMedicosActivos() {
        apiLogic.validoVerUnicamenteMedicosActivos();
    }

    @When("Proceso una orden medica")
    public void proceso_una_orden_medica() {
        apiLogic.procesarOrdenMedica();
    }

    @Then("Valido se creó una comanda exitosamente")
    public void Valido_se_creo_una_comanda_exitosamente() {
        apiLogic.validarCreacionComanda();
    }

    @When("Consulto el estado de una comanda {string}")
    public void consulto_el_estado_de_una_comanda(String idComanda) {
        apiLogic.consultarEstadoComanda(idComanda);
    }

    @Then("Valido recibir un codigo de respuesta 200")
    public void valido_recibir_un_codigo_de_respuesta() {
        apiLogic.validarCodigoRespuesta(200);
    }
}