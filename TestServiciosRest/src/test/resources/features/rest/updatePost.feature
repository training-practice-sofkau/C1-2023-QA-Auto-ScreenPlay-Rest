Feature: Update Post
  As a user
  I want to be able to update an existing post in the system
  So that I can keep my posts up to date

  Scenario: Update an existing post
    Given a post already exists in the system
    When the user updates the post with new information
    Then the post should be updated successfully

