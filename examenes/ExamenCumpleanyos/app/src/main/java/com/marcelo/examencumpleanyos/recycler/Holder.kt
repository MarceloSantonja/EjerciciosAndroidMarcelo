package com.marcelo.examencumpleanyos.recycler

import android.database.Cursor
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.marcelo.examencumpleanyos.Cumple
import com.marcelo.examencumpleanyos.databinding.RecyclerCardviewBinding

class Holder(v: View) : RecyclerView.ViewHolder(v) {
    val binding: RecyclerCardviewBinding = RecyclerCardviewBinding.bind(v)
    val tvNombre: TextView = binding.textViewNombreninyo
    val tvEdad: TextView = binding.textViewEdad
    val tvTelefono: TextView = binding.textViewTelefono
    val tvFecha: TextView = binding.textViewFecha
    val tvEmail: TextView = binding.textViewEmail
    val tvNombrePadre: TextView = binding.textViewNombrePadre

    fun bind(cumple: Cumple, onClickListener: (Int) -> Unit) {
        tvNombre.setText(cumple.nombre)
        tvEdad.setText(cumple.edad.toString())
        tvTelefono.setText(cumple.telefono.toString())
        tvFecha.setText(cumple.fecha)
        tvEmail.setText(cumple.eMail)
        tvNombrePadre.setText(cumple.contacto)
       itemView.setOnClickListener{ onClickListener(cumple.id)}

    }



}