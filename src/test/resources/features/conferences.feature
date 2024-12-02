@allure.label.owner:Raul_Leandro
@CC200
Feature: Conferencias en la pagina web CC200

  Background:
    Given Navego a la pagina web de CC200

  @Conferences
  @Conferences_001
  Scenario Outline: Ingresar a conferencia con el perfil <perfil>
    When Ingreso el codigo de area "United States" y el numero de telefono "<numero>"
    And Ingreso el codigo de OTP "<codigo>"
    And Ingreso a conferencias
    Then Valido que estoy en la conferencia

    Examples:
      | perfil                  | numero     | codigo |
      | Jefe de UBCH            | 1234567890 | 111111 |
      | Jefe de Comunidad UPPAZ | 2234567890 | 222222 |
      | Jefe de Calle           | 3234567890 | 333333 |
