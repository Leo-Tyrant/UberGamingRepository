package com.example.ubergaming2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class UsersLogin extends AppCompatActivity {
    EditText txtNombre;
    EditText txtCorreo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_login);
        VariablesGlobales va = com.example.ubergaming2.VariablesGlobales.getInstance();
        ConstraintLayout li = (ConstraintLayout) findViewById(R.id.fondo);
        li.setBackgroundColor(va.getColor());
        TextView dir = findViewById(R.id.direccionActual);
        Button btnInicio = (Button) findViewById(R.id.BotonInicio);
        Button btnIngresar = (Button) findViewById(R.id.ButtonIngresar);
        if(va.getCurrentUser()!=null){
            dir.setText(va.getDireccion());
        }

        btnInicio.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent listadoConsolas = new Intent(UsersLogin.this, ListadoConsolas.class);
                startActivity(listadoConsolas);
            }
        });

        btnIngresar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                txtNombre = (EditText) findViewById(R.id.EditNombre);
                txtCorreo = (EditText) findViewById(R.id.txtEmail);
                if(!txtNombre.getText().toString().isEmpty() && !txtCorreo.getText().toString().isEmpty()){
                    new UsersLogin.TraerClientes().execute("https://toolboxcr.com/clientes/ulatina/lrselClientes.php?cadena="+txtNombre.getText().toString());
                }else{
                    Toast.makeText(UsersLogin.this, "Debe llenar los espacios", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
    //Clase que trae todos los valores de los clientes
    private class TraerClientes extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String URL = params[0];
            String respuesta = "";

            try {
                java.net.URL url = new URL(URL);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                //conn.setRequestMethod("GET");
                int status = conn.getResponseCode();
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                while (!respuesta.contains("null")) {
                    respuesta += reader.readLine();
                }
                respuesta = respuesta.replace("null", "");

            } catch (Exception e) {
                e.printStackTrace();
            }
            return respuesta;
        }

        @Override
        protected void onPostExecute(String respuesta) {

            String colores[] = respuesta.split(";");
            for (int i = 0; i < colores.length; i++) {
                String valores[] = colores[i].split(",");
                String n = txtNombre.getText().toString();
                String c = txtCorreo.getText().toString();
                if(n.equals(valores[0]) && c.equals(valores[2]) ){
                    Intent LoginInicio = new Intent(UsersLogin.this, ListadoConsolas.class);
                    Toast.makeText(UsersLogin.this, "Login exitoso", Toast.LENGTH_LONG).show();
                    VariablesGlobales va = com.example.ubergaming2.VariablesGlobales.getInstance();
                    va.setCurrentUser(valores[0]);
                    va.setDireccion(valores[3]);
                    startActivity(LoginInicio);
                }else{
                    Toast.makeText(UsersLogin.this, "Nombre o correo incorrectos", Toast.LENGTH_LONG).show();
                    txtNombre.setText("");
                    txtCorreo.setText("");
                }
            }
        }
    }
}