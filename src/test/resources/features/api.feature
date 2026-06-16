@allure.label.layer:web
@allure.label.owner:Jose_Salazar
@All
Feature: Requests al backend del Ecommerce

@api
  Scenario: Login de Usuario Cliente ENDPOINT
    Given las credenciales de un usuario cliente
    Then valido autenticarme exitosamente

@admin @doctors
  Scenario: Listar todos los médicos activos
    Given las credenciales de un administrador
    When solicito la lista de todos los médicos activos
    Then valido ver unicamente a los médicos activos

@api @carrito
  Scenario: Crear un carrito
    When Proceso una orden medica
    Then Valido se creó una comanda exitosamente

@comandas @comanda
  Scenario: Comprobar el estado de una comanda registrada
    When Consulto el estado de una comanda "ec81aea8-4957-4a86-8465-3e37ec27842e"
    Then Valido recibir un codigo de respuesta 200