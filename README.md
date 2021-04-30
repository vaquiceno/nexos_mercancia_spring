# nexos_mercancia_spring
Backend Springboot para aplicacion de gestion de mercancias nexos

Para consultar la estructura de base de datos, ir al archivo db.sql

/api/v1/producto/all - GET
Retorna todos los Productos con su respectivo Usuario creador

/api/v1/usuario/all - GET
Retorna todos los Usuarios.

/api/v1/producto/find/{id} - GET
Retorna Producto especifico por Id.

/api/v1/producto/add - POST
Agrega producto a la base de datos, enviar en este formato el JSON:
{
    "nombre": "manzana",
    "cantidad": 2,
    "fechaIngreso": "2021-04-29",
    "usuario": {
      "id":2
    }
}

/api/v1/producto/update/{userid} - PUT
Modifica el producto en la base de datos, enviar en este formato el JSON:
{
    "id": 16,
    "nombre": "manzana1",
    "cantidad": 2,
    "fechaIngreso": "2021-04-29",
    "usuario": {
        "id": 2
    }
}

el parametro userid se usa para guardar en la tabla de auditoria_mercancia_nexos el usuario que modifico el producto con la fecha.

/api/v1/producto/delete/{productoid}/{userid} - DELETE
Elimina producto de la base de datos, se envia el id del producto y el id del usuario
