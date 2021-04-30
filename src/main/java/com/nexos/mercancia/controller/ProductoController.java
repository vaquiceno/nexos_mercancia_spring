package com.nexos.mercancia.controller;

import com.nexos.mercancia.model.Producto;
import com.nexos.mercancia.model.Usuario;
import com.nexos.mercancia.service.ProductoService;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
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
        Producto nuevoProducto = null;
        try{
            nuevoProducto = productoService.addProducto(producto);
        }catch (ConstraintViolationException | DataIntegrityViolationException e){
            return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(nuevoProducto, HttpStatus.CREATED);
    }

    @PutMapping("/update/{userid}")
    public ResponseEntity<Producto> updateProducto (@RequestBody Producto producto,@PathVariable("userid") Long userid){
        Producto updateProducto = null;
        try{
            updateProducto = productoService.updateProducto(producto, userid);
        }catch (ConstraintViolationException | DataIntegrityViolationException e){
            return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(updateProducto, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{productoid}/{userid}")
    public ResponseEntity<?> deleteProducto (@PathVariable("productoid") Long productoid, @PathVariable("userid") Long userid){
        if (productoService.deleteProducto(productoid,userid))
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}
