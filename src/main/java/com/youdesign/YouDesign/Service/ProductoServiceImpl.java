package com.youdesign.YouDesign.Service;

import com.youdesign.YouDesign.Dto.Productodto;
import com.youdesign.YouDesign.Entity.Categoria;
import com.youdesign.YouDesign.Entity.Marca;
import com.youdesign.YouDesign.Entity.Producto;
import com.youdesign.YouDesign.Repository.CategoriaRepository;
import com.youdesign.YouDesign.Repository.MarcaRepository;
import com.youdesign.YouDesign.Repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductoService {
    @Autowired
    private MarcaRepository marcaRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public Producto save(Productodto productodto, Marca marca, Categoria categoria) {
        Marca marcaprod = marcaRepository.findByNombre(marca.getNombre());
        if (marcaprod == null) {
            marcaprod = new Marca(marca.getNombre());
            marcaRepository.save(marcaprod);
        }
        Categoria cateprod = categoriaRepository.findByNombre(categoria.getNombre());
        if (cateprod == null) {
            cateprod = new Categoria(categoria.getNombre());
            categoriaRepository.save(cateprod);
        }
        // guardar imagen
        MultipartFile img = productodto.getImg_prod();
        Date createdAt = new Date();
        String storageFileName = createdAt.getTime() + "_" + img.getOriginalFilename();
        try {
            String uploadDir = "src/main/resources/static/img/";
            Path uploadPath = Paths.get(uploadDir);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            try (InputStream inputStream = img.getInputStream()) {
                Files.copy(inputStream, Paths.get(uploadDir + storageFileName),
                        StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
        Producto producto = new Producto();
        producto.setNombre_prod(productodto.getNombre_prod());
        producto.setMarca(marcaprod);
        producto.setCategoria(cateprod);
        producto.setPrecio(productodto.getPrecio());
        producto.setStock(productodto.getStock());
        producto.setCreated(createdAt);
        producto.setImg_prod(storageFileName);
        return productoRepository.save(producto);
    }

    @Override
    public Producto update(Long id_producto, Productodto productodto, Marca marca, Categoria categoria) {
        try {
            Producto producto = productoRepository.findById(id_producto).get();

            if (!productodto.getImg_prod().isEmpty()) {
                String uploadDir = "src/main/resources/static/img/";
                Path oldImgPath = Paths.get(uploadDir + producto.getImg_prod());
                try {
                    Files.delete(oldImgPath);
                } catch (Exception ex) {
                    System.out.println("Exception " + ex.getMessage());
                }
                MultipartFile img = productodto.getImg_prod();
                Date createdAt = new Date();
                String storageFileName = createdAt.getTime() + "_" + img.getOriginalFilename();
                try (InputStream inputStream = img.getInputStream()) {
                    Files.copy(inputStream, Paths.get(uploadDir + storageFileName),
                            StandardCopyOption.REPLACE_EXISTING);
                }
                producto.setImg_prod(storageFileName);
            }
            producto.setNombre_prod(productodto.getNombre_prod());
            producto.setMarca(marca);
            producto.setCategoria(categoria);
            producto.setPrecio(productodto.getPrecio());
            producto.setStock(productodto.getStock());

            return productoRepository.save(producto);

        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
            return null;
        }
    }

    @Override
    public void delete(Long id_producto) {
        try {
            Producto producto = productoRepository.findById(id_producto).get();

            Path imgPath = Paths.get("src/main/resources/static/img/" + producto.getImg_prod());
            try {
                Files.delete(imgPath);
            } catch (Exception ex) {
                System.out.println("Exception: " + ex.getMessage());
            }

            productoRepository.deleteById(id_producto);
        } catch (Exception ex) {
            System.out.println("Exception " + ex.getMessage());
        }

    }

    @Override
    public List<Producto> findByCate(Categoria cate) {
        return productoRepository.findByCategoria(categoriaRepository.findByNombre(cate.getNombre()));
    }

}
