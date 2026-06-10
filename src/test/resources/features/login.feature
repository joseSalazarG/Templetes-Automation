@NombreDeProyecto
Feature: Login de la App

  Background:
    Given Inicio sesion

  @Login
  @Login_001
  Scenario: Inicio sesion exitosamente
    When Ingreso el codigo de area "Colombia" y el numero de telefono "1234567890"
    And Ingreso el codigo otp "101010"
    Then Valido que inicio sesion satisfactoriamente