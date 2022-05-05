package com.marcelo.ejemplorecyclerview

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Holder(v: View) :RecyclerView.ViewHolder(v) {
    val textNombre: TextView
    val textApellido: TextView

    fun bind(entity: Usuario){
        textNombre.setText(entity.nombre)
        textApellido.setText(entity.apellidos)
    }
    init {
        textNombre = v.findViewById(R.id.textView)
        textApellido = v.findViewById(R.id.textView2)
    }

}
