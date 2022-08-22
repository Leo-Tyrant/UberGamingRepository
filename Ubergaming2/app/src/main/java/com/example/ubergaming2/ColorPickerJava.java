package com.example.ubergaming2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import yuku.ambilwarna.AmbilWarnaDialog;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ColorPickerJava extends AppCompatActivity {

    private TextView gfgTextView;

    private Button mSetColorButton, mPickColorButton;

    private View mColorPreview;

    private int mDefaultColor;

    private int variableColorDeFondo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_picker_java);

        gfgTextView = findViewById(R.id.heading);

        mPickColorButton = findViewById(R.id.pick_color_button);
        mSetColorButton = findViewById(R.id.set_color_button);

        mColorPreview = findViewById(R.id.preview_selected_color);

        mDefaultColor = 0;

        mPickColorButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                         openColorPickerDialogue();
                    }
                });

             mSetColorButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                          gfgTextView.setTextColor(mDefaultColor);
                    }
                });


        // Cambia el fondo de color
        Button fondo = (Button) findViewById(R.id.buttonBackground);
        fondo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                LinearLayout li=(LinearLayout)findViewById(R.id.bgcolor);
                // Prueba estatica con color ROJO
                //  li.setBackgroundColor(Color.RED);


                //Asigna el color previamente seleccionado al fondo
                li.setBackgroundColor(mDefaultColor);

                //guarda en variable el color de fondo escogido
                 variableColorDeFondo = mDefaultColor;
                String color = (String.valueOf(variableColorDeFondo));
                new ColorPickerJava.ColorAgregar().execute(color);

            }
        });


        // Se regresa al inicio
        Button buttonInicio = (Button)findViewById(R.id.buttonInicio);

        buttonInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ColorPickerJava.this, ListadoConsolas.class));
            }
        });

    }

    public void openColorPickerDialogue() {
        final AmbilWarnaDialog colorPickerDialogue = new AmbilWarnaDialog(this, mDefaultColor,
                new AmbilWarnaDialog.OnAmbilWarnaListener() {
                    @Override
                    public void onCancel(AmbilWarnaDialog dialog) {
                    }

                    @Override
                    public void onOk(AmbilWarnaDialog dialog, int color) {
                       mDefaultColor = color;
                        mColorPreview.setBackgroundColor(mDefaultColor);
                    }
                });
        colorPickerDialogue.show();
    }
    private class ColorAgregar extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String elcolor = params[0];
            String respuesta = "";

            try {
                URL url = new URL("https://toolboxcr.com/clientes/ulatina/lraddConsola.php?id=4&nombre= color&descripcion="+elcolor+"&rutaimagen=yo" );
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
            Toast.makeText(ColorPickerJava.this, "Color registrado", Toast.LENGTH_LONG).show();
        }

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

            String contactos[] = respuesta.split(";");
            for (int i=0; i<contactos.length; i++) {

                String valores[] = contactos[i].split(",");

                //Button btnPersona = new Button(getApplicationContext());
                //btnPersona.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                String color = valores[0];
                //variableColorDeFondo = cast(color);
            }
        }

    }

}