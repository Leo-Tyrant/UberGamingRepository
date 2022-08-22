package com.example.ubergaming2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class UserAccount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_user_account);
        //Boton que manda a Inicio
        Button btnInicio = (Button) findViewById(R.id.BotonInicio);

        //Trea la info de clientes de la base de datos que esta hosteada en el servidor del profesor
        //new LeerCuentas().execute("https://toolboxcr.com/clientes/ulatina/lrselClientes.php?cadena=" + valor);

        btnInicio.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String cadenaBusqueda = String.valueOf(btnInicio.getText());
                Intent laPresentacion = new Intent(UserAccount.this, ListadoConsolas.class);
                laPresentacion.putExtra("cadena", cadenaBusqueda);
                startActivity(laPresentacion);}
        });

    }
    private class LeerCuentas extends AsyncTask<String, Void, String> {
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

                Button btnPersona = new Button(getApplicationContext());
                btnPersona.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                btnPersona.setText(valores[1] + " (" + valores[2] + ")"+ " (" + valores[3] + ")"+ " (" + valores[4] + ")" );
                btnPersona.setId(Integer.parseInt(valores[0]));
                ((LinearLayout)findViewById(R.id.resultados)).addView(btnPersona);

                btnPersona.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", valores[2], null));
                        startActivity(intent);
                    }
                });
            }
        }

    }
}