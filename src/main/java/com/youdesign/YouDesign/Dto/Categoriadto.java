package com.youdesign.YouDesign.Dto;

import org.springframework.web.multipart.MultipartFile;

public class Categoriadto {
    private Long id_cate;
    private String nombre;
    private MultipartFile img_cate;

    public Long getId_cate() {
        return id_cate;
    }

    public void setId_cate(Long id_cate) {
        this.id_cate = id_cate;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public MultipartFile getImg_cate() {
        return img_cate;
    }

    public void setImg_cate(MultipartFile img_cate) {
        this.img_cate = img_cate;
    }
}
