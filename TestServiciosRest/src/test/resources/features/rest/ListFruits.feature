Feature: Register Successful
  AS  fruityvice user
  I WANT TO
  make  a request GET
  SO THAT
  i can see information about fruits
  @Information
  Scenario Outline: Received information
    Given the user is in API fruityvice
    When the user send request GET with <fruit> name
    Then the user see the information of the fruit and a <status>
    Examples:
      | fruit  | status |
      |"Banana"|  200  |
      |"mango" |  200  |
      |"Apple" |  200  |