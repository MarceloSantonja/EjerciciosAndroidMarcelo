package com.marcelo.b7agenda

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Holder(view: View) : RecyclerView.ViewHolder(view) {

    val textNombre: TextView
    val textApellidos: TextView
    val textMail: TextView
    val textTelefono: TextView
    val imagen: ImageView

    init {
        textNombre = view.findViewById(R.id.nombreTextView)
        textApellidos = view.findViewById(R.id.apellidoTextView)
        textMail = view.findViewById(R.id.mailTextView)
        textTelefono = view.findViewById(R.id.numeroTlfTextView)
        imagen = view.findViewById(R.id.imagenCardView)
    }
    fun bind(contacto:Contacto){
        textNombre.setText(contacto.nombre)
        textApellidos.setText(contacto.apellido)
        textMail.setText(contacto.email)
        textTelefono.setText(contacto.telefono)
        imagen.setImageBitmap(contacto.foto)
    }

}