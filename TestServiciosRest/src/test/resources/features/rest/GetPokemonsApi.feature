Feature: Nombre de pokemones
  Yo como usuario de pokeapi
  quiero saber la lista de pokemones disponibles en el sitio
  para poder saber cuales estan registrados

  Scenario Outline: Pokemon registrado
    Given el usuario esta en la PokeApi
    When el usuario hace la peticion con "<pokemon>"
    Then se valida que el <codigo> de respuesta sea correcto y que el "<pokemon>" y el <id> sean correcto

    Examples:
      | codigo | pokemon    | id |
      | 200    | bulbasaur  | 1  |
      | 200    | ivysaur    | 2  |
      | 200    | charmander | 4  |

