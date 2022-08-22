package com.example.ubergaming2

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val miTexto = findViewById<TextView>(R.id.id_saludo);


        //Boton que abre el TEST de LOGIN con GOOGLE
        val buttonLogin = findViewById<Button>(R.id.buttonLogin)
        buttonLogin.setOnClickListener{
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }

        //Boton que abre el Tab Activity-Pagina de Listado de juegos
        val myButton:Button = findViewById(R.id.btnSaludar);
        myButton.setOnClickListener {
            miTexto.text ="El Texto ha sido cambiado";
            Toast.makeText(this@MainActivity, "El boton ha sido presionado", Toast.LENGTH_LONG).show()
        }
        //Boton que abre el Tab Activity-Pagina de Listado de juegos
        val BotonListadoJuegos = findViewById<Button>(R.id.btnListadoJuegos)
        BotonListadoJuegos.setOnClickListener{
            val intent = Intent(this, ListadoJuegos::class.java)
             startActivity(intent)
        }

        //Boton que abre el Tab Activity-Pagina de Listado de consolas
        val BotonListadoConsolas = findViewById<Button>(R.id.btnListadoConsolas)
        BotonListadoConsolas.setOnClickListener{
            val intent = Intent(this, ListadoConsolas::class.java)
            startActivity(intent)
        }

        //Boton que abre el EJEMPLO DE API CIUDAD
        val BotonCiudad = findViewById<Button>(R.id.buttonCity)
        BotonCiudad.setOnClickListener{
            val intent = Intent(this, EjemploMapa::class.java)
            startActivity(intent)
        }

        //Boton que abre el Insertar Usuario en DB
        val botonInsertarUsuario = findViewById<Button>(R.id.buttonBuscarUsuario)
        botonInsertarUsuario.setOnClickListener{
            val intent = Intent(this, presentacion::class.java)
            startActivity(intent)
        }

        //Boton que abre el Buscar Usuario en DB

        val botonBuscarUsuario = findViewById<View>(R.id.buttonBuscarUsuario) as Button
        val edtTexto = findViewById<View>(R.id.editTextTextPersonName) as EditText

        botonBuscarUsuario.setOnClickListener(View.OnClickListener {
            val cadenaBusqueda: String = edtTexto.getText().toString()
            val laPresentacion = Intent(this@MainActivity, presentacion::class.java)
            laPresentacion.putExtra("cadena", cadenaBusqueda)
            startActivity(laPresentacion)
        })


        val numero = 0; //constante
        var nombreVariable = "Leo Roque"; //variable string o int
        var nombreVariableConTipo: String =
            "Leo Roque"; //para definir el tipo de dato, just in case
    }

    fun Saludar(): String {

        return "SALUDO";
    }

}




