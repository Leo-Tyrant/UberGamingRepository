package com.example.ubergaming2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UsersLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_login);

        Button btnInicio = (Button) findViewById(R.id.BotonInicio);
        Button btnLogin = (Button) findViewById(R.id.buttonLogin);
        Button btnMiCuenta = (Button) findViewById(R.id.BotonMiCuenta);
        btnInicio.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent listadoConsolas = new Intent(UsersLogin.this, ListadoConsolas.class);
                startActivity(listadoConsolas);
            }
        });



    }
}