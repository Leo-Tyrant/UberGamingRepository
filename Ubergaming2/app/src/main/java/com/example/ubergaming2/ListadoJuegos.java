package com.example.ubergaming2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ListadoJuegos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_juegos);
        VariablesGlobales va = com.example.ubergaming2.VariablesGlobales.getInstance();
        int colorExtras = getIntent().getExtras().getInt("cadena");
        int consolaID = getIntent().getExtras().getInt("consola");
        ConstraintLayout li = (ConstraintLayout) findViewById(R.id.fondo);
        li.setBackgroundColor(colorExtras);
        Button btnInicio = (Button) findViewById(R.id.BotonInicio);
        Button btnMicuenta = (Button) findViewById(R.id.BotonMiCuenta);
        Button btnLogin = (Button) findViewById(R.id.BotonEditar);
        TextView dir = findViewById(R.id.direccionActual);
        if(va.getCurrentUser()!=null){
            btnLogin.setEnabled(false);
            btnLogin.setText(va.getCurrentUser());
            dir.setText(va.getDireccion());
        }

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
        new ListadoJuegos.MostrarJuego().execute("https://toolboxcr.com/clientes/ulatina/lrselJuegos.php?cadena=" + consolaID);
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
                Button btnJuego = new Button(getApplicationContext());
                btnJuego.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                String idJuego = valores[0];
                String nombreJuego = valores[2];
                btnJuego.setText(valores[2]);
                btnJuego.setId(Integer.parseInt(valores[0]));
                ((LinearLayout)findViewById(R.id.resultados)).addView(btnJuego);
                btnJuego.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        Intent login = new Intent(ListadoJuegos.this, DetalleJuego.class);
                        login.putExtra("idJuego", idJuego);
                        login.putExtra("nombreJuego", nombreJuego);

                        startActivity(login);
                    }
                });
            }
        }

    }

}