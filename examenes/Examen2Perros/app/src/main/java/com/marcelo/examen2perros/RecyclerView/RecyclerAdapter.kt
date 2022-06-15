package com.marcelo.examen2perros.RecyclerView

import android.database.Cursor
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.marcelo.examen2perros.ImagenUtilidad.Companion.convertirStringBitmap
import com.marcelo.examen2perros.R

class RecyclerAdapter(c: Cursor, private var devuelveDatoOnClick: (Int) -> Unit) :
    CursorRecyclerAdapter(c) {

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        cursor: android.database.Cursor
    ) {
        (holder as SimpleViewHolder).bind(cursor, devuelveDatoOnClick)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val vista =
            LayoutInflater.from(parent.context).inflate(R.layout.linearecycler, parent, false)
        return SimpleViewHolder(vista)
    }

    internal inner class SimpleViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var id: Int
        var imagen: ImageView
        fun bind(dato: Cursor, devuelveDatoOnClick: (Int) -> Unit) {

            id = dato.getInt(0)
            val theImage: Bitmap = convertirStringBitmap(dato.getString(5))!!
            imagen.setImageBitmap(theImage)
            itemView.setOnClickListener {
                devuelveDatoOnClick(id)
                println( id.toString())
                println(it.id.toString())
            }
        }

        init {
            id = 0
            imagen = itemView.findViewById(R.id.imagen) as ImageView
        }
    }


}