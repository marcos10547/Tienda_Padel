package com.example.tiendapadel.beans;

public class Usuario {
    int id_usuario;
    String email;
    String contraseña;

    public Usuario(int id_usuario, String email, String contraseña) {
        this.id_usuario = id_usuario;
        this.email = email;
        this.contraseña = contraseña;
    }

    public Usuario() {
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }


}
