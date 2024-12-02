package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import pom.group.GroupLogic;

public class GroupSteps {

    GroupLogic groupLogic = new GroupLogic();

    @And("Creo el grupo {string}")
    public void creoGrupo(String nombre) {
        groupLogic.creoGrupo(nombre);
    }

    @Then("Valido se creo el grupo")
    public void validoSeCreoElGrupo() {
        groupLogic.validoCreoGrupo();
    }

    @And("Elimino el grupo con el numero {string}")
    public void eliminoElGrupoConElNumero(String phone) {
        groupLogic.eliminoGrupo(phone);
    }

    @And("Envio un mensaje al grupo")
    public void envioUnMensajeAlGrupo() {
        groupLogic.envioMensajeGrupo();
    }

    @Then("Valido que se envio el mensaje al grupo")
    public void validoQueSeEnvioElMensajeAlGrupo() {
        groupLogic.validoEnvioMensajeGrupo();
    }

    @And("Veo la informacion del grupo")
    public void veoLaInformacionDelGrupo() {
        groupLogic.veoInformacionGrupo();
    }

    @Then("Valido que puedo ver la informacion del grupo")
    public void validoQuePuedoVerLaInformacionDelGrupo() {
        groupLogic.validoPuedoVerInformacionGrupo();
    }

    @And("Elimino el grupo")
    public void eliminoElGrupo() {
        groupLogic.eliminoGrupoCreado();
    }

    @Then("Valido que se elimino el grupo")
    public void validoQueSeEliminoElGrupo() {
        groupLogic.validoEliminoChatLLamado();
    }

    @And("Busco el grupo {string}")
    public void buscoElGrupo(String nombre) {
        groupLogic.buscoGrupo(nombre);
    }

    @Then("Valido que se visualiza el grupo")
    public void validoQueSeVisualizaElGrupo() {
        groupLogic.validoVisualizaGrupo();
    }

    @And("Veo los miembros del grupo")
    public void veoLosMiembrosDelGrupo() {
        groupLogic.veoMiembrosGrupo();
    }

    @Then("Valido abrir miembros del grupo")
    public void validoAbrirMiembrosGrupo() {
        groupLogic.validoAbrirMiembrosGrupo();
    }

    @And("Cambio el nombre a {string}")
    public void cambiarNombreGrupo(String nuevoNombre) {
        groupLogic.cambiarNombreGrupo(nuevoNombre);
    }

    @Then("Valido que se cambio el nombre del grupo")
    public void validoQueSeCambioElNombreDelGrupo() {
        groupLogic.validoCambioNombreGrupo();
    }

    @And("Actualizo la foto del grupo")
    public void actualizoLaFotoDelGrupo() {
        groupLogic.actualizoFotoPerfilGrupo();
    }

    @Then("Valido que se actualizo la fotol del grupo")
    public void validoQueSeActualizoLaFotolDelGrupo() {
        groupLogic.validoActualizoFotoGrupo();
    }

    @And("Envio un mensaje")
    public void envioUnMensaje() {
        groupLogic.envioMensaje();
    }

    @Then("Valido que se envio el mensaje")
    public void validoQueSeEnvioElMensaje() {
        groupLogic.validoEnvioMensaje();
    }

    @And("Envio un emoji")
    public void envioUnEmoji() {
        groupLogic.envioEmoji();
    }

    @Then("Valido que se envio el emoji")
    public void validoQueSeEnvioElEmoji() {
        groupLogic.validoEnvioEmoji();
    }

    @And("Envio un archivo de tipo {string}")
    public void envioUnArchivoDeTipo(String archivo) {
        groupLogic.envioArchivoTipo(archivo);
    }

    @Then("Valido que se envio la foto por chat")
    public void validoQueSeEnvioLaFotoPorChat() {
        groupLogic.validoEnvioFotoChat();
    }

    @Then("Valido que se envio un documento por chat")
    public void validoQueSeEnvioUnDocumentoPorChat() {
        groupLogic.validoEnvioDocumentoChat();
    }

    @And("Elimino un participante")
    public void eliminoUnParticipante() {
        groupLogic.eliminoParticipante();
    }

    @Then("Valido que se elimino el participante")
    public void validoQueSeEliminoElParticipante() {
        groupLogic.validoEliminoParticipante();
    }

    @And("Creo un grupo sin nombre")
    public void creoUnGrupoSinNombre() {
        groupLogic.creoGrupoSinNombre();
    }

    @Then("Valido que se visualiza el mensaje {string}")
    public void validoQueSeVisualizaElMensaje(String mensaje) {
        groupLogic.validoVisualizaMensaje(mensaje);
    }

    @And("Creo un grupo sin participantes")
    public void creoUnGrupoSinParticipantes() {
        groupLogic.creoGrupoSinParticipantes();
    }

    @Then("Valido que no puedo crear el grupo")
    public void validoQueNoPuedoCrearElGrupo() {
        groupLogic.validoNoPuedoCrearGrupo();
    }
}