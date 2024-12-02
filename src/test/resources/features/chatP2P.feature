@allure.label.owner:Raul_Leandro
@CC200
Feature: Chat P2P en la pagina web CC200

  Background:
    Given Navego a la pagina web de CC200

  @Chat
  @Chat_001
  Scenario Outline: Envio un mensaje por chat con el perfil <perfil>
    When Ingreso el codigo de area "United States" y el numero de telefono "<numero>"
    And Ingreso el codigo de OTP "<codigo>"
    And Busco el chat "Difusor"
    And Envio un mensaje
    Then Valido que se envio el mensaje

    Examples:
      | perfil                  | numero     | codigo |
      | Jefe de UBCH            | 1234567890 | 111111 |
      | Jefe de Comunidad UPPAZ | 2234567890 | 222222 |
      | Jefe de Calle           | 3234567890 | 333333 |

  @Chat
  @Chat_002
  Scenario Outline: Envio un emoji por chat con el perfil <perfil>
    When Ingreso el codigo de area "United States" y el numero de telefono "<numero>"
    And Ingreso el codigo de OTP "<codigo>"
    And Busco el chat "Difusor"
    And Envio un emoji
    Then Valido que se envio el emoji

    Examples:
      | perfil                  | numero     | codigo |
      | Jefe de UBCH            | 1234567890 | 111111 |
      | Jefe de Comunidad UPPAZ | 2234567890 | 222222 |
      | Jefe de Calle           | 3234567890 | 333333 |

  @Chat
  @Chat_003
  Scenario Outline: Envio un archivo de tipo pdf por chat con el perfil <perfil>
    When Ingreso el codigo de area "United States" y el numero de telefono "<numero>"
    And Ingreso el codigo de OTP "<codigo>"
    And Busco el chat "Difusor"
    And Envio un archivo de tipo "pdf"
    Then Valido que se envio un documento por chat

    Examples:
      | perfil                  | numero     | codigo |
      | Jefe de UBCH            | 1234567890 | 111111 |
      | Jefe de Comunidad UPPAZ | 2234567890 | 222222 |
      | Jefe de Calle           | 3234567890 | 333333 |

  @Chat
  @Chat_004
  Scenario Outline: Envio un archivo de tipo jpg por chat con el perfil <perfil>
    When Ingreso el codigo de area "United States" y el numero de telefono "<numero>"
    And Ingreso el codigo de OTP "<codigo>"
    And Busco el chat "Difusor"
    And Envio un archivo de tipo "foto"
    Then Valido que se envio la foto por chat

    Examples:
      | perfil                  | numero     | codigo |
      | Jefe de UBCH            | 1234567890 | 111111 |
      | Jefe de Comunidad UPPAZ | 2234567890 | 222222 |
      | Jefe de Calle           | 3234567890 | 333333 |

  @Chat
  @Chat_005
  Scenario Outline: Elimino un chat con el perfil <perfil>
    When Ingreso el codigo de area "United States" y el numero de telefono "<numero>"
    And Ingreso el codigo de OTP "<codigo>"
    And Busco el chat "José Salazar"
    And Elimino el chat
    Then Valido que se elimino el chat

    Examples:
      | perfil                  | numero     | codigo | chat           |
      | Jefe de UBCH            | 1234567890 | 111111 | Eliminar grupo |
      | Jefe de Comunidad UPPAZ | 2234567890 | 222222 | Eliminar grupo |
      | Jefe de Calle           | 3234567890 | 333333 | Eliminar grupo |

  @Chat
  @Chat_006
  Scenario Outline: Busco un chat con el perfil <perfil>
    When Ingreso el codigo de area "United States" y el numero de telefono "<numero>"
    And Ingreso el codigo de OTP "<codigo>"
    And Busco el chat "Difusor"
    Then Valido que se visualiza el chat

    Examples:
      | perfil                  | numero     | codigo |
      | Jefe de UBCH            | 1234567890 | 111111 |
      | Jefe de Comunidad UPPAZ | 2234567890 | 222222 |
      | Jefe de Calle           | 3234567890 | 333333 |

  @Chat
  @Chat_007
  Scenario Outline: Actualizo el tema de un chat con el perfil <perfil>
    When Ingreso el codigo de area "United States" y el numero de telefono "<numero>"
    And Ingreso el codigo de OTP "<codigo>"
    And Busco el chat "<chat>"
    And Actualizo el tema del chat
    Then Valido que se actualizo el tema del chat

    Examples:
      | perfil                  | numero     | codigo | chat        |
      | Jefe de UBCH            | 1234567890 | 111111 | Difusor     |
      | Jefe de Comunidad UPPAZ | 2234567890 | 222222 | Difusor     |
      | Jefe de Calle           | 3234567890 | 333333 | Jose Guzman |

  @Chat
  @Chat_008
  Scenario Outline: Ver información de un contacto (desde chat P2P) con el perfil <perfil>
    When Ingreso el codigo de area "United States" y el numero de telefono "<numero>"
    And Ingreso el codigo de OTP "<codigo>"
    And Busco el chat "Marco Aurelio"
    Then Valido abrir informacion del chat

    Examples:
      | perfil                  | numero     | codigo |
      | Jefe de UBCH            | 1234567890 | 111111 |
      | Jefe de Comunidad UPPAZ | 2234567890 | 222222 |
      | Jefe de Calle           | 3234567890 | 333333 |

  @Chat
  @Chat_009
  Scenario Outline: Actualizo el alias de un chat con el perfil <perfil>
    When Ingreso el codigo de area "United States" y el numero de telefono "<numero>"
    And Ingreso el codigo de OTP "<codigo>"
    And Busco el chat "<chat>"
    And Cambio el alias a "Difusor nuevo"
    Then Valido que se visualiza el nuevo alias
    And Cambio el alias a "<chat>"

    Examples:
      | perfil                  | numero     | codigo | chat    |
      | Jefe de UBCH            | 1234567890 | 111111 | Difusor |
      | Jefe de Comunidad UPPAZ | 2234567890 | 222222 | Difusor |
      | Jefe de Calle           | 3234567890 | 333333 | Difusor |