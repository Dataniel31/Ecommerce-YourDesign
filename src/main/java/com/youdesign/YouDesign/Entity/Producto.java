package com.youdesign.YouDesign.Entity;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="productos")

public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_producto;
    private int id_categoria;
    private String nombre_prod;
    private String marca;
    private double precio;
    private int stock;
}
