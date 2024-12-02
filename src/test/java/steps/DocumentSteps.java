package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pom.Document.DocumentLogic;

public class DocumentSteps {

    DocumentLogic documentLogic = new DocumentLogic();

    @And("Ingreso a documentos")
    public void ingresoADocumentos() {
        documentLogic.ingresoDocumentos();
    }

    @Then("Valido que puedo ver los documentos en menu")
    public void validoQueSePuedoVerLosDocumentosEnMenu() {
        documentLogic.validoVerDocumentosMenu();
    }

    @When("Abro documentos")
    public void abroDocumentos() {
        documentLogic.abroDocumentos();
    }

    @Then("Valido ver el listado de documentos")
    public void validoVerElListadoDeDocumentos() {
        documentLogic.validoVerListadoDocumentos();
    }

    @When("Abro audios")
    public void abroAudios() {
        documentLogic.abroAudios();
    }

    @Then("Valido ver el listado de audios")
    public void validoVerElListadoDeAudios() {
        documentLogic.validoVerListadoAudios();
    }

    @When("Abro videos")
    public void abroVideos() {
        documentLogic.abroVideos();
    }

    @Then("Valido ver el listado de videos")
    public void validoVerElListadoDeVideos() {
        documentLogic.validoVerListadoVideos();
    }

    @When("Abro imagenes")
    public void abroImagenes() {
        documentLogic.abroImagenes();
    }

    @Then("Valido ver el listado de imagenes")
    public void validoVerElListadoDeImagenes() {
        documentLogic.validoVerListadoImagenes();
    }

    @Then("Valido abrir un documento")
    public void validoAbrirUnDocumento() {
        documentLogic.validoAbrirUnDocumento();
    }

    @Then("Valido ver el listado de otros documentos")
    public void validoVerElListadoDeOtrosDocumentos() {
        documentLogic.validoVerListadoOtrosDocumentos();
    }

    @Then("Valido abrir un audio")
    public void validoAbrirUnAudio() {
        documentLogic.validoAbrirUnAudio();
    }

    @Then("Valido abrir un video")
    public void validoAbrirUnVideo() {
        documentLogic.validoAbrirUnVideo();
    }

    @Then("Valido abrir una imagen")
    public void validoAbrirUnaImagen() {
        documentLogic.validoAbrirUnaImagen();
    }

    @When("Abro otros documentos")
    public void abroOtrosDocumentos() {
        documentLogic.abroOtrosDocumentos();
    }

    @Then("Valido abrir otros documentos")
    public void validoAbrirOtrosDocumentos() {
        documentLogic.validoAbrirOtrosDocumentos();
    }
}
