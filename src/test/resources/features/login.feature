@allure.label.layer:web
@allure.label.owner:Jose_Salazar
@All
Feature: Login en la pagina NopCommerce

  # RECUERDA: el nombre del escenario no puede empezar con un "NO"  
  # el nombre del escenario describe lo que esperas que pase, no lo que no quieres

  Background:
    Given Navego a la pagina web

  @Login
  @Login_001
  Scenario: Iniciar sesión exitosamente
    When Hago click en el boton de login
    And Ingreso el usuario "maria11@coco.com" y la contraseña "qwerty123"
    Then Valido que me logueo de forma exitosa
    And Guardo las cookies de la sesion

  @Login
  @Login_002
  Scenario: Cerrar sesion exitosamente
    Given Cargo las cookies
    When Hago click en el boton de cerrar sesion
    Then Valido que cierro sesion de forma exitosa

  @Login
  @Login_003
  Scenario: Test de prueba
    When Hago click en el carrito
    Then Valido el carrito esta vacio

  @Login
  @Login_004
  Scenario: Iniciar sesion con credenciales erroneas
    When Hago click en el boton de login
    And Ingreso el usuario "maria11@noexiste" y la contraseña "holadaw"
    Then Validar se visualiza un mensaje de error de credenciales

