package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pom.login.LoginLogic;

public class LoginSteps {

    LoginLogic loginLogic = new LoginLogic();

    @Given("Navego a la pagina web")
    public void navegoPaginaWeb() {
        loginLogic.navegoPaginaWeb();
    }

    @When("Ingreso el usuario {string} y la contraseña {string}")
    public void ingresoElUsuarioYLaContrasena(String usuario, String password) {
        loginLogic.ingresarUsuarioYContrasena(usuario, password);
    }

    @Then("Valido que me logueo de forma exitosa")
    public void validoQueMeLogueoDeFormaExitosa() {
        loginLogic.validarLoginExitoso();
    }

    @When("Hago click en el boton de login")
    public void Hago_click_en_el_boton_de_login() {
        loginLogic.hacerClickEnBotonLogin();
    }

    @Then("Guardo las cookies de la sesion")
    public void Guardo_las_cookies_de_la_sesion() {
        loginLogic.guardarCookies();
    }

    @Then("Valido que cierro sesion de forma exitosa")
    public void Valido_que_cierro_sesion_de_forma_exitosa() {
       loginLogic.validarCierreSesionExitoso();
    }

    @When("Hago click en el boton de cerrar sesion")
    public void Hago_click_en_el_boton_de_cerrar_sesion() {
        loginLogic.hacerClickEnBotonCerrarSesion();
    }

    @Given("Cargo las cookies")
    public void Cargo_las_cookies() {
        loginLogic.cargarCookies();
    }

    @When("Hago click en el carrito")
    public void hagoClickEnElCarrito() {
        loginLogic.hacerClickEnCarrito();
    }

    @Then("Valido el carrito esta vacio")
    public void validoElCarritoEstaVacio() {
        loginLogic.validarCarritoEstaVacio();
    }

    @Then("Validar se visualiza un mensaje de error de credenciales")
    public void validarSeVisualizaUnMensajeDeErrorDeCredenciales() {
        loginLogic.validarVisualizarMensajeDeErrorDeCredenciales();
    }

}