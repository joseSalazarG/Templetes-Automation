@allure.label.owner:Raul_Leandro
@CC200
Feature: Configuración en la pagina web CC200

  Background:
  Given Navego a la pagina web de CC200

  @Settings
  @Settings_001
  Scenario Outline: Actualizar el nombre de perfil del <perfil>
    When Ingreso el codigo de area "United States" y el numero de telefono "<numero>"
    And Ingreso el codigo de OTP "<codigo>"
    And Ingreso a configuracion
    And Actualizo el nombre de perfil "<nombre>" a "Nuevo Nombre"
    Then Valido que se cambio el nombre
    And Actualizo el nombre de perfil "Nuevo Nombre" a "<nombre>"
    Then Valido que se cambio el nombre

    Examples:
      | perfil                  | numero     | codigo | nombre                  |
      | Jefe de UBCH            | 1234567890 | 111111 | TestAuto Jefe UBCH      |
#      | Jefe de Comunidad UPPAZ | 2234567890 | 222222 | TestAuto Jefe Comunidad |
#      | Jefe de Calle           | 3234567890 | 333333 | TestAuto Jefe Calle     |

  @Settings
  @Settings_002
  Scenario Outline: Actualizar el nombre de perfil del <perfil> con numeros
    When Ingreso el codigo de area "United States" y el numero de telefono "<numero>"
    And Ingreso el codigo de OTP "<codigo>"
    And Ingreso a configuracion
    And Actualizo el nombre de perfil con numeros "012345"
    Then Valido que no se pueda cambiar el nombre

    Examples:
      | perfil                  | numero     | codigo |
      | Jefe de UBCH            | 1234567890 | 111111 |
#      | Jefe de Comunidad UPPAZ | 2234567890 | 222222 |
#      | Jefe de Calle           | 3234567890 | 333333 |

  @Settings
  @Settings_003
  Scenario Outline: Actualizar el email de perfil del <perfil>
    When Ingreso el codigo de area "United States" y el numero de telefono "<numero>"
    And Ingreso el codigo de OTP "<codigo>"
    And Ingreso a configuracion
    And Actualizo el email de perfil a "<email nuevo>"
    Then Valido que se cambio el email
    And Actualizo el email de perfil a "<email antiguo>"
    Then Valido que se cambio el email

    Examples:
      | perfil                  | numero     | codigo | email nuevo             | email antiguo       |
      | Jefe de UBCH            | 1234567890 | 111111 | autotestubch@gmail.com  | cc200test@gmail.com |
#      | Jefe de Comunidad UPPAZ | 2234567890 | 222222 | autotestuppaz@gmail.com | cc202test@gmail.com |
#      | Jefe de Calle           | 3234567890 | 333333 | autotestcalle@gmail.com | cc203test@gmail.com |

  @Settings
  @Settings_004
  Scenario Outline: Actualizar el email con uno no valido para el perfil del <perfil>
    When Ingreso el codigo de area "United States" y el numero de telefono "<numero>"
    And Ingreso el codigo de OTP "<codigo>"
    And Ingreso a configuracion
    And Actualizo el email con uno no valido "loremipsum"
    Then Valido que no se pueda cambiar el email

    Examples:
      | perfil                  | numero     | codigo |
      | Jefe de UBCH            | 1234567890 | 111111 |
#      | Jefe de Comunidad UPPAZ | 2234567890 | 222222 |
#      | Jefe de Calle           | 3234567890 | 333333 |

  @Settings
  @Settings_005
  Scenario Outline: Actualizar el email con uno existente el perfil del <perfil>
    When Ingreso el codigo de area "United States" y el numero de telefono "<numero>"
    And Ingreso el codigo de OTP "<codigo>"
    And Ingreso a configuracion
    And Actualizo el email de perfil a "<email>"
    Then Valido que el email ya existe

    Examples:
      | perfil                  | numero     | codigo | email               |
      | Jefe de UBCH            | 1234567890 | 111111 | cc202test@gmail.com |
#      | Jefe de Comunidad UPPAZ | 2234567890 | 222222 | cc203test@gmail.com |
#      | Jefe de Calle           | 3234567890 | 333333 | cc202test@gmail.com |

  @Settings
  @Settings_006
  Scenario Outline: Actualizar la foto de perfil del <perfil>
    When Ingreso el codigo de area "United States" y el numero de telefono "<numero>"
    And Ingreso el codigo de OTP "<codigo>"
    And Ingreso a configuracion
    And Actualizo la foto de perfil
    Then Valido que se cambio la foto de perfil

    Examples:
      | perfil                  | numero     | codigo |
      | Jefe de UBCH            | 1234567890 | 111111 |
#      | Jefe de Comunidad UPPAZ | 2234567890 | 222222 |
#      | Jefe de Calle           | 3234567890 | 333333 |