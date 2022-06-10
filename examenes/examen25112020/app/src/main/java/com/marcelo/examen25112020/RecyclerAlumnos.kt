package com.marcelo.examen25112020

import android.content.Intent
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.widget.Adapter
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.marcelo.examen25112020.databinding.ActivityMainBinding
import com.marcelo.examen25112020.databinding.ActivityRecyclerAlumnosBinding
import java.util.zip.Inflater

class RecyclerAlumnos : AppCompatActivity() {

    lateinit var  binding: ActivityRecyclerAlumnosBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerAlumnosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val datos = this.intent.getParcelableArrayListExtra<Parcelable>("ALUMNOS") as ArrayList<Alumno>

        val recyclerView = binding.recyclerList
        val adaptador = Adaptador(datos)
        recyclerView.adapter = adaptador
        recyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        adaptador!!.miOnlick{ v ->
            val posicion = recyclerView.getChildAdapterPosition(v)
            val alumno =  datos[posicion]
            alumno.haFaltado = !alumno.haFaltado
            adaptador.notifyItemChanged(posicion)
            println(alumno)
         }
        binding.floatingActionButton.setOnClickListener{
            val intentMainActivity = Intent(applicationContext,MainActivity::class.java)
            intentMainActivity.putParcelableArrayListExtra("ALUMNOS",datos)
            for (alumno in datos) println(alumno)
            startActivity(intentMainActivity)


        }



    }
}