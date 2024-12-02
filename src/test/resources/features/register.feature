@allure.label.owner:Raul_Leandro
@CC200
Feature: Registro 1x10 en la pagina web CC200

  Background:
    Given Navego a la pagina web de CC200

  @Register
  @Register_001
  Scenario Outline: Ingresar a registro 1x10 con el perfil <perfil>
    When Ingreso el codigo de area "United States" y el numero de telefono "<numero>"
    And Ingreso el codigo de OTP "<codigo>"
    And Ingreso a registro 1x10
    Then Valido que estoy en el registro

    Examples:
      | perfil                  | numero     | codigo |
      | Jefe de UBCH            | 1234567890 | 111111 |
#      | Jefe de Comunidad UPPAZ | 2234567890 | 222222 |
#      | Jefe de Calle           | 3234567890 | 333333 |

#  @Register
#  @Register_002
#  Scenario Outline: Registrar una cedula con el perfil <perfil>
#    When Ingreso el codigo de area "United States" y el numero de telefono "<numero>"
#    And Ingreso el codigo de OTP "<codigo>"
#    And Ingreso a registro 1x10
#    And Registro la cedula "28031558"
#    And Registro un telefono "656565656"
#    Then Valido que se vea el mensaje "Miembro agregado con éxito."
#    And Elimino la cedula "28031558"
#
#    Examples:
#      | perfil                  | numero     | codigo |
#      | Jefe de UBCH            | 1234567890 | 111111 |
#      | Jefe de Comunidad UPPAZ | 2234567890 | 222222 |
#      | Jefe de Calle           | 3234567890 | 333333 |
#
#  @Register
#  @Register_003
#  Scenario Outline: Eliminar una cedula con el perfil <perfil>
#    When Ingreso el codigo de area "United States" y el numero de telefono "<numero>"
#    And Ingreso el codigo de OTP "<codigo>"
#    And Ingreso a registro 1x10
#    And Registro la cedula "28031558"
#    And Registro un telefono "656565656"
#    And Elimino la cedula "28031558"
#    Then Valido que se vea el mensaje "Miembro eliminado"
#
#    Examples:
#      | perfil                  | numero     | codigo |
#      | Jefe de UBCH            | 1234567890 | 111111 |
#      | Jefe de Comunidad UPPAZ | 2234567890 | 222222 |
#      | Jefe de Calle           | 3234567890 | 333333 |

  @Register
  @Register_004
  Scenario Outline: Registrar una cedula existente con el perfil <perfil>
    When Ingreso el codigo de area "United States" y el numero de telefono "<numero>"
    And Ingreso el codigo de OTP "<codigo>"
    And Ingreso a registro 1x10
    And Registro la cedula "25696949"
    Then Valido que se vea el mensaje "Cédula ya registrada"

    Examples:
      | perfil                  | numero     | codigo |
      | Jefe de UBCH            | 1234567890 | 111111 |
#      | Jefe de Comunidad UPPAZ | 2234567890 | 222222 |
#      | Jefe de Calle           | 3234567890 | 333333 |

  @Register
  @Register_005
  Scenario Outline: Registrar un telefono existente con el perfil <perfil>
    When Ingreso el codigo de area "United States" y el numero de telefono "<numero>"
    And Ingreso el codigo de OTP "<codigo>"
    And Ingreso a registro 1x10
    And Registro la cedula "20036259"
    And Registro un telefono "45645654654"
    Then Valido que se vea el mensaje "Telefono ya agregado"

    Examples:
      | perfil                  | numero     | codigo |
      | Jefe de UBCH            | 1234567890 | 111111 |
#      | Jefe de Comunidad UPPAZ | 2234567890 | 222222 |
#      | Jefe de Calle           | 3234567890 | 333333 |

  @Register
  @Register_006
  Scenario Outline: Registrar un telefono sin ingresarlo con el perfil <perfil>
    When Ingreso el codigo de area "United States" y el numero de telefono "<numero>"
    And Ingreso el codigo de OTP "<codigo>"
    And Ingreso a registro 1x10
    And Registro un telefono sin ingresarlo
    Then Valido que se vea el mensaje "Número inválido"

    Examples:
      | perfil                  | numero     | codigo |
      | Jefe de UBCH            | 1234567890 | 111111 |
#      | Jefe de Comunidad UPPAZ | 2234567890 | 222222 |
#      | Jefe de Calle           | 3234567890 | 333333 |

  @Register
  @Register_007
  Scenario Outline: Registrar una cedula sin ingresar el tipo de documento  y dni con el perfil <perfil>
    When Ingreso el codigo de area "United States" y el numero de telefono "<numero>"
    And Ingreso el codigo de OTP "<codigo>"
    And Ingreso a registro 1x10
    And Registro la cedula sin ingresar el tipo de documento y dni
    Then Valido que se vea el mensaje "Campo requerido"
    And Valido que se vea el mensaje "Introduzca una cédula válida"

    Examples:
      | perfil                  | numero     | codigo |
      | Jefe de UBCH            | 1234567890 | 111111 |
#      | Jefe de Comunidad UPPAZ | 2234567890 | 222222 |
#      | Jefe de Calle           | 3234567890 | 333333 |

  @Register
  @Register_008
  Scenario Outline: Registrar una cedula que no pertenece al estado con el perfil <perfil>
    When Ingreso el codigo de area "United States" y el numero de telefono "<numero>"
    And Ingreso el codigo de OTP "<codigo>"
    And Ingreso a registro 1x10
    And Registro la cedula "00000087"
    Then Valido que se vea el mensaje "Esta persona no pertenece a tu Estado"

    Examples:
      | perfil                  | numero     | codigo |
      | Jefe de UBCH            | 1234567890 | 111111 |
#      | Jefe de Comunidad UPPAZ | 2234567890 | 222222 |
#      | Jefe de Calle           | 3234567890 | 333333 |

#  @Register
#  @Register_009
#  Scenario: Limite de registros alcanzados con el perfil <perfil>
#    When Ingreso el codigo de area "United States" y el numero de telefono "1234567890"
#    And Ingreso el codigo de OTP "111111"
#    And Ingreso a registro 1x10
#    And Alcanzo el limite de registro
#    Then Valido que se vea el mensaje "Limite de registros alcanzado"
#    And Elimino la cedula "20036259"

  @Register
  @Register_010
  Scenario: Descargo el archivo pdf con el perfil <perfil>
    When Ingreso el codigo de area "United States" y el numero de telefono "1234567890"
    And Ingreso el codigo de OTP "111111"
    And Ingreso a registro 1x10
    And Descargo el pdf
    Then Valido que se visualiza el archivo pdf
