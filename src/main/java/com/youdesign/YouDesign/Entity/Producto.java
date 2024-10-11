package com.youdesign.YouDesign.Entity;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="productos")

public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id_producto;
private String id_categoria;
private String id_marca;
private String color;
private double precio;
private int cantidad;
private String imagen;


}
