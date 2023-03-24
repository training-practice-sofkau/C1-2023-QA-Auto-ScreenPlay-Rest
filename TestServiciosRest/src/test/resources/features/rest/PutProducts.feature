Feature: Actualizar productos
  yo como administrador del sistema de gestion de productos
  Quiero poder actualizar productos
  Para poder actualizar producto con diferente informacion

  @ActualizarProducto
  Scenario Outline: Actualizar productos
    Given el administrador esta en la pagina FakerProducts seccion actualizar
    When el administrador envia la informacion  <title>, <precio>, <descripcion>, <imagen>, <categoria>
    Then el administrador debe ver un mensaje con informacion actualizada del producto con un estatus <code>
    Examples:
      | title       | precio | descripcion           | imagen                | categoria     | code |
      | "Product 1" | 22.0   | "Updated Description" | "https://updated.com" | "Electronics" | 200  |
      | "Product 2" | 16.0   | "Updated Description" | "https://updated.com" | "Clothing"    | 200  |