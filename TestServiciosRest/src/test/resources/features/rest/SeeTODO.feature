Feature: See To Do tasks
  As: The User with id 1 of JSONPlaceholder
  I want: To see if did a task or not
  So that

  @TODO
  Scenario Outline: Tasks done
    Given I have access to the JSONPlaceholder API server
    When I check my to-do tasks with id <id>
    Then I will see the completed status '<status>' of the task
    And I will receive a status code <code>
    Examples:
      | id | status | code |
      | 1  | false  | 200  |
      | 2  | true   | 200  |
      | 4  | true   | 200  |
      | 21 | ''     | 404  |