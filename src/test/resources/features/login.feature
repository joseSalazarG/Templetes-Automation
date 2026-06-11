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


  @api
  Scenario: Login de Usuario Cliente ENDPOINT
    When Ejecuto el endpoint de login con un usuario cliente
    Then Valido que el login es exitoso

  @api
  @superadmin
  Scenario: Login de Usuario SuperAdmin ENDPOINT
    When Ejecuto el endpoint de login con un usuario superadmin
    Then Valido que el login es exitoso

  @api 
  @security
  Scenario: Validar que un usuario cliente no pueda obtener información exclusiva de superadmin
    When Ejecuto la request de obtención de información de superadmin con token de cliente
    Then Valido que el sistema deniegue el acceso por falta de permisos