package com.example.ubergaming2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DetalleJuego extends AppCompatActivity {

   Button btnInicio;
   Button btnMicuenta;
   TextView txtTituloJuego;
   EditText txtDescripcionJuego;
   EditText txtPrecioNumero;
   String nombreJuego;
   String desc;
   String prec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_juego);
        VariablesGlobales va = com.example.ubergaming2.VariablesGlobales.getInstance();
        ConstraintLayout li = (ConstraintLayout) findViewById(R.id.fondo);
        li.setBackgroundColor(va.getColor());
        String idJuego = getIntent().getExtras().getString("idJuego");
        nombreJuego = getIntent().getExtras().getString("nombreJuego");
        btnInicio =  findViewById(R.id.BotonInicio);
        btnMicuenta = findViewById(R.id.BotonMiCuenta);
        txtTituloJuego = findViewById(R.id.txtTitulo);
        txtDescripcionJuego = findViewById(R.id.descripcionJuego);
        txtPrecioNumero = findViewById(R.id.PrecioNumero);
        Button btnAgregar = findViewById(R.id.buttonAgregar);
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
                Intent inico = new Intent(DetalleJuego.this, ListadoConsolas.class);
                startActivity(inico);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent login = new Intent(DetalleJuego.this, UsersLogin.class);
                startActivity(login);
            }
        });
        btnAgregar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(va.getCurrentUser()!=null) {
                    new DetalleJuego.AgregarJuego().execute(va.getCurrentUser(), nombreJuego, prec, va.getDireccion());
                    startActivity(new Intent(DetalleJuego.this, ListadoConsolas.class));
                }else{
                    AlertDialog.Builder dialogo1 = new AlertDialog.Builder(DetalleJuego.this);
                    dialogo1.setTitle("Login");
                    dialogo1.setMessage("Es necesario hacer el login primero,¿Desea ir a Login?");
                    dialogo1.setCancelable(false);
                    dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogo1, int id) {

                        }
                    });
                    dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogo1, int id) {
                            startActivity(new Intent(DetalleJuego.this, UsersLogin.class));
                        }
                    });
                    dialogo1.show();
                }
            }
        });
        btnMicuenta.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent login = new Intent(DetalleJuego.this, UserAccount.class);
                startActivity(login);
            }
        });
         new DetalleJuego.MostrarJuego().execute("https://toolboxcr.com/clientes/ulatina/lrselJuegos.php?cadena=" + idJuego);
    }

    private class AgregarJuego extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String nomCliente = params[0];
            String nomJuego = params[1];
            String precio = params[2];
            String dir = params[3];
            String respuesta = "";

            try {
                URL url = new URL("https://toolboxcr.com/clientes/ulatina/lraddFactura.php?id=''&nombrecliente="+nomCliente+"&nombrejuego="+nomJuego+"&precio="+precio+"&direccion="+dir);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                int status = conn.getResponseCode();
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "1";
        }

        @Override
        protected void onPostExecute(String respuesta) {
            Toast.makeText(DetalleJuego.this, "Juego Agregado", Toast.LENGTH_LONG).show();
        }
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
            String juegos[] = respuesta.split(";");
            for (int i=0; i<juegos.length; i++) {
                String valores[] = juegos[i].split(",");
                String nom = valores[2];
                desc = valores[1];
                prec = valores[3];
                if(nombreJuego.equals(valores[2])){
                    txtTituloJuego.setText(nom);
                    txtDescripcionJuego.setText(desc);
                    txtPrecioNumero.setText(prec);
               }
            }
        }

    }
}