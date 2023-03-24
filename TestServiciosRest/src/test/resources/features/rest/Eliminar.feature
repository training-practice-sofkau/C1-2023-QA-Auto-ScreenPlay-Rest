Feature: Delete Successful
  AS  json placeholder user
  I WANT TO
  Delete an album to the system
  SO THAT
  I can use the system services

  @Delete
  Scenario Outline: Delete Successful
    Given the user is in the delete page
    When the user send a Delete request with the <id>
    Then the user see a response with property title that was removed and a correct status code 200
    Examples:
      | id |
      | 5  |
      | 1  |
      | 8  |