Feature: Update a Post in JSONPlaceholder API
  As a user of JSONPlaceholder API
  I want to update a post by its Id
  So that I can modify the details of a specific post

  Scenario Outline: Update an existing post
    Given the JSONPlaceholder API is available to use
    When I make a PUT request to update the post with <id>
    Then the response status code that should be received is <code>
    And the response body should contain the updated post details
    Examples:
      | id    | code |
      | "4"   | 200  |
      | "11"  | 200  |
      | "13"  | 200  |
      | "22"  | 200  |