Feature: Update Post
  As a user
  I want to be able to update an existing post in the system
  So that I can keep my posts up to date

  Scenario Outline: Update an existing post
    Given a post with ID "<post_id>" exists in the system
    When the user updates the post with new information "<new_title>" and "<new_body>"
    Then the post should be updated successfully with updated values "<new_title>" and "<new_body>"


    Examples:
      | post_id | new_title   | new_body                      |
      | 1       | New Title 1 | Lorem Ipsum is simply dummy   |
      | 2       | New Title 2 | It is a long established fact |
      | 3       | New Title 3 | There are many variations     |