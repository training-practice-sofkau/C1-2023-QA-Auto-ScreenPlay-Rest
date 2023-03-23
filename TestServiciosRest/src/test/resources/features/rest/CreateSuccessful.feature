Feature: Registro exitoso
  AS  reqres user
  I WANT TO
  registrar un usuario en el sistema
  SO THAT
  I puedo usar los servicios del sistema

  @Crear
  Scenario Outline: Registro exitoso
    Given el usuario esta en la pagina de registro
    When cuando el usuario envia solicitud de registro  con <name> y <job>
    Then la pagina retornara un estatus con codigo <code> y id y fecha de crecion de registro
    Examples:
      | name     | job          | code |
      | "Luisa"  | "pistol"     | 200  |
      | "Salome" | "pastor"     | 400  |
      | "Jhon"   | "juan"       | 400  |
      | "Mary"   | "cualquiera" | 400  |
      | "Samuel" | "cualquiera" | 200  |