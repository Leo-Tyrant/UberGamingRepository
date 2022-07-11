package com.example.ubergaming2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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







    }
}