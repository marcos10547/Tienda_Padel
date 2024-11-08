package com.example.tiendapadel.register_users.data;

import com.example.tiendapadel.beans.Usuario;
import com.google.gson.annotations.SerializedName;

public class RegisterUserData {
    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    @SerializedName("usuario")
    private Usuario usuario;

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public Usuario getUsuario() {
        return usuario;
    }
}