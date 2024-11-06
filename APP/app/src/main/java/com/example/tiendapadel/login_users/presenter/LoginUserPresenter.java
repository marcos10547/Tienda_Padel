package com.example.tiendapadel.login_users.presenter;

import com.example.tiendapadel.beans.Usuario;
import com.example.tiendapadel.login_users.Login_Contract;
import com.example.tiendapadel.login_users.model.LoginUserModel;

public class LoginUserPresenter implements Login_Contract.model.OnLoginUserListener, Login_Contract.presenter {
    private Login_Contract.view view;
    private Login_Contract.model model;

    public LoginUserPresenter(Login_Contract.view view){
        this.view = view;
        model = new LoginUserModel();
    }

    @Override
    public void onFinished(Usuario user) {
        view.successLogin(user);
    }

    @Override
    public void onFailure(String messageError) {
        view.failureLogin(messageError);
    }

    @Override
    public void loginAction(Usuario user) {
        if(user != null){
            model.loginUserAPI(user, this);
        } else {
            view.failureLogin("Campos vacios");
        }
    }
}
