package com.example.tiendapadel.register_users.presenter;

import com.example.tiendapadel.beans.Usuario;
import com.example.tiendapadel.register_users.Register_Contract;
import com.example.tiendapadel.register_users.model.RegisterUserModel;

public class RegisterUserPresenter implements Register_Contract.model.OnRegisterUserListener, Register_Contract.presenter {
    private Register_Contract.view view;
    private Register_Contract.model model;

    public RegisterUserPresenter(Register_Contract.view view) {
        this.view = view;
        model = new RegisterUserModel();
    }

    @Override
    public void onFinished(Usuario user) {
        view.successRegister(user);
    }

    @Override
    public void onFailure(String messageError) {
        view.failureRegister(messageError);
    }

    @Override
    public void registerAction(Usuario user) {
        if (user != null) {
            model.registerUserAPI(user, this);
        } else {
            view.failureRegister("Campos vacíos");
        }
    }
}