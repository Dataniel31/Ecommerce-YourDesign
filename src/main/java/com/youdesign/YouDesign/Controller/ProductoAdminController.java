package com.youdesign.YouDesign.Controller;

import com.youdesign.YouDesign.Dto.Productodto;
import com.youdesign.YouDesign.Entity.Categoria;
import com.youdesign.YouDesign.Entity.Marca;
import com.youdesign.YouDesign.Entity.Pokemon;
import com.youdesign.YouDesign.Entity.Producto;
import com.youdesign.YouDesign.Repository.CategoriaRepository;
import com.youdesign.YouDesign.Repository.MarcaRepository;
import com.youdesign.YouDesign.Repository.ProductoRepository;
import com.youdesign.YouDesign.Service.PokemonService;
import com.youdesign.YouDesign.Service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("admin/productos")
public class ProductoAdminController {
    @Autowired
    private PokemonService pokemonService;
    private final ProductoRepository productoRepository;
    private final ProductoService productoService;
    private final MarcaRepository marcaRepository;
    private final CategoriaRepository categoriaRepository;

    @Autowired
    public ProductoAdminController(ProductoRepository productoRepository,
                                   ProductoService productoService,
                                   MarcaRepository marcaRepository,
                                   CategoriaRepository categoriaRepository) {
        this.productoRepository = productoRepository;
        this.productoService = productoService;
        this.marcaRepository = marcaRepository;
        this.categoriaRepository = categoriaRepository;
    }

    @GetMapping
    public String mostrarProducto (Model model){
        model.addAttribute("pageTitle", "Registro Productos");
        List<Producto> producto = productoRepository.findAll();
        model.addAttribute("producto", producto);
        List<Marca> marca = marcaRepository.findAll();
        model.addAttribute("marca", marca);
        List<Categoria> categoria = categoriaRepository.findAll();
        model.addAttribute("categoria", categoria);
        Pokemon pokemon = pokemonService.getRandomPokemon();
        model.addAttribute("pokemon", pokemon);
        return "admin/productos";
    }

    @PostMapping
    public String guardarProducto(@ModelAttribute("producto") Productodto productodto){
        Marca marcaprod = marcaRepository.findByNombre(productodto.getMarca());
        if (marcaprod == null) {
            marcaprod = new Marca(productodto.getMarca());
            marcaRepository.save(marcaprod);
        }
        Categoria cateprod = categoriaRepository.findByNombre(productodto.getCategoria());
        if (cateprod == null) {
            cateprod = new Categoria(productodto.getCategoria());
            categoriaRepository.save(cateprod);
        }
        productoService.save(productodto,marcaprod,cateprod);
        return "redirect:/admin/productos";
    }

    @PostMapping("/editar/{id}")
    public String editarProducto(@PathVariable Long id, @ModelAttribute("producto") Productodto productodto) {
        Marca marcaprod = marcaRepository.findByNombre(productodto.getMarca());
        if (marcaprod == null) {
            marcaprod = new Marca(productodto.getMarca());
            marcaRepository.save(marcaprod);
        }
        Categoria cateprod = categoriaRepository.findByNombre(productodto.getCategoria());
        if (cateprod == null) {
            cateprod = new Categoria(productodto.getCategoria());
            categoriaRepository.save(cateprod);
        }
        productoService.update(id,productodto,marcaprod,cateprod);
        return "redirect:/admin/productos";
    }
    @GetMapping("/delete/{id}")
    public String eliminarProducto(@PathVariable Long id){
        productoService.delete(id);
        return "redirect:/admin/productos";
    }
}
