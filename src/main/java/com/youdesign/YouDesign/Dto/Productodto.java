package com.youdesign.YouDesign.Dto;


import org.springframework.web.multipart.MultipartFile;

public class Productodto {
    private Long id_producto;
    private String nombre_prod;
    private double precio;
    private int stock;
    private MultipartFile img_prod;
    private String marca;
    private String categoria;

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

    public MultipartFile getImg_prod() {
        return img_prod;
    }

    public void setImg_prod(MultipartFile img_prod) {
        this.img_prod = img_prod;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

}
