package com.nexos.mercancia.service;

import com.nexos.mercancia.exception.ProductoNotFoundException;
import com.nexos.mercancia.exception.UsuarioNotFoundException;
import com.nexos.mercancia.model.AuditoriaProducto;
import com.nexos.mercancia.model.Producto;
import com.nexos.mercancia.model.Usuario;
import com.nexos.mercancia.repository.AuditoriaProductoRepository;
import com.nexos.mercancia.repository.ProductoRepository;
import com.nexos.mercancia.repository.UsuarioRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ProductoService {
    private final ProductoRepository productoRepository;
    private final AuditoriaProductoRepository auditoriaProductoRepository;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public ProductoService(ProductoRepository productoRepository, AuditoriaProductoRepository auditoriaProductoRepository, UsuarioRepository usuarioRepository) {
        this.productoRepository = productoRepository;
        this.auditoriaProductoRepository = auditoriaProductoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public Producto addProducto(Producto producto) throws Exception{
        try{
            return productoRepository.save(producto);
        }catch (Exception e){
            throw e;
        }

    }

    public List<Producto> findAllProductos(){
        return productoRepository.findAll();
    }

    public Producto updateProducto(Producto producto, Long userid) throws Exception{
        try{
            Producto consultaProducto = productoRepository.findProductoById(producto.getId()).orElseThrow(() -> new ProductoNotFoundException("Producto con id "+ userid +" no encontrado"));
            if (!consultaProducto.equals(producto)){
                AuditoriaProducto auditoriaProducto = new AuditoriaProducto(producto.getId(), userid);
                auditoriaProductoRepository.save(auditoriaProducto);
                return productoRepository.save(producto);
            }
            return consultaProducto;
        }catch (Exception e){
            throw e;
        }

    }

    public Producto findProductoById(Long id){
        return productoRepository.findProductoById(id).orElseThrow(() -> new ProductoNotFoundException("Producto con id "+ id +" no encontrado"));
    }

    public boolean deleteProducto(Long productoid, Long userid){
        //solo puede ser eliminada por el usuario que la registro
        Usuario user = usuarioRepository.findById(userid).orElseThrow(() -> new UsuarioNotFoundException("Usuario con id "+ userid +" no encontrado"));
        if (productoRepository.existsByIdAndUsuario(productoid,user)){
            productoRepository.deleteProductoById(productoid);
            return true;
        }
        return false;
    }
 }
