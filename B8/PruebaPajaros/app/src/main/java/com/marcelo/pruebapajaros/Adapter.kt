package com.marcelo.pruebapajaros

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class Adapter(var listaDatos: ArrayList<Datos>) : RecyclerView.Adapter<Adapter.Holder>(),
    View.OnClickListener,
    View.OnLongClickListener {
    lateinit var listenerClick: View.OnClickListener
    lateinit var listenerLong: View.OnLongClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View
        view = LayoutInflater.from(parent.context).inflate(R.layout.elemento_lista, parent, false)
        val holder = Holder(view)
        view.setOnClickListener(this)
        view.setOnLongClickListener(this)
        return holder
    }

    fun miOnClick(listener: View.OnClickListener) {
        listenerClick = listener
    }
    fun miOnLongClick(listener: View.OnLongClickListener) {
        listenerLong = listener
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(listaDatos[position])
    }

    override fun getItemCount(): Int {
    return listaDatos.size
    }

    override fun onClick(p0: View?) {
        listenerClick.onClick(p0)
    }

    override fun onLongClick(p0: View?): Boolean {
        listenerLong.onLongClick(p0)
        return false
    }

    class Holder(
        vista: View,
        var vistaTexto: TextView = vista.findViewById(R.id.textView_titulo),
        var vistaImagen: ImageView = vista.findViewById(R.id.imageView_imagen_miniatura),
        var VistaCardView: CardView = vista.findViewById(R.id.cardView)
    ) : RecyclerView.ViewHolder(vista) {

        fun bind(dato: Datos) {
            vistaTexto.text = dato.nombre
            vistaImagen.setImageResource(dato.idImagen)
        }
    }

}