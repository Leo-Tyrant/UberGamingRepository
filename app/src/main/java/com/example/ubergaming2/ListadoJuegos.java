package com.example.ubergaming2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ListadoJuegos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_juegos);
        Button btnInicio = (Button) findViewById(R.id.BotonInicio);
        Button btnMicuenta = (Button) findViewById(R.id.BotonMiCuenta);
        Button btnLogin = (Button) findViewById(R.id.BotonEditar);
        Button btnRes = (Button) findViewById(R.id.image_residentevil);
        Button btnzelda = (Button) findViewById(R.id.image_zelda);
        Button btnHalo = (Button) findViewById(R.id.image_halo);
        btnRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent res = new Intent(ListadoJuegos.this, DetalleJuego.class);
                startActivity(res);
            }
        });
        btnInicio.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent inico = new Intent(ListadoJuegos.this, ListadoConsolas.class);
                startActivity(inico);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent login = new Intent(ListadoJuegos.this, UsersLogin.class);
                startActivity(login);
            }
        });
        btnMicuenta.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent login = new Intent(ListadoJuegos.this, UserAccount.class);
                startActivity(login);
            }
        });
        String valor = "Juego";
        new ListadoJuegos.MostrarJuego().execute("https://toolboxcr.com/clientes/ulatina/lrselJuegos.php?cadena=" + valor);
    }
    private class MostrarJuego extends AsyncTask<String, Void, String> {
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

            String contactos[] = respuesta.split(";");
            for (int i=0; i<contactos.length; i++) {

                String valores[] = contactos[i].split(",");

                //Button btnPersona = new Button(getApplicationContext());
                //btnPersona.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                Button btnRes = (Button) findViewById(R.id.image_residentevil);
                btnRes.setText(valores[0] + " (" + valores[1] + ")"+ " (" + valores[2] + ")"+ " (" + valores[3] + ")" );
            }
        }

    }

}