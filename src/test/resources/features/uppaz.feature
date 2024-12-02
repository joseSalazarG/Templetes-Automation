@allure.label.owner:Raul_Leandro
@CC200
Feature: UPPAZ en la pagina web CC200

  Background:
    Given Navego a la pagina web de CC200

  @uppaz
  @uppaz_001
  Scenario Outline: Agregar miembro UPPAZ con el perfil Jefe de Comunidad UPPAZ
    When Ingreso el codigo de area "United States" y el numero de telefono "2234567890"
    And Ingreso el codigo de OTP "222222"
    And Ingreso a UPPAZ
    And Agrego un miembro de rol "<rol>" con cedula 11123456
    Then Valido se agrego el miembro UPPAZ
    And Elimino al miembro de rol "<rol>" de cedula 11123456

    Examples:
      | rol                             |
      | EQUIPO COORDINADOR DE COMUNIDAD |
      | MOTORIZADOS                     |
      | EQUIPO DE CALLE                 |

  @uppaz
  @uppaz_002
  Scenario: Ver listado de miembros con el perfil Jefe de Comunidad UPPAZ
    When Ingreso el codigo de area "United States" y el numero de telefono "2234567890"
    And Ingreso el codigo de OTP "222222"
    And Ingreso a UPPAZ
    And Veo el listado de miembros
    Then Valido que estoy en el listado de miembros

#  Se comenta debido a que ya no contamos con un dato de prueba que tenga el perfil difusor
#  @uppaz
#  @uppaz_003
#  Scenario: Ver listado de miembros como un difusor con el perfil Difusor
#    When Ingreso el codigo de area "United States" y el numero de telefono "4234567890"
#    And Ingreso el codigo de OTP "444444"
#    And Ingreso a UPPAZ
#    And Veo el listado de miembros desde un difusor
#    Then Valido se encuentre el miembro de pruebas

  @uppaz
  @uppaz_004
  Scenario: Buscar miembro UPPAZ por nombre con el perfil Jefe de Comunidad UPPAZ
    When Ingreso el codigo de area "United States" y el numero de telefono "2234567890"
    And Ingreso el codigo de OTP "222222"
    And Ingreso a UPPAZ
    And Veo el listado de miembros
    And Busco al miembro de nombre "Jaimito Perez"
    Then Valido que se visualiza el miembro buscado

  @uppaz
  @uppaz_005
  Scenario: Editar un miembro UPPAZ con el perfil Jefe de Comunidad UPPAZ
    When Ingreso el codigo de area "United States" y el numero de telefono "2234567890"
    And Ingreso el codigo de OTP "222222"
    And Ingreso a UPPAZ
    And Veo el listado de miembros
    And Edito el telefono
    Then Valido se edito el telefono
    And Restauro el numero modificado del miembro

  @uppaz
  @uppaz_006
  Scenario: Evitar agregar cedulas repetidas con el perfil Jefe de Comunidad UPPAZ
    When Ingreso el codigo de area "United States" y el numero de telefono "2234567890"
    And Ingreso el codigo de OTP "222222"
    And Ingreso a UPPAZ
    And Consulto la cedula "9876543"
    Then Valido que la cedula ya esta registrada

  @uppaz
  @uppaz_007
  Scenario: Evitar agregar un telefono repetido con el perfil Jefe de Comunidad UPPAZ
    When Ingreso el codigo de area "United States" y el numero de telefono "2234567890"
    And Ingreso el codigo de OTP "222222"
    And Ingreso a UPPAZ
    And Consulto el telefono +58555555
    Then Valido que el numero esta registrado