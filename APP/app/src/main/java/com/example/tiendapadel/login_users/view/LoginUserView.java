package com.example.tiendapadel.login_users.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        presenter = new LoginUserPresenter(this);
        initComponents();
    }

    void initComponents() {
        email = findViewById(R.id.email);
        contraseña = findViewById(R.id.contraseña);
        Login = findViewById(R.id.loginBtn);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailStr = email.getText().toString().trim();
                String contraseñaStr = contraseña.getText().toString().trim();
                if (!emailStr.isEmpty() && !contraseñaStr.isEmpty()) {
                    Usuario user = new Usuario(0, emailStr, contraseñaStr);
                    presenter.loginAction(user);
                } else {
                    Toast.makeText(LoginUserView.this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void successLogin(Usuario user) {
        runOnUiThread(() -> {
            Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public void failureLogin(String messageError) {
        runOnUiThread(() -> {
            Toast.makeText(this, messageError, Toast.LENGTH_SHORT).show();
        });
    }
}