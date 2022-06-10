package com.marcelo.examencumpleanyos.recycler

import android.database.Cursor
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.marcelo.examencumpleanyos.Cumple
import com.marcelo.examencumpleanyos.R

class Adaptador(var cursor: Cursor, private val onClickListener: (Int) -> Unit) :
    RecyclerView.Adapter<Holder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val vista =
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_cardview, parent, false)
        return Holder(vista)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        cursor.moveToPosition(position)
        var cumple = Cumple(
            cursor.getInt(0),
            cursor.getString(1),
            cursor.getInt(2),
            cursor.getString(3),
            cursor.getInt(4),
            cursor.getString(5),
            cursor.getString(6)
        )
        holder.bind(cumple, onClickListener)

    }

    override fun getItemCount(): Int = if (cursor == null) 0 else cursor.count


}