Feature: List Successful
  AS  json placeholder user
  I WANT TO
  list an album to the system
  SO THAT
  I can use the system services

  @List
  Scenario Outline: List Successful
    Given the user is in the list page
    When the user send a list request with the <id>
    Then the user see a response with property "<title>"
    Examples:
      | id | title                  |
      | 5  | eaque aut omnis a      |
      | 1  | quidem molestiae enim  |
      | 8  | qui fuga est a eum     |
      | 10 | distinctio laborum qui |