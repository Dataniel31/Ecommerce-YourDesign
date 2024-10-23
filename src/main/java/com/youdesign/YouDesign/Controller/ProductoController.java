package com.youdesign.YouDesign.Controller;

import com.youdesign.YouDesign.Entity.Categoria;
import com.youdesign.YouDesign.Entity.Producto;
import com.youdesign.YouDesign.Repository.CategoriaRepository;
import com.youdesign.YouDesign.Repository.ProductoRepository;
import com.youdesign.YouDesign.Service.ProductoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ProductoController {
    private final CategoriaRepository categoriaRepository;
    private final ProductoService productoService;
    private final ProductoRepository productoRepository;

    public ProductoController(CategoriaRepository categoriaRepository, ProductoService productoService, ProductoRepository productoRepository) {
        this.categoriaRepository = categoriaRepository;
        this.productoService = productoService;
        this.productoRepository = productoRepository;
    }

    @GetMapping("/productos")
    public String politicaprivacidad(Model model) {
        model.addAttribute("pageTitle", "Productos");
        List<Categoria> categoria = categoriaRepository.findAll();
        model.addAttribute("categoria", categoria);
        List<Producto> producto = productoRepository.findAll();
        model.addAttribute("producto", producto);
        return "list-producto";
    }
    @GetMapping("/plantilla")
    public String listarCategoriasPlantilla(Model model){
        List<Categoria> categoria = categoriaRepository.findAll();
        model.addAttribute("categoria", categoria);
       return "plantilla";
    }
    @GetMapping("/productos/{cateid}")
    public String PorductosporCate(@PathVariable("cateid") Categoria cate, Model model){
        model.addAttribute("pageTitle", "Productos");
        List<Categoria> categoria = categoriaRepository.findAll();
        model.addAttribute("categoria", categoria);
        model.addAttribute("producto", productoService.findByCate(cate));
        return "list-producto";
    }
}
