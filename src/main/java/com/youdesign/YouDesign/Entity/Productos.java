package com.youdesign.YouDesign.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "productos")
public class Productos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_producto;
    private Long id_categoria;
    private String nombre_prod;
    private String marca;
    private double precio;
    private int stock;
}
