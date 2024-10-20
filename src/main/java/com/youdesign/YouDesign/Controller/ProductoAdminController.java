package com.youdesign.YouDesign.Controller;

import com.youdesign.YouDesign.Dto.ProductoDTO;
import com.youdesign.YouDesign.Entity.Productos;
import com.youdesign.YouDesign.Repository.ProductoRepository;
import com.youdesign.YouDesign.Service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/admin/productos")
public class ProductoAdminController {

    @Autowired
    private ProductoService productoService;
    private ProductoRepository productoRepository;

    private static final String UPLOAD_DIR = "src/main/resources/static/img/";

    @GetMapping
    public String listarProductos(Model model) {
        model.addAttribute("productos", productoService.listarTodos());
        model.addAttribute("pageTitle", "Registro Productos");
        return "admin/productos";
    }

    @PostMapping
    public String guardarProducto(@ModelAttribute ProductoDTO productoDTO) {
        Productos producto = new Productos();
        producto.setNombre_prod(productoDTO.getNombre_prod());
        producto.setMarca(productoDTO.getMarca());
        producto.setPrecio(productoDTO.getPrecio());
        producto.setStock(productoDTO.getStock());

        // Guardar la imagen en el sistema de archivos
        MultipartFile imagen = productoDTO.getImagen();
        if (!imagen.isEmpty()) {
            String nombreImagen = UUID.randomUUID().toString() + "_" + imagen.getOriginalFilename();
            Path rutaImagen = Paths.get(UPLOAD_DIR).resolve(nombreImagen).toAbsolutePath();
            try {
                Files.copy(imagen.getInputStream(), rutaImagen);
                producto.setImagen(nombreImagen);  // Guardar el nombre de la imagen en la base de datos
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        productoService.save(producto);
        return "redirect:/admin/productos";
    }

    @PutMapping("/edit/{id}")
    public String editarProducto(@PathVariable Long id, @ModelAttribute ProductoDTO productoDTO) {
        Optional<Productos> productosOpt = productoRepository.findById(id);
        Productos producto = productoService.obtenerProductoPorId(id);
        if (productosOpt.isPresent()) {
            Productos productos = productosOpt.get();
            productos.setNombre_prod(productoDTO.getNombre_prod());
            productos.setMarca(productoDTO.getMarca());
            productos.setPrecio(productoDTO.getPrecio());
            productos.setStock(productoDTO.getStock());

            MultipartFile imagen = productoDTO.getImagen();
            if (!imagen.isEmpty()) {
                String nombreImagen = UUID.randomUUID().toString() + "_" + imagen.getOriginalFilename();
                Path rutaImagen = Paths.get("src/main/resources/static/img/").resolve(nombreImagen).toAbsolutePath();
                try {
                    Files.copy(imagen.getInputStream(), rutaImagen);
                    producto.setImagen(nombreImagen);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            productoService.save(producto);
        }
        return "redirect:/admin/productos";
    }

    @GetMapping("/delete/{id}")
    public String eliminarProducto(@PathVariable("id") Long id) {
        productoService.eliminarProducto(id);
        return "redirect:/admin/productos";
    }

}
