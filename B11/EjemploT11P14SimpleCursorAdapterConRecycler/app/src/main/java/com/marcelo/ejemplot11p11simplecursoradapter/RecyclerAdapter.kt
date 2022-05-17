package com.marcelo.ejemplot11p11simplecursoradapter

import android.database.Cursor
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter(c: Cursor) : CursorRecyclerAdapter(c) {
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): RecyclerView.ViewHolder
    {
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_layout, parent, false)
        return SimpleViewHolder(v)
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder,
                                  cursor: Cursor)
    {
        (holder as SimpleViewHolder).bind(cursor)
    }
    internal inner class SimpleViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView)
    {
        var nombre: TextView
        var cate: TextView
        var imagen: ImageView
        fun bind(dato: Cursor) {
            nombre.setText(dato.getString(0))
            cate.setText(dato.getString(1))
            val theImage: Bitmap = MainActivity.
            convertirStringBitmap(dato.getString(3))
            imagen.setImageBitmap(theImage)
        }
        init {
            nombre = itemView.findViewById(R.id.ciclo)
            cate = itemView.findViewById(R.id.cate)
            imagen = itemView.findViewById(R.id.imagen) as ImageView
        }
    }

}