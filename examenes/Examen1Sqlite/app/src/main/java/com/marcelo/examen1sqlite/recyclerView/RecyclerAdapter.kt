package com.marcelo.examen1sqlite.recyclerView

import android.database.Cursor
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.marcelo.examen1sqlite.DatosSql
import com.marcelo.examen1sqlite.MainActivity
import com.marcelo.examen1sqlite.R

class RecyclerAdapter(c: Cursor,private var onClickitem:(Int,ImageView)->Unit) : CursorRecyclerAdapter(c) {
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


        (holder as SimpleViewHolder).bind(cursor,onClickitem)
    }
    internal inner class SimpleViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView)
    {
        var id : Int= 0
        var imagen: ImageView

        fun bind(dato: Cursor, onClickitem:(Int,ImageView)-> Unit) {
            println("HOLDER HOLA!! : ${dato.getString(0)} ")
            id= dato.getInt(0)
            val theImage: Bitmap = MainActivity.convertirStringBitmap(dato.getString(5))
            imagen.setImageBitmap(theImage)
            itemView.setOnClickListener{onClickitem(id,imagen)}


        }
        init {

            imagen = itemView.findViewById(R.id.imageViewRecycler) as ImageView


        }


    }




}