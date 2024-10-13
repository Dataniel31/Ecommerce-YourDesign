package com.youdesign.YouDesign.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "rol_user")
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_rol;
    private String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getId_rol() {
        return id_rol;
    }

    public void setId_rol(Long id_rol) {
        this.id_rol = id_rol;
    }

    public Rol(Long id_rol, String nombre) {
        this.id_rol = id_rol;
        this.nombre = nombre;
    }

    public Rol() {
    }

    public Rol(String nombre) {
        this.nombre = nombre;
    }
}
