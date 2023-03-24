Feature: Delete Post with id
  AS json place holder user
  I WANT TO make a request to the Delete post service
  SO THAT I can delte a post from the list of posts

  Scenario: Delete a post successfully
    Given the user is in the json place holder web page
    When the user sends a request to the Delete service
    Then the user gets a status code response Ok