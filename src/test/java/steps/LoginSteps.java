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
        loginLogic.ingresoElUsuarioYLaContrasena(usuario, password);
    }

    @Then("Valido que me logueo de forma exitosa")
    public void validoQueMeLogueoDeFormaExitosa() {
        loginLogic.validoLoginExitoso();
    }

    @When("Hago click en el boton de login")
    public void Hago_click_en_el_boton_de_login() {
        loginLogic.hagoClickEnElBotonDeLogin();
    }

    @Then("Guardo las cookies de la sesion")
    public void Guardo_las_cookies_de_la_sesion() {
        loginLogic.guardarCookies();
    }

    @Then("Valido que cierro sesion de forma exitosa")
    public void Valido_que_cierro_sesion_de_forma_exitosa() {
       loginLogic.validoCierreSesionExitoso();
    }

    @When("Hago click en el boton de cerrar sesion")
    public void Hago_click_en_el_boton_de_cerrar_sesion() {
        loginLogic.hagoClickEnElBotonDeCerrarSesion();
    }

    @Given("Cargo las cookies")
    public void Cargo_las_cookies() {
        loginLogic.cargar_cookies();
    }

    @When("Hago click en el carrito")
    public void hagoClickEnElCarrito() {
        loginLogic.hagoClickEnElCarrito();
    }

    @Then("Valido el carrito esta vacio")
    public void validoElCarritoEstaVacio() {
        loginLogic.validoElCarritoEstaVacio();
    }

    @Then("Validar se visualiza un mensaje de error de credenciales")
    public void validarSeVisualizaUnMensajeDeErrorDeCredenciales() {
        loginLogic.validarSeVisualizaUnMensajeDeErrorDeCredenciales();
    }

  @When("Ejecuto el endpoint de login con un usuario cliente")
    public void login_usuarioCliente_endpoint() {
        loginLogic.endpointLogin();
    }

    @Then("Valido que el login es exitoso")
    public void validoQueElLoginEsExitoso() {
        loginLogic.validoLoginExitosoEndpoint();
    }

    @When("Ejecuto el endpoint de login con un usuario superadmin")
    public void login_usuarioSuperAdmin_endpoint() {
        loginLogic.endpointSuperAdmin();
    }

    @When("Ejecuto la request de obtención de información de superadmin con token de cliente")
    public void obtenerInformacionSuperAdmin_conTokenCliente() {
        loginLogic.endpointObtenerInformacionSuperAdminConTokenCliente();
    }
    @Then("Valido que el sistema deniegue el acceso por falta de permisos")
    public void validoQueElSistemaDeniegueElAccesoPorFaltaDePermisos() {
        loginLogic.validoAccesoDenegadoPorRol();
    }
}