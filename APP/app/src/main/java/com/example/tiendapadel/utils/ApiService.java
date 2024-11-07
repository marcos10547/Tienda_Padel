package com.example.tiendapadel.utils;

import com.example.tiendapadel.beans.Usuario;
import com.example.tiendapadel.login_users.data.LoginUserData;
import com.example.tiendapadel.register_users.data.RegisterUserData;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("/api/login")
    Call<LoginUserData> login(@Body Usuario user);
    @POST("/api/register")
    Call<RegisterUserData> register(@Body Usuario user);
}
