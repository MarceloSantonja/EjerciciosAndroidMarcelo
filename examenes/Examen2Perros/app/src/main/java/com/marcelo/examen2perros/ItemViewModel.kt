package com.marcelo.examen2perros

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ItemViewModel : ViewModel() {
    private val idActual= MutableLiveData<Int>()
    val getIdActual: LiveData<Int> get() = idActual
    fun setIdActual(id: Int) {
        idActual.value = id
    }
    private val tipoSeleccionado= MutableLiveData<Int?>()
    val getTipoSeleccionado: LiveData<Int?> get() = tipoSeleccionado
    fun setTipoSeleccionado(tipo: Int?) {
        tipoSeleccionado.value = tipo
    }
}