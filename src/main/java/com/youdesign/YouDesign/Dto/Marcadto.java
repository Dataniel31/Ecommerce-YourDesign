package com.youdesign.YouDesign.Dto;

import org.springframework.web.multipart.MultipartFile;

public class Marcadto {
    private Long id_marca;
    private String nombre;
    private MultipartFile img_marca;

    public Long getId_marca() {
        return id_marca;
    }

    public void setId_marca(Long id_marca) {
        this.id_marca = id_marca;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public MultipartFile getImg_marca() {
        return img_marca;
    }

    public void setImg_marca(MultipartFile img_marca) {
        this.img_marca = img_marca;
    }

}
