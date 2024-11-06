package com.example.tiendapadel.login_users;

import com.example.tiendapadel.beans.Usuario;

public interface Login_Contract {
    interface view{
        void successLogin(Usuario user);
        void failureLogin(String messageError);
    }

    interface presenter{
        void loginAction(Usuario user);
    }

    interface model{
        interface OnLoginUserListener{
            void onFinished(Usuario user);
            void onFailure(String messageError);
        }

        void loginUserAPI(Usuario user, OnLoginUserListener onLoginUserListener);
    }
}
