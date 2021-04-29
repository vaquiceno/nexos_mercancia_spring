package com.nexos.mercancia.service;

import com.nexos.mercancia.exception.ProductoNotFoundException;
import com.nexos.mercancia.model.Producto;
import com.nexos.mercancia.model.Usuario;
import com.nexos.mercancia.repository.ProductoRepository;
import com.sun.scenario.effect.impl.prism.PrDrawable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ProductoService {
    private final ProductoRepository productoRepository;

    @Autowired
    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public Producto addProducto(Producto producto){
        return productoRepository.save(producto);
    }

    public List<Producto> findAllProductos(){
        return productoRepository.findAll();
    }

    public Producto updateProducto(Producto producto){
        return productoRepository.save(producto);
    }

    public Producto findProductoById(Long id){
        return productoRepository.findProductoById(id).orElseThrow(() -> new ProductoNotFoundException("Producto con id "+ id +" no encontrado"));
    }

    public void deleteProducto(Long productoid, Usuario user){
        //solo puede ser eliminada por el usuario que la registro
        if (productoRepository.existsByIdAndUsuario(productoid,user))
            productoRepository.deleteProductoById(productoid);
    }
 }
