package com.nexos.mercancia.service;

import com.nexos.mercancia.exception.ProductoNotFoundException;
import com.nexos.mercancia.model.AuditoriaProducto;
import com.nexos.mercancia.model.Producto;
import com.nexos.mercancia.model.Usuario;
import com.nexos.mercancia.repository.AuditoriaProductoRepository;
import com.nexos.mercancia.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ProductoService {
    private final ProductoRepository productoRepository;
    private final AuditoriaProductoRepository auditoriaProductoRepository;

    @Autowired
    public ProductoService(ProductoRepository productoRepository, AuditoriaProductoRepository auditoriaProductoRepository) {
        this.productoRepository = productoRepository;
        this.auditoriaProductoRepository = auditoriaProductoRepository;
    }

    public Producto addProducto(Producto producto){
        return productoRepository.save(producto);
    }

    public List<Producto> findAllProductos(){
        return productoRepository.findAll();
    }

    public Producto updateProducto(Producto producto, Long userid){
        Producto consultaProducto = productoRepository.findProductoById(producto.getId()).orElseThrow(() -> new ProductoNotFoundException("Producto con id "+ userid +" no encontrado"));
        if (!consultaProducto.equals(producto)){
            AuditoriaProducto auditoriaProducto = new AuditoriaProducto(producto.getId(), userid);
            auditoriaProductoRepository.save(auditoriaProducto);
            return productoRepository.save(producto);
        }
        return consultaProducto;

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
