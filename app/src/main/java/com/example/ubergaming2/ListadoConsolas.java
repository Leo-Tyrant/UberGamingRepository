package com.example.ubergaming2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ListadoConsolas extends AppCompatActivity {
    public int colorFondo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String valor = "color";
       new ListadoConsolas.MostrarColor().execute("https://toolboxcr.com/clientes/ulatina/lrselConsolas.php?cadena=" + valor);
        setContentView(R.layout.activity_listado_consolas2);
        //Insatancia de los componentes de la actividad con el id 
        Button btnInicio = (Button) findViewById(R.id.BotonInicio);
        Button btnMicuenta = (Button) findViewById(R.id.BotonMiCuenta);
        Button btnLogin = (Button) findViewById(R.id.BotonLogin);
        Button btnTemp = (Button) findViewById(R.id.BotonTemperatura);
        Button btnColor = (Button) findViewById(R.id.buttonCambiarColor);
        ImageButton btnSony = (ImageButton) findViewById(R.id.image_sony);
        ImageButton btnNintendo = (ImageButton) findViewById(R.id.image_nintendo);
        ImageButton btnMicrosoft = (ImageButton) findViewById(R.id.image_microsoft);
        
        //Acciones por hacer cuando se le da click a un boton
        btnSony.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent res = new Intent(ListadoConsolas.this, ListadoJuegos.class);
                startActivity(res);
            }
        });
        btnNintendo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent res = new Intent(ListadoConsolas.this, ListadoJuegos.class);
                startActivity(res);
            }
        });
        btnMicrosoft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent res = new Intent(ListadoConsolas.this, ListadoJuegos.class);
                startActivity(res);
            }
        });
        btnInicio.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent inico = new Intent(ListadoConsolas.this, ListadoConsolas.class);
                startActivity(inico);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent login = new Intent(ListadoConsolas.this, UsersLogin.class);
                startActivity(login);
            }
        });
        btnTemp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent login = new Intent(ListadoConsolas.this, EjemploMapa.class);
                startActivity(login);
            }
        });

        btnColor.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent login = new Intent(ListadoConsolas.this, ColorPickerJava.class);
                startActivity(login);
            }
        });

        btnMicuenta.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent login = new Intent(ListadoConsolas.this, UserAccount.class);
                startActivity(login);
            }
        });


    }

     private class MostrarColor extends AsyncTask<String, Void, String> {
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
                String color = valores[1];
                int ValorColor = Integer.parseInt(valores[1]);
                ConstraintLayout li= (ConstraintLayout) findViewById(R.id.fondo);
                li.setBackgroundColor(ValorColor);
                Toast.makeText(ListadoConsolas.this, "Color en base de datos es:" + color, Toast.LENGTH_LONG).show();

            }
        }

    }
}