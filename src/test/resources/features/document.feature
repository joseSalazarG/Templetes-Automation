@allure.label.owner:Raul_Leandro
@CC200
Feature: Documentos en la pagina web CC200

  Background:
    Given Navego a la pagina web de CC200
    And Ingreso el codigo de area "United States" y el numero de telefono "1234567890"
    And Ingreso el codigo de OTP "111111"
    And Ingreso a documentos

  @Documents
  @Documents_001
  Scenario: Ver listado de documentos en menu
    Then Valido que puedo ver los documentos en menu

  @Documents
  @Documents_002
  Scenario: Ver listado de documentos
    When Abro documentos
    Then Valido ver el listado de documentos


  @Documents
  @Documents_003
  Scenario: Ver listado de audios
    When Abro audios
    Then Valido ver el listado de audios

  @Documents
  @Documents_004
  Scenario: Ver listado de videos
    When Abro videos
    Then Valido ver el listado de videos

  @Documents
  @Documents_005
  Scenario: Ver listado de imagenes
    When Abro imagenes
    Then Valido ver el listado de imagenes

  @Documents
  @Documents_006
  Scenario: Ver listado de otros documentos
    When Abro otros documentos
    Then Valido ver el listado de otros documentos

  @Documents
  @Documents_007
  Scenario: Abrir un documento
    When Abro documentos
    Then Valido abrir un documento

  @Documents
  @Documents_008
  Scenario: Abrir un audio
    When Abro audios
    Then Valido abrir un audio

  @Documents
  @Documents_009
  Scenario: Abrir un video
    When Abro videos
    Then Valido abrir un video

  @Documents
  @Documents_010
  Scenario: Abrir una imagen
    When Abro imagenes
    Then Valido abrir una imagen

  @Documents
  @Documents_011
  Scenario: Abrir otros documentos
    When Abro otros documentos
    Then Valido abrir otros documentos