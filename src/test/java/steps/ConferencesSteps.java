package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pom.conferences.ConferencesLogic;

public class ConferencesSteps {

    ConferencesLogic conferencesLogic = new ConferencesLogic();

    @When("Ingreso a conferencias")
    public void ingresoAConferencias() {
        conferencesLogic.ingresoConferencia();
    }

    @Then("Valido que estoy en la conferencia")
    public void validoQueEstoyEnLaConferencia() {
        conferencesLogic.validoEstoyConferencia();
    }
}
