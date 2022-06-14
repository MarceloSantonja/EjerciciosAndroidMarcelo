package com.marcelo.examen1sqlite

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface ProveedorSersvicio {

    @GET("imagenes")
    @Headers("Accept: application/json", "Content-Type: application/json")
    suspend fun cargarDatos(): Response<ArrayList<DatosSql>>
}
