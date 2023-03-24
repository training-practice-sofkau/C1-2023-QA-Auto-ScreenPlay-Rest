Feature: See To Do tasks
  As: The User with id 1 of JSONPlaceholder
  I want: To see the title of a task
  So that

  @TODO
  Scenario Outline: Tasks title
    Given I have access to the JSONPlaceholder API server
    When I check my to-do tasks with id <id>
    Then I will see the title '<title>' of the task
    And I will receive a status code <code>
    Examples:
      | id | title               | code |
      | 1  | delectus aut autem  | 200  |
      | 2  | quis ut nam facilis | 200  |
      | 4  | et porro tempora    | 200  |
      | 21 |                     | 404  |