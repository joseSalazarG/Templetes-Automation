package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pom.baseStructure.BaseStructureLogic;

public class BaseStructureSteps {

    BaseStructureLogic baseStructureLogic = new BaseStructureLogic();

    @And("Agrego un miembro desde agregar miembro con la responsabilidad de {string}")
    public void agregoUnMiembroDesdeAgregarMiembro(String rol) {
        baseStructureLogic.agregoMiembroAgregarMiembro(rol);
    }

    @Then("Valido que agregue el miembro con estado {string}")
    public void validoQueAgregueElMiembro(String estado) {
        baseStructureLogic.validoAgregueMiembro(estado);
    }

    @And("Elimino el miembro")
    public void eliminoElMiembro() {
        baseStructureLogic.eliminoMiembro();
    }

    @When("Ingreso a listado de miembros")
    public void ingresoAListadoDeMiembros() {
        baseStructureLogic.ingresoListadoMiembros();
    }

    @Then("Valido que puedo ver el listado de miembros")
    public void validoQuePuedoVerElListadoDeMiembros() {
        baseStructureLogic.validoVerListadoMiembros();
    }

    @Then("Valido que se filtro por estado")
    public void validoQueSeFiltroPorEstado() {
        baseStructureLogic.validoFiltroEstado();
    }

    @And("Ingreso al detalle de un miembro")
    public void ingresoAlDetalleDeUnMiembro() {
        baseStructureLogic.ingresoDetalleMiembro();
    }

    @Then("Valido que se visualizan los detalles del miembro")
    public void validoQueSeVisualizanLosDetallesDelMiembro() {
        baseStructureLogic.validoDetalleMiembro();
    }

    @Then("Valido que puedo buscar un miembro")
    public void validoQuePuedoBuscarUnMiembro() {
        baseStructureLogic.validoBuscarMiembro();
    }

    @And("Edito un miembro desde listado de miembros")
    public void editoUnMiembroDesdeListadoDeMiembros() {
        baseStructureLogic.editarMiembro();
    }

    @Then("Valido que puedo editar el miembro")
    public void validoQuePuedoEditarElMiembro() {
        baseStructureLogic.validoEditarMiembro();
    }

    @And("Busco un miembro para eliminar")
    public void buscoUnMiembroParaEliminar() {
        baseStructureLogic.buscoMiembroEliminar();
    }

    @Then("Valido que puedo eliminar un miembro desde estructura base")
    public void validoQuePuedoEliminarUnMiembroDesdeEstructuraBase() {
        baseStructureLogic.validoEliminarmiembroEstructuraBase();
    }

    @When("Agrego un miembro desde equipo de trabajo")
    public void agregoUnMiembroDesdeEquipoDeTrabajo() {
        baseStructureLogic.agregoMiembroEquipoTrabajo();
    }

    @Then("Valido que se agrego el miembro a equipo de trabajo")
    public void validoQueSeAgregoElMiembroAEquipoDeTrabajo() {
        baseStructureLogic.validoAgregoMiembroEquipoTrabajo();
    }

    @And("Eliminar un miembro desde equipo de trabajo")
    public void eliminarUnMiembroDesdeEquipoDeTrabajo() {
        baseStructureLogic.eliminarMiembroEquipoTrabajo();
    }

    @Then("Valido que puedo ver los detalles de un miembro desde equipo de trabajo")
    public void validoQuePuedoVerLosDetallesDeUnMiembroDesdeEquipoDeTrabajo() {
        baseStructureLogic.validoVerDetallesMiembroEquipoTrabajo();
    }

    @When("Ingreso a listado de miembros de equipo de trabajo")
    public void ingresoAListadoDeMiembrosDeEquipoDeTrabajo() {
        baseStructureLogic.ingresoListadoMiembrosEquipoTrabajo();
    }

    @Then("Valido que puedo ver el listado de miembros de equipo trabajo")
    public void validoQuePuedoVerElListadoDeMiembrosDeEquipoTrabajo() {
        baseStructureLogic.validoVerListadoMiembrosEquipoTrabajo();
    }

    @Then("Valido que puedo buscar el miembro por nombre")
    public void validoQuePuedoBuscarElMiembroPorNombre() {
        baseStructureLogic.validoBuscarMiembroPorNombre();
    }

    @And("Edito un miembro desde equipo de trabajo")
    public void editoUnMiembroDesdeEquipoDeTrabajo() {
        baseStructureLogic.editarMiembroDeEquipo();
    }

    @Then("Valido que puedo editar el miembro desde equipo de trabajo")
    public void validoQuePuedoEditarElMiembroDesdeEquipoDeTrabajo() {
        baseStructureLogic.validoEditarMiembroDeEquipo();
    }

    @Then("Valido que elimine un miembro desde equipo de trabajo")
    public void validoQueElimineUnMiembroDesdeEquipoDeTrabajo() {
        baseStructureLogic.validoEliminoMiembroEquipoDeTrabajo();
    }
}