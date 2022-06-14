package com.marcelo.examen1sqlite

import android.util.Log
import android.widget.Toast
import kotlinx.coroutines.*
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApirestAdapter {
    val sqLiteAdapter =SQLiteAdapter().retrofit

    fun getDatos():Deferred<ArrayList<DatosSql>>{

        var respuesta = ArrayList<DatosSql>()

       return CoroutineScope(Dispatchers.IO).async {
           // val response: <ArrayList<DatosSql>>
           val response = sqLiteAdapter.cargarDatos()
            if (response.isSuccessful) {
                 val datosResponse = response.body()
                if (datosResponse != null)
                    respuesta=datosResponse
                 }
            else {
                Log.e("Error", response.errorBody().toString())

            }
            respuesta
        }
    }
    internal inner class SQLiteAdapter() {

        val retrofit = crearRetrofit()

        private fun crearRetrofit(): ProveedorSersvicio {
            val url = "http://xusa.iesdoctorbalmis.info/examenjuego/"
            val retrofit = Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(ProveedorSersvicio::class.java)
        }


    }


}