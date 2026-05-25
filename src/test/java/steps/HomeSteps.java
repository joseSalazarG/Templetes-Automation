package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pom.home.HomeLogic;

public class HomeSteps {
    HomeLogic homeLogic = new HomeLogic();

    
    @When("Cuando hago click al boton productos")
    public void Cuando_hago_click_al_boton_productos() {
        homeLogic.Cuando_hago_click_al_boton_productos();
    }

    @And("Busco el producto Fancy Green Top")
    public void Busco_el_producto_Fancy_Green_Top() {
         homeLogic.Busco_el_producto_Fancy_Green_Top();
    }

    @Then("Verifico que unicamente se visualice ese producto")
    public void Verifico_que_unicamente_se_visualice_ese_producto() {
        homeLogic.Verifico_que_unicamente_se_visualice_ese_producto();
    }
}
