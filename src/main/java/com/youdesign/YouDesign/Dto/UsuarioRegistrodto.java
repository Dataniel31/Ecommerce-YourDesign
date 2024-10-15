package com.youdesign.YouDesign.Dto;

public class UsuarioRegistrodto {
    private Long id_usuario;
    private String nombre;
    private String direccion;
    private String email;
    private String password;
    private Long rol_id;

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

    public Long getRol_id() {
        return rol_id;
    }

    public void setRol_id(Long rol_id) {
        this.rol_id = rol_id;
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

    public UsuarioRegistrodto(String nombre, String direccion, String email, String password, Long rol_id) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.email = email;
        this.password = password;
        this.rol_id = rol_id;
    }

    public UsuarioRegistrodto(String email) {
        this.email = email;
    }

    public UsuarioRegistrodto() {
    }
}
