package com.example.tiendapadel.register_users.model;

import android.util.Log;

import com.example.tiendapadel.beans.Usuario;
import com.example.tiendapadel.register_users.Register_Contract;
import com.example.tiendapadel.register_users.data.RegisterUserData;
import com.example.tiendapadel.utils.ApiService;
import com.example.tiendapadel.utils.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterUserModel implements Register_Contract.model {
    private static final String base_url = "http://192.168.104.77:3000/";

    @Override
    public void registerUserAPI(Usuario user, OnRegisterUserListener onRegisterUserListener) {
        ApiService apiService = RetrofitClient.getClient(base_url).create(ApiService.class);

        Log.d("RegisterUserModel", "Attempting register with email: " + user.getEmail());

        Call<RegisterUserData> call = apiService.register(user);

        call.enqueue(new Callback<RegisterUserData>() {
            @Override
            public void onResponse(Call<RegisterUserData> call, Response<RegisterUserData> response) {
                if (response.isSuccessful()) {
                    RegisterUserData myData = response.body();
                    if (myData != null && myData.isSuccess()) {
                        Usuario registeredUser = myData.getUsuario() != null ? myData.getUsuario() : user;
                        onRegisterUserListener.onFinished(registeredUser);
                        Log.d("RegisterUserModel", "Register successful for user: " + registeredUser.getEmail());
                    } else {
                        String errorMessage = myData != null ? myData.getMessage() : "Error desconocido";
                        onRegisterUserListener.onFailure(errorMessage);
                        Log.e("RegisterUserModel", "Register failed: " + errorMessage);
                    }
                } else {
                    onRegisterUserListener.onFailure("Error en el registro. Código: " + response.code());
                    Log.e("RegisterUserModel", "Register failed with response code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<RegisterUserData> call, Throwable t) {
                handleNetworkError(t, onRegisterUserListener);
            }
        });
    }

    private void handleNetworkError(Throwable t, OnRegisterUserListener listener) {
        String errorMessage = "Error de conexión: " + t.getMessage();
        listener.onFailure(errorMessage);
        Log.e("RegisterUserModel", errorMessage, t);
    }
}