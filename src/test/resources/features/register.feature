@allure.label.layer:web
@allure.label.owner:Jose_Salazar
@All
Feature: Registro en la pagina NopCommerce

  # RECUERDA: el nombre del escenario no puede empezar con un "NO"
  # el nombre del escenario describe lo que esperas que pase, no lo que no quieres

  Background:
    Given Navego a la pagina web

  @Register
  @Register_001
  Scenario: Registro correo ya existente
    When Hago click en el boton de login
    And Registro el nombre "Pepito" y la correo "maria11@coco.com"
    Then Valido se visualiza el error correo ya existe
