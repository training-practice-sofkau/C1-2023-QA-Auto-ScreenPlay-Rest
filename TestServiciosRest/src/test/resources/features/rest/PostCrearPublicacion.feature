Feature:Creacion de publicacion
  yo como administrador del servicio JSON Placeholder
  quiero realizar una peticion para crea una publicaciones en Placeholder
  para registrar la nueva publicacion

  @RegisterPublicacion
  Scenario Outline:Registro exitoso
    Given que el usuario esta en la pagina de registro de la api
    When el usuario envia una peticion post con el titulo <titulo> cuerpo <body> y el userId <userId>
    Then el usuario debe ver un codigo <code> de respuesta y el id
    Examples:
      | titulo     | body        | userId | code |
      | "Alimento" | "Vegetales" | 1      | 201  |
     # | "Deporte"  | "natacion"  | 2      | 201  |
     # | "Musica"   | "Pop"       | 3      | 201  |