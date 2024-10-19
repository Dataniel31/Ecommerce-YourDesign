package com.youdesign.YouDesign.Dto;

import lombok.Data;

@Data
public class UsuarioRegistrodto {
    private Long id_usuario;
    private String nombre;
    private String direccion;
    private String email;
    private String password;
    private String rol;

    public Long getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Long id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public UsuarioRegistrodto(Long id_usuario, String nombre, String direccion, String email, String password) {
        this.id_usuario = id_usuario;
        this.nombre = nombre;
        this.direccion = direccion;
        this.email = email;
        this.password = password;
    }

    public UsuarioRegistrodto(String nombre, String direccion, String email, String password) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.email = email;
        this.password = password;
    }

    public UsuarioRegistrodto(String nombre, String direccion, String email, String password, String rol) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.email = email;
        this.password = password;
        this.rol = rol;
    }

    public UsuarioRegistrodto(String email) {
        this.email = email;
    }

    public UsuarioRegistrodto() {
    }
}
