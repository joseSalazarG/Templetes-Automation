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

    @When("Registro un nuevo medico con datos válidos")
    public void registroUnNuevoMedicoConDatosValidos() {
        apiLogic.endpointRegistrarMedico();
    }

    @Then("El sistema confirma que el registro del medico fue exitoso")
    public void elSistemaConfirmaQueElRegistroDelMedicoFueExitoso() {
        apiLogic.validoRegistroMedicoExitoso();
    }

    @When("Actualizo la especialidad del médico seleccionado")
    public void actualizoLaEspecialidadDelMedicoSeleccionado() {
        apiLogic.endpointActualizarMedicoParcial();
    }

    @Then("El sistema confirma que la actualización del médico fue exitosa")
    public void elSistemaConfirmaQueLaActualizacionDelMedicoFueExitosa() {
        apiLogic.validoActualizacionMedicaExitosa();
    }

    @When("Solicito el borrado lógico del médico seleccionado")
    public void solicitoElBorradoLogicoDelMedicoSeleccionado() {
        apiLogic.endpointEliminarMedico();
    }

    @Then("El sistema confirma que el médico fue eliminado exitosamente")
    public void elSistemaConfirmaQueElMedicoFueEliminadoExitosamente() {
        apiLogic.validoEliminacionMedicaExitosa();
    }

    @When("Solicito la generación de un código de descuento para el médico seleccionado")
    public void solicitoLaGeneracionDeUnCodigoDeDescuentoParaElMedicoSeleccionado() {
        apiLogic.endpointGenerarDescuentoMedico();
    }

    @Then("El sistema confirma que el código de descuento fue generado exitosamente")
    public void elSistemaConfirmaQueElCodigoDeDescuentoFueGeneradoExitosamente() {
        apiLogic.validoGeneracionDescuentoExitosa();
    }
    
}