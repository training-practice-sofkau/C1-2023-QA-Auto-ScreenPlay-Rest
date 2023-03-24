Feature: Post Successful
  AS  JSON place holder user
  I WANT TO do a request to the system
  SO THAT I can post on the page

  @Post
  Scenario Outline: Post Successful
    Given the user is in the JSON place holder post page
    When the user sends the <title>, <body> and <userId> of the post he wants to post
    Then the user sees a status <code> and the post he wants to post <title>
    Examples:
      | title      | body                 | userId | code |
      | "Prueba 1" | "Hola Mundo"         | 1      | 201  |
      | "Prueba 2" | "Pasta con atun"     | 2      | 201  |
      | "Prueba 3" | "Cereales con leche" | 3      | 201  |
      | "Prueba 4" | "Arroz con pollo"    | 4      | 201  |
      | "Prueba 5" | "The best QA ever"   | 5      | 201  |