package com.youdesign.YouDesign.Controller;

import com.youdesign.YouDesign.Dto.Categoriadto;
import com.youdesign.YouDesign.Entity.Categoria;
import com.youdesign.YouDesign.Repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("admin/categorias")
public class CategoriaAdminController {
    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    public String mostrarCategoria (Model model){
        List<Categoria> categoria = categoriaRepository.findAll();
        model.addAttribute("categoria", categoria);
        model.addAttribute("pageTitle", "Registro Categoria");
        return "admin/categorias";
    }
    @PostMapping
    public String guardarCategoria(@ModelAttribute Categoriadto categoriadto){
        //guardar imagen
        MultipartFile img = categoriadto.getImg_cate();
        Date createdAt = new Date();
        String storageFileName = createdAt.getTime()+ "_" +img.getOriginalFilename();
        try{
            String uploadDir = "src/main/resources/static/img/";
            Path uploadPath = Paths.get(uploadDir);

            if (!Files.exists(uploadPath)){
                Files.createDirectories(uploadPath);
            }
            try (InputStream inputStream = img.getInputStream()){
                Files.copy(inputStream, Paths.get(uploadDir + storageFileName),
                        StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (Exception ex){
            System.out.println("Exception: "+ex.getMessage());
        }

        Categoria categoria = new Categoria();
        categoria.setNombre(categoriadto.getNombre());
        categoria.setCreated(createdAt);
        categoria.setImg_cate(storageFileName);
        categoriaRepository.save(categoria);
        return "redirect:/admin/categorias";
    }
    @PostMapping("/editar/{id}")
    public String editarCategoria(Model model, @RequestParam Long id_cate, @ModelAttribute Categoriadto categoriadto){
        try {
            Categoria categoria = categoriaRepository.findById(id_cate).get();
            model.addAttribute("categoria", categoria);

            if (!categoriadto.getImg_cate().isEmpty()){
                String uploadDir = "src/main/resources/static/img/";
                Path oldImgPath = Paths.get(uploadDir + categoria.getImg_cate());
                try {
                    Files.delete(oldImgPath);
                } catch (Exception ex) {
                    System.out.println("Exception: "+ex.getMessage());
                }
                MultipartFile img = categoriadto.getImg_cate();
                Date createdAt = new Date();
                String storageFileName = createdAt.getTime()+ "_" + img.getOriginalFilename();

                try(InputStream inputStream = img.getInputStream()){
                    Files.copy(inputStream, Paths.get(uploadDir + storageFileName),
                            StandardCopyOption.REPLACE_EXISTING);
                }
                categoria.setImg_cate(storageFileName);
            }
            categoria.setNombre(categoriadto.getNombre());
            categoriaRepository.save(categoria);
        }
        catch (Exception ex){
            System.out.println("Exception: "+ ex.getMessage());
        }
        return "redirect:/admin/categorias";
    }

    @GetMapping("/delete/{id}")
    public String eliminarCategoria(@PathVariable("id") Long id_cate){
        try {
            Categoria categoria = categoriaRepository.findById(id_cate).get();

            Path imgPath = Paths.get("src/main/resources/static/img/" + categoria.getImg_cate());
            try {
                Files.delete(imgPath);
            }
            catch (Exception ex){
                System.out.println("Exception: " + ex.getMessage());
            }

            categoriaRepository.delete(categoria);
        }
        catch (Exception ex){
            System.out.println("Exception "+ex.getMessage());
        }
        return "redirect:/admin/categorias";
    }
}
