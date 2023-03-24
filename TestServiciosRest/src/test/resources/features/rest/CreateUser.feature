Feature: Creacion de usuario
  yo como administrador que usa los servicios rest
  quiero relaizar peticiones al servicio post create de reqres.in
  para registrar un usuario

  @RegisterUser
  Scenario Outline: Registrar usuario
    Given que estoy apuntando con un endpoint a la api
    When envio la peticion get con el <nombre> y la <trabajo>
    Then recibo <code> de codigo de respuesta y el id con la fecha de creacion
    Examples:
      | nombre          | trabajo       | code |
      | "Pedro"         | "Pinto"       | 201  |
      | "Juan"          | "pastor"      | 201  |
      | "Jimmy"         | "QA"          | 201  |
