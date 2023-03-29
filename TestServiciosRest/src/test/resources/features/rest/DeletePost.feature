Feature: delete blog posts
  I AS Json Placeholder user
  I WANT to delete an existing post
  TO keep blog content updated

  Scenario: Delete the first post
    Given that the user is logged into the application
    When the user sends a request to remove the post
    Then the user should see a status response code 200