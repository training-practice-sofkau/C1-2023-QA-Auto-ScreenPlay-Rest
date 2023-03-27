Feature: Eliminacion de albums
  Yo como usuario
  quiero eliminar un album de la plataforma jsonHolder
  Para borrar informacion que no es deseada en la plataforma.

  Scenario Outline: Eliminacion de un album exitosa
    Given el usuario esta en la pagina de jsonplaceholder
    When el usuario realiza la peticion para eliminar un album con el id interno <id>
    Then el usuario obtendra un <codigo> de estado exitoso

    Examples:
      | codigo | id |
      | 200    | 1  |
      | 200    | 2  |
      | 200    | 4  |

