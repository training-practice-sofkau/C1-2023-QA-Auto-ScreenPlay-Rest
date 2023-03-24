Feature: Create a Book
  As: An User of Faker REST API
  I want: To create a new book
  So that

  @CreateBook
  Scenario Outline: Create a new book
    Given I have access to Fake REST API server
    When I try to create a book with id <id>, title '<title>' and page count <pageCount>
    Then I will see the response code <code>
    And I will receive the book info back
    Examples:
      | id  | title          | pageCount | code |
      | 1   | Las rosas      | 12        | 200  |
      | -1  | Cara dura      | 69        | 200  |
      | 100 | Flecha         | 44        | 200  |
      | 101 | Por la Ventana | 188       | 200  |