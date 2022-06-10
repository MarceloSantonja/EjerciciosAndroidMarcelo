package com.marcelo.examen25112020

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.marcelo.examen25112020.databinding.RecyclerlayoutBinding
import java.util.zip.Inflater

class Adaptador (val datos:ArrayList<Alumno>): RecyclerView.Adapter<Holder>(),
                                                View.OnClickListener,
                                                View.OnLongClickListener {

    lateinit var listener: View.OnClickListener
    var listenerLong: View.OnLongClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
//        val v = View.inflate(parent.context,R.layout.activity_recycler_alumnos,parent)
        val vistaLayoutRecycler = LayoutInflater.from(parent.context).inflate(R.layout.recycler_cardview,parent,false)

        vistaLayoutRecycler.setOnClickListener(this)
        vistaLayoutRecycler.setOnLongClickListener(this)
       return Holder(vistaLayoutRecycler)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = datos[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = datos.size

    fun miOnlick(listener: View.OnClickListener?) {
        if (listener != null) {
            this.listener = listener
        }
    }

    override fun onClick(p0: View?) {
        if (listener != null) listener!!.onClick(p0)
    }

    fun miOnLongClick(listener: View.OnLongClickListener?){
        listenerLong = listener
    }

    override fun onLongClick(p0: View?): Boolean {
        if (listener != null) listener!!.onClick(p0)
        return false
    }
}