@allure.label.layer:web
@allure.label.owner:Jose_Salazar
@All
Feature: Requests al backend del Ecommerce

@api
  Scenario: Login de Usuario Cliente ENDPOINT
    Given las credenciales de un usuario cliente
    Then valido autenticarme exitosamente

# Escenarios de la crud de doctores, por defecto tengo el tag admin encendido, lo que hago es cambiar uno de los otros 4 por admin para que corran en co
#junto con login

@admin @doctors
  Scenario: Listar todos los médicos activos
    Given las credenciales de un administrador
    When solicito la lista de todos los médicos activos
    Then valido ver unicamente a los médicos activos

@registro @doctors
  Scenario: Registrar un nuevo medico exitosamente
    Given las credenciales de un administrador
    When solicito el registro de un nuevo medico con datos validos
    When busco el nuevo medico registrado en el listado de medicos activos
    Then valido que el medico registrado se encuentre en el listado

@actualizar @doctors
  Scenario: Actualizar la especialidad de un médico específico
    Given las credenciales de un administrador
    When actualizo la especialidad del médico seleccionado
    Then valido que la actualización del médico fue exitosa

@delete @doctors
  Scenario: Desactivar un médico haciendo un borrado lógico
    Given las credenciales de un administrador
    When solicito el borrado lógico del médico seleccionado
    Then valido que el medico fue desactivado correctamente   
    # siempre les va a fallar porque el médico ya se encuentra desactivado
    # la documentacion esta mal porque especifica que primero hace una comprobacion de si el medico esta en la lista de los activos 

@descuento @doctors
  Scenario: Generar un código de descuento para un médico validado exitosamente
    Given las credenciales de un administrador
    When Solicito la generación de un código de descuento para el médico seleccionado
    Then El sistema confirma que el código de descuento fue generado exitosamente