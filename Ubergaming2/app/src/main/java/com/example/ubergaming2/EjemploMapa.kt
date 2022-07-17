package com.example.ubergaming2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.ubergaming2.data.Weather

class EjemploMapa : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejemplo_mapa)
        val btnGetInfo = findViewById<Button>(R.id.btnGetInfo)
        btnGetInfo.setOnClickListener {
            getWeatherInfo()

        }
    }


    private fun getWeatherInfo() {
        val textViewResultado = findViewById<TextView>(R.id.textViewResultado);
        val textViewFeelsLike= findViewById<TextView>(R.id.textViewFeelsLike);
        val textViewPressure = findViewById<TextView>(R.id.textViewPressure);
        val textViewHumidy = findViewById<TextView>(R.id.textViewHumidy);
        val textViewInfoCiudad = findViewById<TextView>(R.id.textViewInfoCiudad);
        val city = findViewById<TextView>(R.id.txtCity).text
        val appId = "7c1a4068ee8bb69f2354862922c07fb1"
        // PROFE ID val appId ="5eeddeddbc5be9412d4d6db065d11f69"
        val url = "https://api.openweathermap.org/data/2.5/weather?q=${city}&appid=${appId}"
        val queue = Volley.newRequestQueue(this)
        val jsonObjectRequest = JsonObjectRequest (
            Request.Method.GET, url, null,
            {

                Toast.makeText(this, "Data Received", Toast.LENGTH_LONG).show()
                textViewResultado.text = "Data Received"

                val datos = it.getJSONObject("main")

          //   val weather = Weather()



            //    Log.d("EjemploMapa", it.toString())
            //    textViewInfoCiudad.text = datos.toString()
                val temp = datos.getDouble("temp")
                val feelsLike = datos.getDouble("feels_like")
                val pressure = datos.getInt("pressure")
                val humidity = datos.getInt("humidity")
                val minTemp = datos.getDouble("temp_min")
                val maxTemp = datos.getDouble("temp_max")

                var weather = Weather(temp, feelsLike, pressure, humidity, minTemp, maxTemp )
                textViewInfoCiudad.text = weather.temp.toString()
                textViewFeelsLike.text =  weather.feelsLike.toString()
                textViewPressure.text =  weather.pressure.toString()
                textViewHumidy.text =  weather.humidity.toString()


              //  textViewInfoCiudad.text = datos.getString("temp")

            }, {
             //   Log.d("EjemploMapa ERror", it.toString())
                Toast.makeText(this, "Request not found", Toast.LENGTH_LONG).show()
                textViewResultado.text = "Request not found"
            }

        )
        queue.add(jsonObjectRequest)
        Toast.makeText(this,"Getting Info", Toast.LENGTH_LONG).show()
        textViewResultado.text = "Getting info"
    }



}