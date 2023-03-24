Feature: Realizar put en un post
  AS usuario de la pagina de JsonPlaceHolder
  WANT realizar actualizaciones en los post
  BECAUSE mantener los posts actualizados

  @PutPosts
  Scenario Outline: Actualizar post por id
    Given El usuario se encuentra en la web de JsonPlaceHolder para actualizar los post
    When El usuario envia una solicitud con el <id> del post deseado para actualizar el <title> el <userid> y el <body>
    Then El usuario debe recibir un respuesta de status <code> y el post solicitado con su actualizacion
    Examples:
      | id  | title                         | body                                                      | userid | code |
      | "1" | "El increible balon cuadrado" | "Se encuentra un increible balon cuadrado en un lugar"    | 5      | 200  |
      | "2" | "El tucan se cayo"            | "Un bonito tucan se cayo al piso mientras volaba"         | 6      | 200  |
      | "3" | "Encuentre lo que busca"      | "Se descubre un manera de encontrar simpre los que busca" | 5      | 200  |
