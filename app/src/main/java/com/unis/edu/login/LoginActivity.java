package com.unis.edu.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;
import com.unis.edu.login.classes.MyPreferences;
import com.unis.edu.login.classes.UserPreferences;

public class LoginActivity extends AppCompatActivity {

    private EditText txtUsuario;
    private EditText txtSenha;
    private CheckBox chManterConectado;
    private Button btEntrar;
    private MyPreferences userPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userPreferences = new UserPreferences(getApplicationContext(), getText(R.string.sp_usuario).toString());

        initViews();
        setupEvents();
        verificarUsuario();
    }

    private void initViews() {
        txtUsuario = findViewById(R.id.txtUsuario);
        txtSenha = findViewById(R.id.txtSenha);
        chManterConectado = findViewById(R.id.chManterConectado);
        btEntrar = findViewById(R.id.btEntrar);
    }

    private void setupEvents() {
        btEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Entrar();
            }
        });
    }

    private void verificarUsuario() {
        if (userPreferences.existe()) {
            iniciarMain(true);
        }
    }

    private void Entrar() {
        if (!validarLogin()) {
            showSnackBar();
            return;
        }
        boolean manterConectado = chManterConectado.isChecked();
        userPreferences.setDados(txtUsuario.getText().toString());
        iniciarMain(manterConectado);
    }

    private boolean validarLogin() {
        String usuario = txtUsuario.getText().toString();
        String senha = txtSenha.getText().toString();

        if (usuario.equals("Thales") && senha.equals("thales")) {
            return true;
        } else {
            return false;
        }
    }

    private void iniciarMain(boolean stayLogged) {
        Intent mainIntent = new Intent(this, MainActivity.class);
        mainIntent.putExtra("MANTER_CONECTADO", stayLogged);
        startActivity(mainIntent);
    }

    private void showSnackBar() {
        View viewLogin = findViewById(android.R.id.content);
        Snackbar.make(viewLogin, R.string.usuario_ou_senha_invalidos, Snackbar.LENGTH_SHORT).show();
    }
}
