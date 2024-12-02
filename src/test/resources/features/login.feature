@allure.label.owner:Raul_Leandro
@CC200
Feature: Login en la pagina web CC200

  Background:
    Given Navego a la pagina web de CC200

  @Login
  @Login_001
  Scenario Outline: Iniciar sesion de forma exitosa con el perfil <perfil>
    When Ingreso el codigo de area "United States" y el numero de telefono "<numero>"
    And Ingreso el codigo de OTP "<codigo>"
    Then Valido que me logueo de forma exitosa

    Examples:
      | perfil                  | numero     | codigo |
      | Jefe de UBCH            | 1234567890 | 111111 |
      | Jefe de Comunidad UPPAZ | 2234567890 | 222222 |
      | Jefe de Calle           | 3234567890 | 333333 |

  @Login
  @Login_002
  Scenario: Iniciar sesion con un numero de telefono que no esta registrado
    When Ingreso el codigo de area "United States" y el numero de telefono "1234567822"
    Then Valido que se visualice el mensaje de error "Este número de teléfono no está registrado como usuario"

#  Se comenta porque el login ahora no arroja mensaje de error simplemente no hay accion si el numero esta vacio
# Raul Leandro 27/08/2024
#  @Login
#  @Login_003
#  Scenario: Iniciar sesion sin ingresar un numero de telefono
#    When Ingreso el codigo de area "United States" y el numero de telefono ""
#    Then Valido que se visualice el mensaje de error "Introduzca un número de teléfono y seleccione su país para continuar"

  @Login
  @Login_004
  Scenario: Iniciar sesion con un numero de telefono incompleto
    When Ingreso el codigo de area "United States" y el numero de telefono "12345"
    Then Valido que se visualice el mensaje de error "Seleccione su país e introduzca un número de teléfono válido para continuar"

  @Login
  @Login_005
  Scenario: Iniciar sesion con un OTP incorrecto
    When Ingreso el codigo de area "United States" y el numero de telefono "1234567890"
    And Ingreso el codigo de OTP "123456"
    Then Valido que se visualice el mensaje de error de OTP "Código SMS inválido"

  @Login
  @Login_006
  Scenario: Iniciar sesion sin ingresar el OTP
    When Ingreso el codigo de area "United States" y el numero de telefono "1234567890"
    And Ingreso el codigo de OTP ""
    Then Valido que se visualice el mensaje de error de OTP "Ingrese el código enviado a su teléfono celular"

  @Login
  @Login_007
  Scenario: Iniciar sesion con el OTP incompleto
    When Ingreso el codigo de area "United States" y el numero de telefono "1234567890"
    And Ingreso el codigo de OTP "123"
    Then Valido que se visualice el mensaje de error de OTP "Código SMS inválido"

  @Login
  @Login_008
  Scenario Outline: Cerrar sesion con el usuario <perfil>
    When Ingreso el codigo de area "United States" y el numero de telefono "<numero>"
    And Ingreso el codigo de OTP "<codigo>"
    And Cierro la sesion
    Then Valido que se cerro la sesion

    Examples:
      | perfil                  | numero     | codigo |
      | Jefe de UBCH            | 1234567890 | 111111 |
      | Jefe de Comunidad UPPAZ | 2234567890 | 222222 |
      | Jefe de Calle           | 3234567890 | 333333 |