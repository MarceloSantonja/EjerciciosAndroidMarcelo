package com.marcelo.examen25112020

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.widget.RecyclerView

class Holder(v: View) : RecyclerView.ViewHolder(v) {

    private val textNombre: TextView
    private val textApellido: TextView
    private val imagenFalta : ImageView
    lateinit var listenerClick: View.OnClickListener
    fun bind(entity: Alumno) {
        textNombre.setText(entity.nombre)
        textApellido.setText(entity.apellidos)
        if(entity.haFaltado== true)
            imagenFalta.visibility = View.VISIBLE
        else imagenFalta.visibility = View.GONE
    }
    init {
        textNombre = v.findViewById(R.id.textViewNombre)
        textApellido = v.findViewById(R.id.textViewApellidos)
        imagenFalta = v.findViewById(R.id.imageViewFalta)
    }

}