Feature: Yo como usuario de la Poke API
  Quiero poder listar la informacion de
  los pokemones de generacion 1

  @ListPokemons
 Scenario: Listar informacion de manera exitosa
   Given Que estoy en la Poke API
   When Hago una peticion para listar la info de los pokemones
   Then Debo ver un codigo de estado exitoso
   And La respuesta debe contener un body con toda la info