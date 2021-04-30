package com.nexos.mercancia.service;

import com.nexos.mercancia.model.AuditoriaProducto;
import com.nexos.mercancia.repository.AuditoriaProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AuditoriaProductoService {
    private final AuditoriaProductoRepository auditoriaProductoRepository;

    @Autowired
    public AuditoriaProductoService(AuditoriaProductoRepository auditoriaProductoRepository) {
        this.auditoriaProductoRepository = auditoriaProductoRepository;
    }

    public List<AuditoriaProducto> findAllAuditoriaProducto(){
        return auditoriaProductoRepository.findAll();
    }

    public AuditoriaProducto addAuditoriaProducto(AuditoriaProducto auditoriaProducto){
        return auditoriaProductoRepository.save(auditoriaProducto);
    }
}
