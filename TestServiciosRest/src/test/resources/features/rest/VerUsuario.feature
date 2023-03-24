Feature: Ver informacion de un usuario
  yo como administrador que usa los servicios rest
  quiero relaizar peticiones al servicio get usuarios de jsonplaceholder
  para ver toda la informacion de un registro

  @RegisterUser
  Scenario Outline: Ver informacion de un usuario
    Given que estoy apuntando con un endpoint a la api de jsonplaceholder
    When envio la peticion get con el <id>
    Then recibo <code> de codigo de respuesta y la informacion del usuario
    Examples:
      | id       | code |
      | "1"      | 200  |
      | "2"      | 200  |
      | "5"      | 200  |
