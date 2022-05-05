package com.marcelo.ejemplorecyclerview

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Holder(v: View) :RecyclerView.ViewHolder(v), View.OnClickListener {
    val textNombre: TextView
    val textApellido: TextView
    lateinit var pasarCadenaInterface: PasarCadenaInterface

    fun bind(entity: Usuario){
        textNombre.setText(entity.nombre)
        textApellido.setText(entity.apellidos)
    }
    init {
        textNombre = v.findViewById(R.id.textView)
        textApellido = v.findViewById(R.id.textView2)
        textNombre.setOnClickListener(this)
        textApellido.setOnClickListener(this)
    }
    fun pasarCadena( pasarCadenaInterface: PasarCadenaInterface){
        this.pasarCadenaInterface =pasarCadenaInterface
    }

    override fun onClick(p0: View?) {
        var cadena:String
        if(p0?.id==R.id.textView) cadena=textNombre.text.toString()
        else cadena=textApellido.text.toString()
        pasarCadenaInterface.pasarCadena(cadena)
    }



}
