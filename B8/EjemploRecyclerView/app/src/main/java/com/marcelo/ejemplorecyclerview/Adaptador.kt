package com.marcelo.ejemplorecyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class Adaptador (val datos: ArrayList<Usuario>,var contexto: Context) :
    RecyclerView.Adapter<Holder>(), View.OnClickListener, View.OnLongClickListener {
    lateinit var listenerClick: View.OnClickListener
    lateinit var onLongListenerClick: View.OnLongClickListener
    lateinit var pasarCadenaInterface: PasarCadenaInterface

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_layout, parent, false)

        val holder = Holder(itemView)
        holder.pasarCadena( object : PasarCadenaInterface {
            override fun pasarCadena(cadena: String) {
                pasarCadenaInterface.pasarCadena(cadena)
            }
        })

        itemView.setOnClickListener(this)
        itemView.setOnLongClickListener(this)
        return Holder(itemView)
    }
    fun pasarCadena(pasarCadenaInterface: PasarCadenaInterface) {
        this.pasarCadenaInterface = pasarCadenaInterface
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item: Usuario = datos[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return datos.size
    }

    fun onClick(listener: View.OnClickListener) {
        this.listenerClick = listener
    }

    fun onLongClick(listener: View.OnLongClickListener) {
        this.onLongListenerClick = listener
    }

    override fun onClick(p0: View?) {
        listenerClick.onClick(p0)
    }

    override fun onLongClick(p0: View?): Boolean {
        onLongListenerClick?.onLongClick(p0)
        return true
    }


}