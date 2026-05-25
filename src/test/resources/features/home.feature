@allure.label.layer:web
@allure.label.owner:Gobert_Piñago
@All
Feature: Home en la pagina NopCommerce

  # RECUERDA: el nombre del escenario no puede empezar con un "NO"  
  # el nombre del escenario describe lo que esperas que pase, no lo que no quieres

  Background:
    Given Navego a la pagina web

    @Home
    @Home_001
    @CamisaVerde
    Scenario:
      When Cuando hago click al boton productos
      And Busco el producto Fancy Green Top
      Then Verifico que unicamente se visualice ese producto
      
