Feature:consulta de publicaciones por id en JSONPlaceholder
  yo como administrador del servicio
  quiero realizar una peticion de consulta de publicaciones en Placeholder
  para ver el contenido

  @ConsultarPublicaciones
  Scenario Outline:Consulta exitoso
    Given que el usuario esta en la pagina de registro
    When el usuario envia una peticion get con el <id>
    Then el usuario ve un codigo de respuesta estado <codigoEstado> y la la informacion de la publicacion
    Examples:
      | id   | codigoEstado |
      | "4"  | 200          |
      | "12" | 200          |
      | "10" | 200          |