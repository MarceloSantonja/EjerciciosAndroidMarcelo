package com.marcelo.ejemplorecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class Adaptador internal constructor(val datos: ArrayList<Usuario>) :
    RecyclerView.Adapter<Holder>(),View.OnClickListener ,View.OnLongClickListener {
    lateinit var listenerClick : View.OnClickListener
    lateinit var onLongListenerClick : View.OnLongClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val itemView: View =
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_layout, parent, false)
        itemView.setOnClickListener(this)
        itemView.setOnLongClickListener(this)
        return Holder(itemView)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item: Usuario = datos[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return datos.size
    }
    fun onClick(listener:View.OnClickListener){
        this.listenerClick= listener
    }

    fun onLongClick(listener:View.OnLongClickListener){
        this.onLongListenerClick= listener
    }

    override fun onClick(p0: View?) {
        listenerClick?.onClick(p0)
    }

    override fun onLongClick(p0: View?): Boolean {
        onLongListenerClick?.onLongClick(p0)
        return true
    }
}