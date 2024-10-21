package com.youdesign.YouDesign.Controller;

import com.youdesign.YouDesign.Dto.Marcadto;
import com.youdesign.YouDesign.Entity.Marca;
import com.youdesign.YouDesign.Repository.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.*;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("admin/marcas")
public class MarcaAdminController {
    @Autowired
    private MarcaRepository marcaRepository;

    @GetMapping
    public String mostrarMarca (Model model){
        List<Marca> marca = marcaRepository.findAll();
        model.addAttribute("marca", marca);
        model.addAttribute("pageTitle", "Registro Marcas");
        return "admin/marcas";
    }
    @PostMapping
    public String guardarMarca(@ModelAttribute Marcadto marcadto){
        //guardar imagen
        MultipartFile img = marcadto.getImg_marca();
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

        Marca marca = new Marca();
        marca.setNombre(marcadto.getNombre());
        marca.setCreated(createdAt);
        marca.setImg_marca(storageFileName);
        marcaRepository.save(marca);
        return "redirect:/admin/marcas";
    }
    @PostMapping("/editar/{id}")
    public String editarMarca(Model model,@RequestParam Long id_marca, @ModelAttribute Marcadto marcadto){
        try {
            Marca marca = marcaRepository.findById(id_marca).get();
            model.addAttribute("marca", marca);

            if (!marcadto.getImg_marca().isEmpty()){
                String uploadDir = "src/main/resources/static/img/";
                Path oldImgPath = Paths.get(uploadDir + marca.getImg_marca());
                try {
                    Files.delete(oldImgPath);
                } catch (Exception ex) {
                    System.out.println("Exception: "+ex.getMessage());
                }
                MultipartFile img = marcadto.getImg_marca();
                Date createdAt = new Date();
                String storageFileName = createdAt.getTime()+ "_" + img.getOriginalFilename();

                try(InputStream inputStream = img.getInputStream()){
                    Files.copy(inputStream, Paths.get(uploadDir + storageFileName),
                            StandardCopyOption.REPLACE_EXISTING);
                }
                marca.setImg_marca(storageFileName);
            }
            marca.setNombre(marcadto.getNombre());
            marcaRepository.save(marca);
        }
        catch (Exception ex){
            System.out.println("Exception: "+ ex.getMessage());
        }
        return "redirect:/admin/marcas";
    }

    @GetMapping("/delete/{id}")
    public String eliminarMarca(@PathVariable("id") Long id_marca){
        try {
            Marca marca = marcaRepository.findById(id_marca).get();

            Path imgPath = Paths.get("src/main/resources/static/img/" + marca.getImg_marca());
            try {
                Files.delete(imgPath);
            }
            catch (Exception ex){
                System.out.println("Exception: " + ex.getMessage());
            }

            marcaRepository.delete(marca);
        }
        catch (Exception ex){
            System.out.println("Exception "+ex.getMessage());
        }
        return "redirect:/admin/marcas";
    }
}
