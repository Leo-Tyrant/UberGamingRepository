package com.example.ubergaming2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val miTexto = findViewById<TextView>(R.id.id_saludo);

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



        val numero = 0; //constante
        var nombreVariable = "Leo Roque"; //variable string o int
        var nombreVariableConTipo: String =
            "Leo Roque"; //para definir el tipo de dato, just in case
    }

    fun Saludar(): String {

        return "SALUDO";
    }

}




