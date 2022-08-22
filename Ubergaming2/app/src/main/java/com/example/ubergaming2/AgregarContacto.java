package com.example.ubergaming2;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class AgregarContacto extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregarcontacto);

        Button btnAgregar = (Button) findViewById(R.id.btnAgregar);
        EditText edtNombre = (EditText) findViewById(R.id.edtNombre);
        EditText edtTelefono = (EditText) findViewById(R.id.edtTelefono);
        EditText edtCorreo = (EditText) findViewById(R.id.edtCorreo);
        EditText edtDireccion = (EditText) findViewById(R.id.edtDireccion);

        btnAgregar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String elNombre = edtNombre.getText().toString();
                String elTelefono = edtTelefono.getText().toString();
                String elCorreo = edtCorreo.getText().toString();
                String elDireccion = edtDireccion.getText().toString();
                new AgregarContacto.TareaAgregarDatos().execute(edtNombre.getText().toString(), edtTelefono.getText().toString(), edtCorreo.getText().toString(), edtDireccion.getText().toString());
            }
        });
    }

    private class TareaAgregarDatos extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String elNombre = params[0];
            String elTelefono = params[1];
            String elCorreo = params[2];
            String elDireccion = params[3];
            String respuesta = "";

            try {
                URL url = new URL("https://www.toolboxcr.com/clientes/ulatina/writeContacto.php?nombre=" + elNombre + "&telefono=" + elTelefono + "&correo=" + elCorreo + "&direccion=" + elDireccion);
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
            Toast.makeText(AgregarContacto.this, "Registro agregado", Toast.LENGTH_LONG).show();
            ((EditText) findViewById(R.id.edtNombre)).setText("");
            ((EditText) findViewById(R.id.edtTelefono)).setText("");
            ((EditText) findViewById(R.id.edtCorreo)).setText("");
            ((EditText) findViewById(R.id.edtDireccion)).setText("");
        }

    }
}