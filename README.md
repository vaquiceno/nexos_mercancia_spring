# nexos_mercancia_spring
Backend Springboot para aplicacion de gestion de mercancias nexos


/api/v1/producto/all - GET
Retorna todos los Productos con su respectivo Usuario creador

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

/api/v1/producto/update - PUT
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


/api/v1/producto/delete/{productoid}/{user} - DELETE
Elimina producto de la base de datos, se envia el id del producto y el id del usuario
