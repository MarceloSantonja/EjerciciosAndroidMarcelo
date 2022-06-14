package com.marcelo.examen1sqlite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ItemViewModel:ViewModel() {
    private val tituloSelecionado= MutableLiveData<String>()
    val getTitulo: LiveData<String> get() = tituloSelecionado
    fun setTitulo(item: String) {
        tituloSelecionado.value = item
    }
    private val tipoSelecionado= MutableLiveData<Int>()
    val getTipo: LiveData<Int> get() = tipoSelecionado
    fun setTipo(item: Int) {
        tipoSelecionado.value = item
    }

    private val puntuacion= MutableLiveData<Int>()
    val getPuntuacion: LiveData<Int> get() = puntuacion
    fun setPuntuacion(item: Int) {
        puntuacion.value = item
    }
    private val datosPartida= MutableLiveData<HashMap<Int,AuxPistasSolucion>>()
    val getDatosPartida: MutableLiveData<HashMap<Int, AuxPistasSolucion>> get() = datosPartida
    fun setDatosPartida(item: HashMap<Int,AuxPistasSolucion>) {
        datosPartida.value = item
    }


}