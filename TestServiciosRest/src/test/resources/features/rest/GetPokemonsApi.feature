Feature: Nombre de pokemones
  Yo como usuario de pokeapi
  quiero saber la lista de pokemones disponibles en el sitio
  para poder saber cuales estan registrados

  Scenario Outline: Pokemon registrado
    Given que el usuario necesita consultar que pokemons que estan registrados, se hace la peticion
    When se valida que el codigo de respuesta sea exitoso
    Then se validara que en los datos de retorno se encuentre el pokemon '<pokemon>'

    Examples:
      | pokemon   |
      | bulbasaur |
      | ivysaur   |

  Scenario: Recurso no encontrado
    Given que el usuario realiza una peticion no valida
    Then el codigo de respuesta debe ser el de no encontrado

