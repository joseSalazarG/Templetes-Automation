package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import pom.chatP2P.ChatP2PLogic;

public class ChatP2PSteps {

    ChatP2PLogic chatP2PLogic = new ChatP2PLogic();

    @And("Busco el chat {string}")
    public void buscoElChat(String nombre) {
        chatP2PLogic.buscoChat(nombre);
    }

    @And("Elimino el chat")
    public void eliminoElChat() {
        chatP2PLogic.eliminoChatCreado();
    }

    @Then("Valido que se elimino el chat")
    public void validoQueSeEliminoElChat() {
        chatP2PLogic.validoEliminoChatLLamado();
    }

    @Then("Valido que se visualiza el chat")
    public void validoQueSeVisualizaElChat() {
        chatP2PLogic.validoVisualizaChat();
    }

    @And("Actualizo el tema del chat")
    public void actualizoElTemaDelChat() {
        chatP2PLogic.actualizoTemaChat();
    }

    @Then("Valido que se actualizo el tema del chat")
    public void validoQueSeActualizoElTemaDelChat() {
        chatP2PLogic.validoActualizoTemaChat();
    }

    @Then("Valido abrir informacion del chat")
    public void validoAbrirInformacionChat() {
        chatP2PLogic.validoAbrirInformacionChat();
    }

    @And("Cambio el alias a {string}")
    public void cambioElAliasA(String nuevoAlia) {
        chatP2PLogic.cambioAlias(nuevoAlia);
    }

    @Then("Valido que se visualiza el nuevo alias")
    public void validoQueSeVisualizaElNuevoAlias() {
        chatP2PLogic.validoVisualizaNuevoAlias();
    }
}