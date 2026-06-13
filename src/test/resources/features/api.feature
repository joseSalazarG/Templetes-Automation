@allure.label.layer:web
@allure.label.owner:Jose_Salazar
@All
Feature: Requests al backend del Ecommerce

@api
  Scenario: Login de Usuario Cliente ENDPOINT
    When Ejecuto el endpoint de login con un usuario cliente
    Then Valido que el login es exitoso

@admin @doctors
  Scenario: Listar todos los médicos activos
    Given Un administrador autenticado en el panel de gestión
    When Solicito la lista de todos los médicos registrados
    Then El sistema muestra el listado completo de los médicos del personal activo

@registro @doctors
  Scenario: Registrar un nuevo medico exitosamente
    Given Un administrador autenticado en el panel de gestión
    When Registro un nuevo medico con datos válidos
    Then El sistema confirma que el registro del medico fue exitoso

@actualizar @doctors @patchDoctor
  Scenario: Actualizar parcialmente la especialidad de un médico específico
    Given Un administrador autenticado en el panel de gestión
    When Actualizo la especialidad del médico seleccionado
    Then El sistema confirma que la actualización del médico fue exitosa

@delete @doctors @deleteDoctor
  Scenario: Realizar el borrado lógico de un médico específico
    Given Un administrador autenticado en el panel de gestión
    When Solicito el borrado lógico del médico seleccionado
    Then El sistema confirma que el médico fue eliminado exitosamente    

@admin @doctors @doctorDiscount
  Scenario: Generar un código de descuento para un médico validado exitosamente
    Given Un administrador autenticado en el panel de gestión
    When Solicito la generación de un código de descuento para el médico seleccionado
    Then El sistema confirma que el código de descuento fue generado exitosamente