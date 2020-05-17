package com.unis.edu.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.unis.edu.login.classes.MyPreferences;
import com.unis.edu.login.classes.UserPreferences;

public class MainActivity extends AppCompatActivity {

    private TextView tvUsuario;
    private MyPreferences userPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getText(R.string.main_title).toString());

        initViews();

        userPreferences = new UserPreferences(getApplicationContext(), getText(R.string.sp_usuario).toString());
        atribuirUsuario();

        boolean manterConectado = getIntent().getBooleanExtra(getText(R.string.extra_manter_conectado).toString(), false);
        if (!manterConectado) {
            userPreferences.remove();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_sair:
                sair();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void initViews() {
        tvUsuario = findViewById(R.id.tvUsuario);
    }

    private void atribuirUsuario() {
        if (userPreferences.existe()) {
            tvUsuario.setText(userPreferences.getDados());
        } else {
            tvUsuario.setText(getText(R.string.sem_usuario).toString());
        }
    }

    private void sair() {
        userPreferences.remove();
        finishAndRemoveTask();
    }
}
