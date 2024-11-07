package com.example.tiendapadel.register_users;

import com.example.tiendapadel.beans.Usuario;

public interface Register_Contract {
    interface view {
        void successRegister(Usuario user);
        void failureRegister(String messageError);
    }

    interface presenter {
        void registerAction(Usuario user);
    }

    interface model {
        interface OnRegisterUserListener {
            void onFinished(Usuario user);
            void onFailure(String messageError);
        }

        void registerUserAPI(Usuario user, OnRegisterUserListener onRegisterUserListener);
    }
}