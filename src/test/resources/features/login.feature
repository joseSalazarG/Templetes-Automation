@allure.label.layer:web
@allure.label.owner:Jose_Salazar
@Templete
Feature: Login en la pagina web de Nombre

  Background:
    Given Navego a la pagina web

  @critical
  @Login @Login_001
  Scenario: Iniciar sesión
    When Ingreso el correo "xonavix163@mvpalace.com" y la contraseña ""
    Then Valido que me logueo de forma exitosa

  @minor
  @Login @Login_002
  Scenario: Cerrar sesión
    When Ingreso el correo "xonavix163@mvpalace.com" y la contraseña "87654321"
    Then Valido cerrar sesion