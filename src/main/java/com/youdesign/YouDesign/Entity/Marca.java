package com.youdesign.YouDesign.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "marca")
public class Marca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_marca;
    private String nombre;
    private Date created;
    private String img_marca;

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

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getImg_marca() {
        return img_marca;
    }

    public void setImg_marca(String img_marca) {
        this.img_marca = img_marca;
    }

    public Marca(String nombre) {
        this.nombre = nombre;
    }

    public Marca() {
    }
}
