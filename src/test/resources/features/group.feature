@allure.label.owner:Raul_Leandro
@CC200
Feature: Grupos en la pagina web CC200

  Background:
    Given Navego a la pagina web de CC200

  @Group
  @Group_001
  Scenario: Crear grupo con el perfil <perfil>
    When Ingreso el codigo de area "United States" y el numero de telefono "1234567890"
    And Ingreso el codigo de OTP "111111"
    And Creo el grupo "Crear grupo"
    Then Valido se creo el grupo
    And Elimino el grupo con el numero "1234567890"

#    Examples:
#      | perfil       | numero     | codigo |
#      | Jefe de UBCH | 1234567890 | 111111 |

  @Group
  @Group_002
  Scenario: Envio mensaje grupal con el perfil <perfil>
    When Ingreso el codigo de area "United States" y el numero de telefono "1234567890"
    And Ingreso el codigo de OTP "111111"
    And Creo el grupo "Envio un mensaje"
    And Envio un mensaje al grupo
    Then Valido que se envio el mensaje al grupo
    And Elimino el grupo con el numero "1234567890"

#    Examples:
#      | perfil       | numero     | codigo |
#      | Jefe de UBCH | 1234567890 | 111111 |


  @Group
  @Group_003
  Scenario: Ver informacion de un grupo con el perfil <perfil>
    When Ingreso el codigo de area "United States" y el numero de telefono "1234567890"
    And Ingreso el codigo de OTP "111111"
    And Creo el grupo "Ver informacion del grupo"
    And Veo la informacion del grupo
    Then Valido que puedo ver la informacion del grupo
    And Elimino el grupo con el numero "1234567890"

#    Examples:
#      | perfil       | numero     | codigo |
#      | Jefe de UBCH | 1234567890 | 111111 |


  @Group
  @Group_004
  Scenario: Eliminar un grupo con el perfil <perfil>
    When Ingreso el codigo de area "United States" y el numero de telefono "1234567890"
    And Ingreso el codigo de OTP "111111"
    And Creo el grupo "<chat>"
    And Elimino el grupo
    Then Valido que se elimino el grupo

#    Examples:
#      | perfil                  | numero     | codigo | chat           |
#      | Jefe de UBCH            | 1234567890 | 111111 | Eliminar grupo |
#      | Jefe de Comunidad UPPAZ | 2234567890 | 222222 | Eliminar grupo |
#      | Jefe de Calle           | 3234567890 | 333333 | Eliminar grupo |

  @Group
  @Group_005
  Scenario: Buscar un grupo con el perfil <perfil>
    When Ingreso el codigo de area "United States" y el numero de telefono "1234567890"
    And Ingreso el codigo de OTP "111111"
    And Creo el grupo "Buscar grupo"
    And Busco el grupo "Buscar grupo"
    Then Valido que se visualiza el grupo
    And Elimino el grupo con el numero "1234567890"

#    Examples:
#      | perfil       | numero     | codigo |
#      | Jefe de UBCH | 1234567890 | 111111 |


  @Group
  @Group_006
  Scenario: Ver miembros de un grupo con el perfil <perfil>
    When Ingreso el codigo de area "United States" y el numero de telefono "1234567890"
    And Ingreso el codigo de OTP "111111"
    And Creo el grupo "Ver miembros"
    And Veo los miembros del grupo
    Then Valido abrir miembros del grupo
    And Elimino el grupo con el numero "1234567890"

#    Examples:
#      | perfil       | numero     | codigo |
#      | Jefe de UBCH | 1234567890 | 111111 |


  @Group
  @Group_007
  Scenario: Cambiar nombre a un grupo con el perfil <perfil>
    When Ingreso el codigo de area "United States" y el numero de telefono "1234567890"
    And Ingreso el codigo de OTP "111111"
    And Creo el grupo "Cambiar nombre"
    And Cambio el nombre a "Nuevo nombre"
    Then Valido que se cambio el nombre del grupo
    And Elimino el grupo con el numero "1234567890"

