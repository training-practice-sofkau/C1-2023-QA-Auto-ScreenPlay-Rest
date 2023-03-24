Feature: Delete Successful
  AS  json placeholder user
  I WANT TO
  delete an todo to the system
  SO THAT
  I can use the system services

  @List
  Scenario Outline: List Successful
    Given the user is in the list page
    When the user send a list request with the <userId>
    Then the user see a <id> with "<title>" response title
    Examples:
      | id | userId | title                  |
      | 5  | 1      | eaque aut omnis a      |
      | 1  | 1      | quidem molestiae enim  |
      | 2  | 3      | qui fuga est a eum     |
      | 4  | 3      | distinctio laborum qui |