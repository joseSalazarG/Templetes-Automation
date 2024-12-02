#@CC200
Feature: Estructura base en la pagina web CC200

  Background:
    Given Navego a la pagina web de CC200

  @BaseStructure
  @BaseStructure_001
  Scenario Outline: Agregar un miembro con el perfil <perfil>
    When Ingreso el codigo de area "United States" y el numero de telefono "<numero>"
    And Ingreso el codigo de OTP "<codigo>"
    And Agrego un miembro desde agregar miembro con la responsabilidad de "<responsabilidad>"
    Then Valido que agregue el miembro con estado "<estado>"
    And Elimino el miembro

    Examples:
      | perfil                  | numero     | codigo | estado    | responsabilidad |
      | Jefe de UBCH            | 1234567890 | 111111 | ACEPTADO  | JEFE DE UPPAZ   |
      | Jefe de Comunidad UPPAZ | 2234567890 | 222222 | DECLINADO | JEFE DE CALLE   |

  @BaseStructure
  @BaseStructure_002
  Scenario Outline: Ver listado de miembros con el perfil <perfil>
    When Ingreso el codigo de area "United States" y el numero de telefono "<numero>"
    And Ingreso el codigo de OTP "<codigo>"
    And Ingreso a listado de miembros
    Then Valido que puedo ver el listado de miembros

    Examples:
      | perfil                  | numero     | codigo |
      | Jefe de UBCH            | 1234567890 | 111111 |
      | Jefe de Comunidad UPPAZ | 2234567890 | 222222 |

  @BaseStructure
  @BaseStructure_003
  Scenario Outline: Filtrar listado de miembros por estado con el perfil <perfil>
    When Ingreso el codigo de area "United States" y el numero de telefono "<numero>"
    And Ingreso el codigo de OTP "<codigo>"
    And Ingreso a listado de miembros
    Then Valido que se filtro por estado

    Examples:
      | perfil                  | numero     |  | codigo |
      | Jefe de UBCH            | 1234567890 |  | 111111 |
      | Jefe de Comunidad UPPAZ | 2234567890 |  | 222222 |

  @BaseStructure
  @BaseStructure_004
  Scenario Outline: Ver detalle de un miembro con el perfil <perfil>
    When Ingreso el codigo de area "United States" y el numero de telefono "<numero>"
    And Ingreso el codigo de OTP "<codigo>"
    And Ingreso a listado de miembros
    And Ingreso al detalle de un miembro
    Then Valido que se visualizan los detalles del miembro

    Examples:
      | perfil                  | numero     |  | codigo |
      | Jefe de UBCH            | 1234567890 |  | 111111 |
      | Jefe de Comunidad UPPAZ | 2234567890 |  | 222222 |

  @BaseStructure
  @BaseStructure_005
  Scenario Outline: Buscar miembro con el perfil <perfil>
    When Ingreso el codigo de area "United States" y el numero de telefono "<numero>"
    And Ingreso el codigo de OTP "<codigo>"
    And Ingreso a listado de miembros
    Then Valido que puedo buscar un miembro

    Examples:
      | perfil                  | numero     |  | codigo |
      | Jefe de UBCH            | 1234567890 |  | 111111 |
      | Jefe de Comunidad UPPAZ | 2234567890 |  | 222222 |

  @BaseStructure
  @BaseStructure_006
  Scenario Outline: Editar miembro con el perfil <perfil>
    When Ingreso el codigo de area "United States" y el numero de telefono "<numero>"
    And Ingreso el codigo de OTP "<codigo>"
    And Agrego un miembro desde agregar miembro con la responsabilidad de "<responsabilidad>"
    And Edito un miembro desde listado de miembros
    Then Valido que puedo editar el miembro
    And Elimino el miembro

    Examples:
      | perfil                  | numero     |  | codigo | responsabilidad |
      | Jefe de UBCH            | 1234567890 |  | 111111 | JEFE DE UPPAZ   |
      | Jefe de Comunidad UPPAZ | 2234567890 |  | 222222 | JEFE DE CALLE   |

  @BaseStructure
  @BaseStructure_007
  Scenario Outline: Eliminar miembro con el perfil <perfil>
    When Ingreso el codigo de area "United States" y el numero de telefono "<numero>"
    And Ingreso el codigo de OTP "<codigo>"
    And Agrego un miembro desde agregar miembro con la responsabilidad de "<responsabilidad>"
    And Busco un miembro para eliminar
    And Elimino el miembro
    Then Valido que puedo eliminar un miembro desde estructura base

    Examples:
      | perfil                  | numero     |  | codigo | responsabilidad |
      | Jefe de UBCH            | 1234567890 |  | 111111 | JEFE DE UPPAZ   |
      | Jefe de Comunidad UPPAZ | 2234567890 |  | 222222 | JEFE DE CALLE   |

  @BaseStructure
  @BaseStructure_008
  Scenario Outline: Registro un equipo de trabajo con el perfil <perfil>
    When Ingreso el codigo de area "United States" y el numero de telefono "<numero>"
    And Ingreso el codigo de OTP "<codigo>"
    And Agrego un miembro desde agregar miembro con la responsabilidad de "<responsabilidad>"
    Then Valido que se agrego el miembro a equipo de trabajo
    And Eliminar un miembro desde equipo de trabajo

    Examples:
      | perfil                  | numero     | codigo | responsabilidad        |
      | Jefe de UBCH            | 1234567890 | 111111 | FORMACIÓN              |
      | Jefe de Comunidad UPPAZ | 2234567890 | 222222 | ELECTORAL Y APC        |
      | Jefe de Calle           | 3234567890 | 333333 | MOVILIZACIÓN Y EVENTOS |

  @BaseStructure
  @BaseStructure_009
  Scenario Outline: Ver detalle de un miembro en equipo de trabajo con el perfil <perfil>
    When Ingreso el codigo de area "United States" y el numero de telefono "<numero>"
    And Ingreso el codigo de OTP "<codigo>"
    And Agrego un miembro desde agregar miembro con la responsabilidad de "<responsabilidad>"
    Then Valido que puedo ver los detalles de un miembro desde equipo de trabajo
    And Eliminar un miembro desde equipo de trabajo

    Examples:
      | perfil                  | numero     | codigo | responsabilidad        |
      | Jefe de UBCH            | 1234567890 | 111111 | FORMACIÓN              |
      | Jefe de Comunidad UPPAZ | 2234567890 | 222222 | ELECTORAL Y APC        |
      | Jefe de Calle           | 3234567890 | 333333 | MOVILIZACIÓN Y EVENTOS |

  @BaseStructure
    @BaseStructure_010
  Scenario Outline: Ver listado de miembros equipo de trabajo con el perfil <perfil>
    When Ingreso el codigo de area "United States" y el numero de telefono "<numero>"
    And Ingreso el codigo de OTP "<codigo>"
    And Ingreso a listado de miembros de equipo de trabajo
    Then Valido que puedo ver el listado de miembros de equipo trabajo

    Examples:
      | perfil                  | numero     | codigo |
      | Jefe de UBCH            | 1234567890 | 111111 |
      | Jefe de Comunidad UPPAZ | 2234567890 | 222222 |
      | Jefe de Calle           | 3234567890 | 333333 |

  @BaseStructure
  @BaseStructure_011
  Scenario Outline: Buscar miembro de equipo de trabajo con el perfil <perfil>
    When Ingreso el codigo de area "United States" y el numero de telefono "<numero>"
    And Ingreso el codigo de OTP "<codigo>"
    And Agrego un miembro desde agregar miembro con la responsabilidad de "<responsabilidad>"
    Then Valido que puedo buscar el miembro por nombre
    And Eliminar un miembro desde equipo de trabajo

    Examples:
      | perfil                  | numero     | codigo | responsabilidad        |
      | Jefe de UBCH            | 1234567890 | 111111 | FORMACIÓN              |
      | Jefe de Comunidad UPPAZ | 2234567890 | 222222 | ELECTORAL Y APC        |
      | Jefe de Calle           | 3234567890 | 333333 | MOVILIZACIÓN Y EVENTOS |

  @BaseStructure
    @BaseStructure_012
  Scenario Outline: Editar un miembro en equipo de trabajo con el perfil <perfil>
    When Ingreso el codigo de area "United States" y el numero de telefono "<numero>"
    And Ingreso el codigo de OTP "<codigo>"
    And Agrego un miembro desde agregar miembro con la responsabilidad de "<responsabilidad>"
    And Edito un miembro desde equipo de trabajo
    Then Valido que puedo editar el miembro desde equipo de trabajo
    And Eliminar un miembro desde equipo de trabajo

    Examples:
      | perfil                  | numero     | codigo | responsabilidad        |
      | Jefe de UBCH            | 1234567890 | 111111 | FORMACIÓN              |
      | Jefe de Comunidad UPPAZ | 2234567890 | 222222 | ELECTORAL Y APC        |
      | Jefe de Calle           | 3234567890 | 333333 | MOVILIZACIÓN Y EVENTOS |

  @BaseStructure
    @BaseStructure_013
  Scenario Outline: Eliminar un miembro en equipo de trabajo con el perfil <perfil>
    When Ingreso el codigo de area "United States" y el numero de telefono "<numero>"
    And Ingreso el codigo de OTP "<codigo>"
    And Agrego un miembro desde agregar miembro con la responsabilidad de "<responsabilidad>"
    And Valido que se agrego el miembro a equipo de trabajo
    And Eliminar un miembro desde equipo de trabajo
    Then Valido que elimine un miembro desde equipo de trabajo

    Examples:
      | perfil                  | numero     | codigo | responsabilidad        |
      | Jefe de UBCH            | 1234567890 | 111111 | FORMACIÓN              |
      | Jefe de Comunidad UPPAZ | 2234567890 | 222222 | ELECTORAL Y APC        |
      | Jefe de Calle           | 3234567890 | 333333 | MOVILIZACIÓN Y EVENTOS |