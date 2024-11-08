package com.example.tiendapadel.register_users.view;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tiendapadel.R;
import com.example.tiendapadel.beans.Usuario;
import com.example.tiendapadel.register_users.Register_Contract;
import com.example.tiendapadel.register_users.presenter.RegisterUserPresenter;

public class RegisterUserView extends AppCompatActivity implements Register_Contract.view {
    EditText nombre;
    EditText email;
    EditText contraseña;
    Spinner spinnerRol;
    Button Register;
    private RegisterUserPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        presenter = new RegisterUserPresenter(this);
        initComponents();
    }

    void initComponents() {
        nombre = findViewById(R.id.nombreRegister);
        email = findViewById(R.id.emailRegister);
        contraseña = findViewById(R.id.contraseñaRegister);
        spinnerRol = findViewById(R.id.spinnerRol);
        Register = findViewById(R.id.registerBtn);

        // Configurar el Spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.roles_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRol.setAdapter(adapter);

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombreStr = nombre.getText().toString().trim();
                String emailStr = email.getText().toString().trim();
                String contraseñaStr = contraseña.getText().toString().trim();
                String rolStr = spinnerRol.getSelectedItem().toString();
                if (!nombreStr.isEmpty() && !emailStr.isEmpty() && !contraseñaStr.isEmpty()) {
                    Usuario user = new Usuario(0, nombreStr, emailStr, contraseñaStr, rolStr);
                    presenter.registerAction(user);
                } else {
                    Toast.makeText(RegisterUserView.this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void successRegister(Usuario user) {
        runOnUiThread(() -> {
            Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show();
            finish(); // Volver a la pantalla de login
        });
    }

    @Override
    public void failureRegister(String messageError) {
        runOnUiThread(() -> {
            Toast.makeText(this, messageError, Toast.LENGTH_SHORT).show();
        });
    }
}