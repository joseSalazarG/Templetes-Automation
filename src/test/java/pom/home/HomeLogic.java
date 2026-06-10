package pom.home;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pom.home.HomePage;
import steps.Hooks;
//import static org.junit.Assert.*;


public class HomeLogic extends Hooks {

  HomePage homePage = new HomePage();
  private static final Logger log = LogManager.getLogger(HomeLogic.class);

  public void Cuando_hago_click_al_boton_productos() {
    String step = "Cuando hago click al boton productos";
    log.info(step);
      
    clickElement(homePage.getBtnProductos());
  }

  public void Busco_el_producto_Fancy_Green_Top() {
    String step = "Busco el producto Fancy Green Top";
    log.info(step);

    write(homePage.getInputBusqueda(), "Fancy Green Top");
    clickElement(homePage.getBtnBusqueda());
  }

  public void Verifico_que_unicamente_se_visualice_ese_producto() {
    String step = "Verifico que unicamente se visualice ese producto";
    log.info(step);

    assertTrue(elementIsDisplayed(homePage.getCamisaVerdeBusqueda()), "No se visualiza el Producto Buscado");  
  }
}
