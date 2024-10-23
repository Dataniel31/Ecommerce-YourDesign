package com.youdesign.YouDesign.Service;

import com.youdesign.YouDesign.Dto.Productodto;
import com.youdesign.YouDesign.Entity.Categoria;
import com.youdesign.YouDesign.Entity.Marca;
import com.youdesign.YouDesign.Entity.Producto;
import com.youdesign.YouDesign.Repository.CategoriaRepository;

import java.util.List;

public interface ProductoService{
    Producto save(Productodto productodto, Marca marca, Categoria categoria);
    Producto update(Long id, Productodto productodto, Marca marca, Categoria categoria);
    void delete(Long id_producto);
    List<Producto> findByCate(Categoria cate);
}
