package com.marcelo.ejercicioresueltobd

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Holder(v: View) : RecyclerView.ViewHolder(v) {
    val textNombre: TextView
    val textApellido: TextView
    val textDni: TextView

    fun bind(entity: Cliente) {
        textNombre.setText(entity.nombre)
        textApellido.setText(entity.apellidos)
        textDni.setText(entity.dni)
    }

    init {
        textNombre = v.findViewById(R.id.textViewNombre)
        textApellido = v.findViewById(R.id.textViewApellido)
        textDni = v.findViewById(R.id.textViewDni)
    }
}