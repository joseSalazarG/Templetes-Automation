package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pom.api.apiLogic;

public class ApiSteps {
    
    apiLogic apiLogic = new apiLogic();

  @When("Ejecuto el endpoint de login con un usuario cliente")
    public void login_usuarioCliente_endpoint() {
        apiLogic.endpointLoginUsuario();
    }

    @Then("Valido que el login es exitoso")
    public void validoQueElLoginEsExitoso() {
        apiLogic.validoLoginExitosoEndpoint();
    }

    @Given("Un administrador autenticado en el panel de gestión")
    public void unAdministradorAutenticadoEnElPanelDeGestion() {
        apiLogic.endpointLoginAdmin(); 
    }

    @When("Solicito la lista de todos los médicos registrados")
    public void solicitoLaListaDeTodosLosMedicosRegistrados() {
        apiLogic.endpointListarMedicos();
    }

    @Then("El sistema muestra el listado completo de los médicos del personal activo")
    public void elSistemaMuestraElListadoCompletoDeLosMedicosDelPersonalActivo() {
        apiLogic.validoListadoMedicosActivos();
    }
}