#    Examples:
#      | perfil       | numero     | codigo |
#      | Jefe de UBCH | 1234567890 | 111111 |


  @Group
  @Group_008
  Scenario: Actualizo la foto del grupo con el perfil <perfil>
    When Ingreso el codigo de area "United States" y el numero de telefono "1234567890"
    And Ingreso el codigo de OTP "111111"
    And Creo el grupo "Actualizo la foto"
    And Actualizo la foto del grupo
    Then Valido que se actualizo la fotol del grupo
    And Elimino el grupo con el numero "1234567890"

#    Examples:
#      | perfil       | numero     | codigo |
#      | Jefe de UBCH | 1234567890 | 111111 |


  @Group
  @Group_009
  Scenario: Envio un mensaje a un grupo con el perfil <perfil>
    When Ingreso el codigo de area "United States" y el numero de telefono "1234567890"
    And Ingreso el codigo de OTP "111111"
    And Creo el grupo "Envio un mensaje"
    And Envio un mensaje
    Then Valido que se envio el mensaje
    And Elimino el grupo con el numero "1234567890"

#    Examples:
#      | perfil       | numero     | codigo |
#      | Jefe de UBCH | 1234567890 | 111111 |


  @Group
  @Group_010
  Scenario: Envio un emoji a un grupo con el perfil <perfil>
    When Ingreso el codigo de area "United States" y el numero de telefono "1234567890"
    And Ingreso el codigo de OTP "111111"
    And Creo el grupo "Envio emoji"
    And Envio un emoji
    Then Valido que se envio el emoji
    And Elimino el grupo con el numero "1234567890"

#    Examples:
#      | perfil       | numero     | codigo |
#      | Jefe de UBCH | 1234567890 | 111111 |


  @Group
  @Group_011
  Scenario: Envio un archivo de tipo jpg a un grupo con el perfil <perfil>
    When Ingreso el codigo de area "United States" y el numero de telefono "1234567890"
    And Ingreso el codigo de OTP "111111"
    And Creo el grupo "Envio foto"
    And Envio un archivo de tipo "foto"
    Then Valido que se envio la foto por chat
    And Elimino el grupo con el numero "1234567890"

#    Examples:
#      | perfil       | numero     | codigo |
#      | Jefe de UBCH | 1234567890 | 111111 |


  @Group
  @Group_012
  Scenario: Envio un archivo de tipo pdf a un grupo con el perfil <perfil>
    When Ingreso el codigo de area "United States" y el numero de telefono "1234567890"
    And Ingreso el codigo de OTP "111111"
    And Creo el grupo "Envio documento"
    And Envio un archivo de tipo "pdf"
    Then Valido que se envio un documento por chat
    And Elimino el grupo con el numero "1234567890"

#    Examples:
#      | perfil       | numero     | codigo |
#      | Jefe de UBCH | 1234567890 | 111111 |


  @Group
  @Group_013
  Scenario: Eliminar participante de un grupo con el perfil <perfil>
    When Ingreso el codigo de area "United States" y el numero de telefono "1234567890"
    And Ingreso el codigo de OTP "111111"
    And Creo el grupo "Elimino un participante"
    And Elimino un participante
    Then Valido que se elimino el participante
    And Elimino el grupo con el numero "1234567890"

#    Examples:
#      | perfil       | numero     | codigo |
#      | Jefe de UBCH | 1234567890 | 111111 |


  @Group
  @Group_014
  Scenario: Crear grupo sin nombre con el perfil <perfil>
    When Ingreso el codigo de area "United States" y el numero de telefono "1234567890"
    And Ingreso el codigo de OTP "111111"
    And Creo un grupo sin nombre
    Then Valido que se visualiza el mensaje "El nombre debe tener al menos 6 caracteres"

#    Examples:
#      | perfil       | numero     | codigo |
#      | Jefe de UBCH | 1234567890 | 111111 |


  @Group
  @Group_015
  Scenario: Crear grupo sin participantes con el perfil <perfil>
    When Ingreso el codigo de area "United States" y el numero de telefono "1234567890"
    And Ingreso el codigo de OTP "111111"
    And Creo un grupo sin participantes
    Then Valido que no puedo crear el grupo

#    Examples:
#      | perfil       | numero     | codigo |
#      | Jefe de UBCH | 1234567890 | 111111 |
