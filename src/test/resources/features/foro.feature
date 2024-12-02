@allure.label.owner:Raul_Leandro
@CC200
Feature: Foro de opinion en la pagina web CC200

  Background:
    Given Navego a la pagina web de CC200

  @Foro
    @Foro_001
  Scenario Outline: Ingresar a foro de opinion con el perfil <perfil>
    When Ingreso el codigo de area "United States" y el numero de telefono "<numero>"
    And Ingreso el codigo de OTP "<codigo>"
    And Ingreso al foro de opinion
    Then Valido que estoy en el foro de opinion

    Examples:
      | perfil                  | numero     | codigo |
      | Jefe de UBCH            | 1234567890 | 111111 |
      | Jefe de Comunidad UPPAZ | 2234567890 | 222222 |
      | Jefe de Calle           | 3234567890 | 333333 |

  @Foro
    @Foro_002
  Scenario Outline: Filtro del foro de opinion con el perfil <perfil>
    When Ingreso el codigo de area "United States" y el numero de telefono "<numero>"
    And Ingreso el codigo de OTP "<codigo>"
    And Ingreso al foro de opinion
    And Filtro por "<filtro>"
    Then Valido que se aplico el filtro

    Examples:
      | perfil                  | numero     | codigo | filtro  |
      | Jefe de UBCH            | 1234567890 | 111111 | ciencia |
      | Jefe de Comunidad UPPAZ | 2234567890 | 222222 | ciencia |
      | Jefe de Calle           | 3234567890 | 333333 | ciencia |