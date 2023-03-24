Feature: Retrieve Posts from JSONPlaceholder API
  As a user of JSONPlaceholder API
  I want to retrieve a post by its ID
  So that I can view the details of a specific post

  @Get
  Scenario Outline: Retrieve posts from the JSONPlaceholder API
    Given the JSONPlaceholder API is available
    When I make a GET request by <id>
    Then the response status code should be <code>
    Examples:
      | id    | code |
      | "4"   | 200  |
      | "11"  | 200  |
      | "13 " | 200  |
      | "*"   | 404  |
      | "101" | 404  |
