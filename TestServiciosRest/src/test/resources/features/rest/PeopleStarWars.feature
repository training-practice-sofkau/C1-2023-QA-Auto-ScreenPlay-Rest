Feature:Star Wars Character Information
  I AS SWAPI user
  I WANT to access the information of the characters of Star Wars
  SO THAT I can learn about the movies


  Scenario: Obtain information of a character by his ID
    Given that the user has the ID of a Star Wars character
    When when the user makes a request with the character id
    Then the user should see a response containing the character's information

