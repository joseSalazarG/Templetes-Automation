@allure.label.owner:Raul_Leandro
@CC200
Feature: Todos por venezuela

  Background:
    Given Navego a la pagina web de CC200
    And Ingreso el codigo de area "United States" y el numero de telefono "2234567890"
    And Ingreso el codigo de OTP "222222"

  @venezuela
  @venezuela_001
  Scenario: Agregar elector por comunidad al perfil de Jefe de Comunidad UPPAZ
    When Ingreso a todos por venezuela
    And Agrego un elector
    Then Valido se agrego el elector
    And Elimino al elector agregado

  @venezuela
  @venezuela_002
  Scenario: Ver listado de electores por comunidad al perfil de Jefe de Comunidad UPPAZ
    When Ingreso a todos por venezuela
    Then Valido que estoy en el listado de electores

  @venezuela
  @venezuela_003
  Scenario: Buscar elector por comunidad al perfil de Jefe de Comunidad UPPAZ
    When Ingreso a todos por venezuela
    And Busco al elector de nombre "VASQUEZ  JOSE SALVADOR"
    Then Valido que se encontro al elector

  @venezuela
  @venezuela_004
  Scenario: Editar el numero de telefono del elector por comunidad al perfil de Jefe de Comunidad UPPAZ
    When Ingreso a todos por venezuela
    And Edito el numero de telefono de un miembro
    Then Valido se modifico el telefono
    And Restauro el numero modificado del elector

  @venezuela
  @venezuela_005
  Scenario: Eliminar elector por comunidad al perfil de Jefe de Comunidad UPPAZ
    When Ingreso a todos por venezuela
    And Agrego un elector
    And Elimino al elector agregado
    Then Valido no encontrar al elector agregado

  @venezuela
  @venezuela_006
  Scenario: Evitar registrar cedulas repetidas al perfil de Jefe de Comunidad UPPAZ
    When Ingreso a todos por venezuela
    And Consulto en base de datos la cedula "3334567"
    Then Valido esta cedula ya esta registrada

  @venezuela
  @venezuela_007
  Scenario: Evitar registrar numero de telefono repetido al perfil de Jefe de Comunidad UPPAZ
    When Ingreso a todos por venezuela
    And Agrego un miembro con numero de telefono "111222337"
    Then Valido este telefono ya esta registrado

  @venezuela
  @venezuela_008
  Scenario: Evitar registrar numero de telefono invalido al perfil de Jefe de Comunidad UPPAZ
    When Ingreso a todos por venezuela
    And Agrego un miembro con numero de telefono ""
    Then Valido que el numero de telefono es invalido