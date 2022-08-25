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
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class UserAccount extends AppCompatActivity {
    TextView nombre ;
    TextView correo ;
    TextView direccion;
    TextView telefono ;
    TextView nombreJuego;
    TextView precio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_account);
        VariablesGlobales va = com.example.ubergaming2.VariablesGlobales.getInstance();
        ConstraintLayout li = (ConstraintLayout) findViewById(R.id.fondo);
        li.setBackgroundColor(va.getColor());
        //Boton que manda a Inicio
        Button btnInicio = (Button) findViewById(R.id.BotonInicio);
        Button btnCuenta = findViewById(R.id.BotonMiCuenta);
        TextView dir = findViewById(R.id.direccionActual);
        Button btnLogin = findViewById(R.id.buttonLogin);
        Button btnActulizar = findViewById(R.id.buttonActualizar);
        nombre = findViewById(R.id.txtNombre);
        correo = findViewById(R.id.txtEmail);
        direccion = findViewById(R.id.txtDireccion);
        telefono = findViewById(R.id.txtTel);
        nombreJuego = findViewById(R.id.txtNombreJuego);
        precio = findViewById(R.id.txtPrecio);
        if(va.getCurrentUser()!=null){
            dir.setText(va.getDireccion());
        }
        //Trea la info de clientes de la base de datos que esta hosteada en el servidor del profesor
        //new LeerCuentas().execute("https://toolboxcr.com/clientes/ulatina/lrselClientes.php?cadena=" + valor);

        btnInicio.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent laPresentacion = new Intent(UserAccount.this, ListadoConsolas.class);
                startActivity(laPresentacion);}
        });
        btnCuenta.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent laPresentacion = new Intent(UserAccount.this, UserAccount.class);
                startActivity(laPresentacion);}
        });
        btnActulizar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent laPresentacion = new Intent(UserAccount.this, ListadoConsolas.class);
                startActivity(laPresentacion);}
        });
        new UserAccount.MostrarCuenta().execute("https://toolboxcr.com/clientes/ulatina/lrselClientes.php?cadena="+va.getCurrentUser());
        new UserAccount.MostrarInfo().execute("https://toolboxcr.com/clientes/ulatina/lrselFacturas.php?cadena=" +va.getCurrentUser());

    }
    private class MostrarCuenta extends AsyncTask<String, Void, String> {
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
                nombre.setText(valores[0]);
                correo.setText(valores[2]);
                direccion.setText(valores[3]);
                telefono.setText(valores[1]);
            }
        }
    }
    private class MostrarInfo extends AsyncTask<String, Void, String> {
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
                nombreJuego.setText(valores[1]);
                precio.setText(valores[2]);
            }
        }
    }
}