package com.example.tiendapadel.login_users.model;


import android.util.Log;

import com.example.tiendapadel.beans.Usuario;
import com.example.tiendapadel.login_users.Login_Contract;
import com.example.tiendapadel.login_users.data.LoginUserData;
import com.example.tiendapadel.utils.ApiService;
import com.example.tiendapadel.utils.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginUserModel implements Login_Contract.model {
    private static final String base_url = "http://172.29.240.1:3000/";

    @Override
    public void loginUserAPI(Usuario user, OnLoginUserListener onLoginUserListener) {
        ApiService apiService = RetrofitClient.getClient(base_url).create(ApiService.class);

        Log.d("LoginUserModel", "Attempting login with email: " + user.getEmail());

        Usuario userL = new Usuario();
        userL.setEmail(user.getEmail());
        userL.setContraseña(user.getContraseña());

        Call<LoginUserData> call = apiService.login(userL);

        call.enqueue(new Callback<LoginUserData>() {
            @Override
            public void onResponse(Call<LoginUserData> call, Response<LoginUserData> response) {
                if (response.isSuccessful()) {
                    LoginUserData myData = response.body();
                    if (myData != null && myData.getUsuario() != null) {
                        onLoginUserListener.onFinished(userL);
                        Log.d("LoginUserModel", "Login successful for user: " + userL.getEmail());
                    } else {
                        onLoginUserListener.onFailure("Usuario no encontrado o datos inválidos.");
                        Log.e("LoginUserModel", "Login failed: No se ha encontrado el usuario.");
                    }
                } else {
                    // Manejar otros códigos de error HTTP
                    onLoginUserListener.onFailure("Error en la autenticación. Código: " + response.code());
                    Log.e("LoginUserModel", "Login failed with response code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<LoginUserData> call, Throwable t) {
                handleNetworkError(t, onLoginUserListener);
            }
        });
    }

    private void handleNetworkError(Throwable t, OnLoginUserListener listener) {
        String errorMessage = "Error de conexión: " + t.getMessage();
        listener.onFailure(errorMessage);
        Log.e("LoginUserModel", errorMessage, t);
    }
}