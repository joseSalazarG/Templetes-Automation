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

    @When("solicito el registro de un nuevo medico con datos validos")
    public void solicitoRegistrarNuevoMedicoConDatosValidos() {
        apiLogic.solicitoRegistrarNuevoMedicoConDatosValidos();
    }

    @Then("valido registrar un nuevo medico desde la API exitosamente")
    public void validoRegistrarNuevoMedicoAPIExitosamente() {
        apiLogic.validoRegistrarNuevoMedicoAPIExitosamente();
    }

    @When("busco el nuevo medico registrado en el listado de medicos activos")
    public void buscarNuevoMedicoRegistradoEnListado() {
        apiLogic.buscarNuevoMedicoEnListadoActivos();
    }

    @Then("valido que el medico registrado se encuentre en el listado")
    public void validoMedicoRegistradoEnListado() {
        apiLogic.validoMedicoRegistradoEnListado();
    }

    @When("actualizo la especialidad del médico seleccionado")
    public void actualizarEspecialidadMedicoSeleccionado() {
        apiLogic.actualizarEspecialidadMedicoSeleccionado();
    }

    @Then("valido que la actualización del médico fue exitosa")
    public void validarActualizacionMedicoExitosa() {
        apiLogic.validarActualizacionMedicoExitosa();
    }

    @When("solicito el borrado lógico del médico seleccionado")
    public void solicitoElBorradoLogicoMedicoSeleccionado() {
        apiLogic.solicitoElBorradoLogicoMedicoSeleccionado();
    }

    @Then("valido que el medico fue desactivado correctamente")
    public void validoDesactivarMedico() {
        apiLogic.validoDesactivarMedico();
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