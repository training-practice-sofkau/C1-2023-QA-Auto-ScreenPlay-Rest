Feature: Login Successful
  AS  reqres user
  I WANT TO
  make a login to the system
  SO THAT
  I can use the system services

  @Login
  Scenario Outline: Login Successful
    Given the user is in the login page
    When the user send a login request with the <email> and the <password>
    Then the user see a status response code and an id with a token
    Examples:
      | email                        | password     |
      | "eve.holt@reqres.in"         | "cityslicka" |
      | "eve.holt@reqres.in"        | "3658"       |
      | "lindsay.ferguson@reqres.in" | "1234"       |
