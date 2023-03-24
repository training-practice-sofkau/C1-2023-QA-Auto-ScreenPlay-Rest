Feature:consultar usuario por id en JSONPlaceholder
  yo como administrador del servicio JSONPlaceholder
  quiero realizar una peticion de consulta de usuario por id en Placeholder
  para ver los usuarios registrados

  @ConsultarUsuario
  Scenario Outline:Consulta exitoso
    Given que el usuario esta en la pagina de registro de usuario
    When el usuario envia una peticion get con el <id> del usuario
    Then el usuario debe observar un codigo de respuesta <codigEstado> y la la informacion del usuario
    Examples:
      | id  | codigEstado |
      | "5" | 200         |
      | "7" | 200         |
      | "9" | 200         |