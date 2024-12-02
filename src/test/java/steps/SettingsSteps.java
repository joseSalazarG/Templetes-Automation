package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

import pom.settings.SettingsLogic;


public class SettingsSteps {
    SettingsLogic settingsLogic = new SettingsLogic();

    @And("Ingreso a configuracion")
    public void IngresoAConfiguracion(){
        settingsLogic.IngresoConfiguracion();
    }

    @And("Actualizo el nombre de perfil {string} a {string}")
    public void actualizoElNombreDePerfilA(String nombreActual, String nombreNuevo) {
        settingsLogic.actualizoNombrePerfil2(nombreActual, nombreNuevo);
    }

    @Then("Valido que se cambio el nombre")
    public void validarCambiarNombre() {
        settingsLogic.validarCambiarNombre();
    }

    @And("Actualizo el nombre de perfil con numeros {string}")
    public void actualizoElNombreDePerfilConNumeros(String numeros) {
        settingsLogic.actualizoNombrePerfilNumeros(numeros);
    }

    @Then("Valido que no se pueda cambiar el nombre")
    public void validoQueNoSePuedaCambiarElNombre() {
        settingsLogic.validoNoPuedeCambiarNombre();
    }

    @And("Actualizo el email de perfil a {string}")
    public void actualizoElEmailDePerfilA(String email) {
        settingsLogic.actualizoEmailPerfil(email);
    }

    @Then("Valido que se cambio el email")
    public void validarCambiarEmail() {
        settingsLogic.validarCambiarEmail();
    }

    @And("Actualizo el email con uno no valido {string}")
    public void actualizoElEmailConUnoNoValido(String email) {
        settingsLogic.actualizoEmailNoValido(email);
    }

    @Then("Valido que no se pueda cambiar el email")
    public void validoQueNoSePuedaCambiarElEmail() {
        settingsLogic.validoNoPuedeCambiarEmail();
    }

    @Then("Valido que el email ya existe")
    public void validoQueElEmailYaExiste() {
        settingsLogic.validoEmailExiste();
    }

    @And("Actualizo la foto de perfil")
    public void actualizoLaFotoDePerfil() {
        settingsLogic.actualizoFotoPerfil();
    }

    @Then("Valido que se cambio la foto de perfil")
    public void validoQueSeCambioLaFotoDePerfil() {
        settingsLogic.validoCambioFotoPerfil();
    }
}