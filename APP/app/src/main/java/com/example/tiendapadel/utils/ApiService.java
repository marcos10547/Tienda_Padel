package com.example.tiendapadel.utils;

import com.example.tiendapadel.beans.Usuario;
import com.example.tiendapadel.login_users.data.LoginUserData;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("login")
    Call<LoginUserData> login(@Body Usuario user);
}
