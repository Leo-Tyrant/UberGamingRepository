package com.example.ubergaming2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class ListadoConsolas : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listado_consolas2)


        //Boton SONY que abre la Pagina de Listado de juegos
        val BotonListadoJuegos = findViewById<ImageButton>(R.id.image_sony)
        BotonListadoJuegos.setOnClickListener{
            val intent = Intent(this, ListadoJuegos::class.java)
            startActivity(intent)
        }

        //Boton NINTENDO que abre la Pagina de Listado de juegos
        val BotonListadoJuegos2 = findViewById<ImageButton>(R.id.image_nintendo)
        BotonListadoJuegos2.setOnClickListener{
            val intent = Intent(this, ListadoJuegos::class.java)
            startActivity(intent)
        }

        //Boton MICROSOFT que abre la Pagina de Listado de juegos
        val BotonListadoJuegos3 = findViewById<ImageButton>(R.id.image_microsoft)
        BotonListadoJuegos3.setOnClickListener{
            val intent = Intent(this, ListadoJuegos::class.java)
            startActivity(intent)
        }

        //Boton que abre el EJEMPLO DE API CIUDAD
        val BotonTemperatura = findViewById<Button>(R.id.BotonTemperatura)
        BotonTemperatura.setOnClickListener{
            val intent = Intent(this, EjemploMapa::class.java)
            startActivity(intent)
        }

        //Boton que manda al INICIO (Listado de Consolas)
        val BotonInicio = findViewById<Button>(R.id.BotonInicio)
        BotonInicio.setOnClickListener{
            val intent = Intent(this, ListadoConsolas::class.java)
            startActivity(intent)
        }

        //Boton que manda al LOGIN
        val BotonEditar = findViewById<Button>(R.id.BotonEditar)
        BotonEditar.setOnClickListener{
            val intent = Intent(this, UsersLogin::class.java)
            startActivity(intent)
        }







    }
}