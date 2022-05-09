package com.marcelo.pajaros2

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class Adapter(val listaDatos:ArrayList<Datos>): RecyclerView.Adapter<Holder>(),View.OnClickListener,View.OnLongClickListener {
    lateinit var escuchadorClick: View.OnClickListener
    lateinit var escuchadorLong: View.OnLongClickListener


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val datoActual = listaDatos[position]
        holder.bind(datoActual)
    }

    override fun getItemCount(): Int {
       return listaDatos.size
    }
    fun miClickListener(listener: View.OnClickListener){
        escuchadorClick= listener
    }
    fun miLongClickListener(listener: View.OnLongClickListener){
        escuchadorLong= listener
    }
    override fun onClick(vista: View?) {
        TODO("Not yet implemented")
    }

    override fun onLongClick(vista: View?): Boolean {
        TODO("Not yet implemented")
    }


}