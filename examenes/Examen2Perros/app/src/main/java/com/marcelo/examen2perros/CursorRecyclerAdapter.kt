package com.marcelo.examen2perros

import android.database.Cursor
import androidx.recyclerview.widget.RecyclerView

abstract class CursorRecyclerAdapter(cursor: Cursor) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var mCursor: Cursor
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder,
                                  position: Int)
    {
        checkNotNull(mCursor) { "ERROR, cursos vacio" }
        check(mCursor.moveToPosition(position)) { "ERROR, no se puede" +
                " encontrar la posicion:  $position" }
        onBindViewHolder(holder, mCursor)
    }
    abstract fun onBindViewHolder(holder: RecyclerView.ViewHolder,
                                  cursor: Cursor)
    override fun getItemCount(): Int
    {
        return if (mCursor != null)    mCursor.getCount()
        else 0
    }
    init {
        mCursor = cursor
    }
}