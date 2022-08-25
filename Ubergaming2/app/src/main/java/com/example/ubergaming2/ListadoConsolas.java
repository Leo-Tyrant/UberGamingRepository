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
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ListadoConsolas extends AppCompatActivity {
    VariablesGlobales va = com.example.ubergaming2.VariablesGlobales.getInstance();
    public int variableColorDeFondo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String valor = "color";
        new ListadoConsolas.TraeColor().execute("https://toolboxcr.com/clientes/ulatina/lrselConsolas.php?cadena=" + valor);
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
                Intent ListJuegos = new Intent(ListadoConsolas.this, ListadoJuegos.class);
                ListJuegos.putExtra("cadena", variableColorDeFondo);
                ListJuegos.putExtra("consola", 1);
                startActivity(ListJuegos);
            }
        });
        btnNintendo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ListJuegos = new Intent(ListadoConsolas.this, ListadoJuegos.class);
                ListJuegos.putExtra("cadena", variableColorDeFondo);
                ListJuegos.putExtra("consola", 2);
                startActivity(ListJuegos);
            }
        });
        btnMicrosoft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ListJuegos = new Intent(ListadoConsolas.this, ListadoJuegos.class);
                ListJuegos.putExtra("cadena", variableColorDeFondo);
                ListJuegos.putExtra("consola", 3);
                startActivity(ListJuegos);
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
                login.putExtra("cadena", variableColorDeFondo);
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
                Intent color = new Intent(ListadoConsolas.this, ColorPickerJava.class);
                color.putExtra("cadena", variableColorDeFondo);
                startActivity(color);
            }
        });

        btnMicuenta.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(va.getCurrentUser()!=null) {
                    Intent micuenta = new Intent(ListadoConsolas.this, UserAccount.class);
                    startActivity(micuenta);
                }else{
                    AlertDialog.Builder dialogo1 = new AlertDialog.Builder(ListadoConsolas.this);
                    dialogo1.setTitle("Login");
                    dialogo1.setMessage("Es necesario hacer el login primero,¿Desea ir a Login?");
                    dialogo1.setCancelable(false);
                    dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogo1, int id) {
                        }
                    });
                    dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogo1, int id) {
                            startActivity(new Intent(ListadoConsolas.this, UsersLogin.class));
                        }
                    });
                    dialogo1.show();
                }
            }
        });

    }
    @Override
    public void onResume(){
        super.onResume();
        VariablesGlobales va = com.example.ubergaming2.VariablesGlobales.getInstance();
        if(va.getCurrentUser()!=null){
            Button user = findViewById(R.id.BotonLogin);
            TextView dir = findViewById(R.id.direccionActual);
            user.setEnabled(false);
            user.setText(va.getCurrentUser());
            dir.setText(va.getDireccion());
        }
    }


    private class TraeColor extends AsyncTask<String, Void, String> {
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
                variableColorDeFondo = Integer.parseInt(valores[1]);
                ConstraintLayout li = (ConstraintLayout) findViewById(R.id.fondo);
                li.setBackgroundColor(variableColorDeFondo);

                va.setColor(variableColorDeFondo);
            }
        }

    }
}