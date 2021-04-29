package com.nexos.mercancia.controller;

import com.nexos.mercancia.model.Producto;
import com.nexos.mercancia.model.Usuario;
import com.nexos.mercancia.service.ProductoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/v1/producto")
public class ProductoController {
    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Producto>> getAllProductos() {
        List<Producto> productos = productoService.findAllProductos();
        return new ResponseEntity<>(productos, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Producto> getProductoById(@PathVariable("id") Long id) {
        Producto producto = productoService.findProductoById(id);
        return new ResponseEntity<>(producto, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Producto> addProducto (@RequestBody Producto producto){
        Producto nuevoProducto = productoService.addProducto(producto);
        return new ResponseEntity<>(nuevoProducto, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Producto> updateProducto (@RequestBody Producto producto){
        Producto updateProducto = productoService.updateProducto(producto);
        return new ResponseEntity<>(updateProducto, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{productoid}/{user}")
    public ResponseEntity<?> deleteProducto (@PathVariable("productoid") Long productoid, @PathVariable("user") Usuario user){
        productoService.deleteProducto(productoid,user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
