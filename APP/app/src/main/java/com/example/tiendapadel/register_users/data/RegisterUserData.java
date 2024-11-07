package com.example.tiendapadel.register_users.data;

import com.example.tiendapadel.beans.Usuario;

public class RegisterUserData {
    private boolean success;
    private String message;
    private Usuario usuario;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}