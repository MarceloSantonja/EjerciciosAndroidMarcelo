package com.marcelo.b7agenda

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ItemViewModel : ViewModel() {
    private val liveData= MutableLiveData<Contacto>() //:MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val getItem: LiveData<Contacto> get() = liveData

    fun setItem(item: Contacto) {
        liveData.value = item
    }
}