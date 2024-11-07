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
    private static final String base_url = "http://172.29.80.1:3000/";

    @Override
    public void registerUserAPI(Usuario user, OnRegisterUserListener onRegisterUserListener) {
        ApiService apiService = RetrofitClient.getClient(base_url).create(ApiService.class);

        Log.d("RegisterUserModel", "Attempting register with email: " + user.getEmail());
        Log.d("RegisterUserModel", "Datos del usuario antes del registro: " + user.toString());

        Call<RegisterUserData> call = apiService.register(user);

        call.enqueue(new Callback<RegisterUserData>() {
            @Override
            public void onResponse(Call<RegisterUserData> call, Response<RegisterUserData> response) {
                if (response.isSuccessful()) {
                    RegisterUserData myData = response.body();

                    // Verifica que la respuesta y el campo `success` existan
                    if (myData != null && myData.isSuccess()) {
                        Usuario registeredUser = myData.getUsuario() != null ? myData.getUsuario() : user;
                        onRegisterUserListener.onFinished(registeredUser);
                        Log.d("RegisterUserModel", "Registro exitoso para el usuario: " + registeredUser.getEmail());
                    } else {
                        // En caso de que el registro falle en el servidor, captura el mensaje de error
                        String errorMessage = myData != null ? myData.getMessage() : "Error desconocido en la respuesta";
                        onRegisterUserListener.onFailure(errorMessage);
                        Log.e("RegisterUserModel", "Falló el registro: " + errorMessage);
                    }
                } else {
                    // En caso de respuesta no exitosa, captura el mensaje detallado de error
                    try {
                        String errorResponse = response.errorBody() != null ? response.errorBody().string() : "Respuesta de error desconocida";
                        Log.e("RegisterUserModel", "Error en el registro. Código: " + response.code() + ", Detalle: " + errorResponse);
                        onRegisterUserListener.onFailure("Error en el registro. Código: " + response.code() + ", Detalle: " + errorResponse);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
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
