package com.youdesign.YouDesign.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="usuario")

public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_usuario;
    private int id_rol;
    private String nombre;
    private String direccion;
    private String email;
    private String usuario;
    private String password;
}
