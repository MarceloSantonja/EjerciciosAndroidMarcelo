package com.marcelo.ejemplorecyclerview

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val datos = anadirDatos()
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerList)
        val adaptador = Adaptador(datos,this)
        recyclerView.adapter = adaptador
        recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true)
//        adaptador.onClick(View.OnClickListener { v ->
//            Toast.makeText(
//                this@MainActivity,
//                "Has pulsado" + recyclerView.getChildAdapterPosition(v),
//                Toast.LENGTH_SHORT
//            ).show()
//        })
//        adaptador.onLongClick(View.OnLongClickListener { v ->
//
//            val posicion = recyclerView.getChildAdapterPosition(v)
//            adaptador.datos.removeAt(posicion)
//            adaptador.notifyItemRemoved(posicion)
//            true
//        })
        adaptador.pasarCadena(object:PasarCadenaInterface {
            override fun pasarCadena(cadena: String) {
                Toast.makeText(applicationContext, "Has pulsado " + cadena, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun anadirDatos(): ArrayList<Usuario> {
        var datos = ArrayList<Usuario>()
        for (i in 0..19)
            datos.add(Usuario("nombre$i", "apellido$i apellido$i"))
        return datos
    }
}