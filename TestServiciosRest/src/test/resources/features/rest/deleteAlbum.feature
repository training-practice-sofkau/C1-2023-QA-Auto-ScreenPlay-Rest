# language: es

Característica: Eliminacion de albums
  Yo como usuario
  quiero eliminar un album de la plataforma jsonHolder
  Para borrar informacion que no es deseada en la plataforma.

  Escenario: Eliminacion de un album exitosa
    Dado que el usuario quiere hacer la peticion para eliminar un album
    Cuando el usuario realiza la peticion para eliminar un album con el id interno 1
    Entonces el usuario obtendra un codigo de estado exitoso