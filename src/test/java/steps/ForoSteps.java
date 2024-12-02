package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import pom.foro.ForoLogic;

public class ForoSteps {

    ForoLogic foroLogic = new ForoLogic();

    @And("Ingreso al foro de opinion")
    public void ingresoAlForoDeOpinion() {
        foroLogic.ingresoConferencia();
    }

    @Then("Valido que estoy en el foro de opinion")
    public void validoQueEstoyEnElForoDeOpinion() {
        foroLogic.validoEstoyConferencia();
    }

    @And("Filtro por {string}")
    public void filtroPor(String filtro) {
        foroLogic.filtro(filtro);
    }

    @Then("Valido que se aplico el filtro")
    public void validoQueSeAplicoElFiltro() {
        foroLogic.validoAplicoFiltro();
    }
}