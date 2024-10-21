package com.youdesign.YouDesign.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name="categoria")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_cate;
    private String nombre;
    private Date created;
    private String img_cate;

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

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getImg_cate() {
        return img_cate;
    }

    public void setImg_cate(String img_cate) {
        this.img_cate = img_cate;
    }

    public Categoria(String nombre) {
        this.nombre = nombre;
    }

    public Categoria() {
    }
}
