Feature: Update Successful
  AS  json placeholder user
  I WANT TO
  make a update to a property to the system
  SO THAT
  I can use the system services

  @Update
  Scenario Outline: Update a property Successful
    Given the user is in the update page
    When the user send a update request with the <id> the "<title>" and the "<completed>"
    Then the user see a status <code> response code
    Examples:
      | id | title                             | completed | code |
      | 11 | vero rerum temporibus dolor       | true      | 200  |
      | 12 | ipsa repellendus fugit nisi       | true      | 200  |
      | 7  | illo expedita consequatur quia in | true      | 200  |