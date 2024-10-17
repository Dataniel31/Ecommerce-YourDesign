package com.youdesign.YouDesign.Service;

import com.youdesign.YouDesign.Entity.Productos;
import com.youdesign.YouDesign.Repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Productos> listarTodos() {
        return productoRepository.findAll();
    }

    public void save(Productos producto) {
        productoRepository.save(producto);
    }

    public Productos obtenerProductoPorId(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    public void eliminarProducto(Long id) {
        productoRepository.deleteById(id);
    }
}
