package com.example.tiendapadel.login_users.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tiendapadel.R;
import com.example.tiendapadel.beans.Usuario;
import com.example.tiendapadel.login_users.Login_Contract;
import com.example.tiendapadel.login_users.presenter.LoginUserPresenter;

public class LoginUserView extends AppCompatActivity implements Login_Contract.view {
    EditText email;
    EditText contraseña;
    Button Login;
    private LoginUserPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        presenter = new LoginUserPresenter(this);
//        Toast.makeText(this, "Llego", Toast.LENGTH_LONG).show();
        initComponents();

    }

    void initComponents(){
        email = findViewById(R.id.email);
        contraseña = findViewById(R.id.contraseña);
        Login = findViewById(R.id.loginBtn);
        Login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Usuario user = new Usuario(1, email.getText().toString(), contraseña.getText().toString());
                presenter.loginAction(user);
                System.out.println(user.getEmail());
                System.out.println(user.getContraseña());
            }
        });
    }

    @Override
    public void successLogin(Usuario user) {

    }

    @Override
    public void failureLogin(String messageError) {

    }

}
