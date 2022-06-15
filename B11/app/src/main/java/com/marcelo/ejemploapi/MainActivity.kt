package com.marcelo.ejemploapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.marcelo.ejemploapi.api.ProveedorSersvicio
import com.marcelo.ejemploapi.api.Usuarios
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var usuarios = ArrayList<Usuarios>()
        CoroutineScope(Dispatchers.Main).launch {
            usuarios = getUsuario().await()

        }
        for (u in usuarios)
            println(usuarios.toString())
        println(usuarios.isEmpty())

    }


    private fun crearRetrofit(): ProveedorSersvicio {
//val url = "http://10.0.2.2/usuarios/" //para el AVD de android
        val url = "http://xusa.iesdoctorbalmis.info/usuarios/" //para servidor del ins
        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(ProveedorSersvicio::class.java)
    }

    private fun getUsuario(): Deferred<ArrayList<Usuarios>> {

        var respuesta = ArrayList<Usuarios>()
        val proveedorServicios: ProveedorSersvicio = crearRetrofit()
        return CoroutineScope(Dispatchers.IO).async {
            val response = proveedorServicios.usuarios()
            if (response.isSuccessful) {
                val datosResponse = response.body()
                if (response != null)
                    respuesta = datosResponse!!

            } else
                Log.e("Error", response.errorBody().toString())

            respuesta
        }

    }

}