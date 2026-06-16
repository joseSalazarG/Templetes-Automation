@allure.label.layer:web
@allure.label.owner:Jose_Salazar
@All
Feature: Requests al backend del Ecommerce

@admin
  Scenario: Login de Usuario Cliente ENDPOINT
    When Ejecuto el endpoint de login con un usuario cliente
    Then Valido que el login es exitoso

@admin @doctors
  Scenario: Listar todos los médicos activos
    Given Un administrador autenticado en el panel de gestión
    When Solicito la lista de todos los médicos registrados
    Then El sistema muestra el listado completo de los médicos del personal activo

@api @carrito
  Scenario: Crear un carrito
    When Proceso una orden medica
    Then Valido se creó una comanda exitosamente

@comandas @comanda
  Scenario: Comprobar el estado de una comanda registrada
    When Consulto el estado de una comanda "ec81aea8-4957-4a86-8465-3e37ec27842e"
    Then Valido recibir un codigo de respuesta 200