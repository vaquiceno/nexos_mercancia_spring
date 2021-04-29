package com.nexos.mercancia.repository;

import com.nexos.mercancia.model.Producto;
import com.nexos.mercancia.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

    void deleteProductoById(Long id);

    Optional<Producto> findProductoById(Long id);

    boolean existsByIdAndUsuario(Long id, Usuario usuario);
}
