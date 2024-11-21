package com.youdesign.YouDesign.Entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_producto;
    @Column(name = "nombre_prod")
    private String nombre_prod;
    @Column(name = "precio")
    private double precio;
    @Column(name = "stock")
    private int stock;
    @Column(name = "created")
    private Date created;
    @Column(name = "img_prod")
    private String img_prod;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "marca_id", referencedColumnName = "id_marca")
    private Marca marca;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cate_id", referencedColumnName = "id_cate")
    private Categoria categoria;

    public Long getId_producto() {
        return id_producto;
    }

    public void setId_producto(Long id_producto) {
        this.id_producto = id_producto;
    }

    public String getNombre_prod() {
        return nombre_prod;
    }

    public void setNombre_prod(String nombre_prod) {
        this.nombre_prod = nombre_prod;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getImg_prod() {
        return img_prod;
    }

    public void setImg_prod(String img_prod) {
        this.img_prod = img_prod;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

}
