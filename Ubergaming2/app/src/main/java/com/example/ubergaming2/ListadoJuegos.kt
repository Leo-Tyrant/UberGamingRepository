package com.example.ubergaming2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class ListadoJuegos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listado_juegos)


        //Boton RESIDENT EVIL que abre la Pagina de DETALLE del juego
        val botonDetalleJuego = findViewById<ImageButton>(R.id.image_residentevil)
        botonDetalleJuego.setOnClickListener{
            val intent = Intent(this, DetalleJuego::class.java)
            startActivity(intent)
        }

        //Boton NINTENDO que abre la Pagina de Listado de juegos
        val BotonListadoJuegos2 = findViewById<ImageButton>(R.id.image_zelda)
        BotonListadoJuegos2.setOnClickListener{
            val intent = Intent(this, ListadoJuegos::class.java)
            startActivity(intent)
        }

        //Boton MICROSOFT que abre la Pagina de Listado de juegos
        val BotonListadoJuegos3 = findViewById<ImageButton>(R.id.image_halo)
        BotonListadoJuegos3.setOnClickListener{
            val intent = Intent(this, ListadoJuegos::class.java)
            startActivity(intent)
        }







    }
}