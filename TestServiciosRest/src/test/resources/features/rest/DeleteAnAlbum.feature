Feature: Delete an Album
  As: An User of JSONPlaceholder
  I want: To delete a particular album
  So that

  @DeletePost
  Scenario Outline: Delete several albums
    Given I have access to JSONPlaceholder API server
    When I try to delete an album with id <id>
    Then I will see a response code <code>
    Examples:
      | id  | code |
      | 1   | 200  |
      | -1  | 404  |
      | 100 | 200  |
      | 101 | 404  |