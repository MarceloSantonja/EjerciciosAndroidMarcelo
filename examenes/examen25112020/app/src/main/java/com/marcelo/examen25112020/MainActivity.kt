package com.marcelo.examen25112020

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import com.marcelo.examen25112020.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    var alumnos: ArrayList<Alumno>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        val botonFaltas = binding.buttonFaltas
        val botonInforme = binding.buttonInforme
        if (alumnos.isNullOrEmpty()) {
            alumnos = MainActivity.cargaListaAlumnos()
            println("INICIADO ARRAY")
        }else{
            alumnos = intent.getParcelableArrayListExtra<Parcelable>("ALUMNOS") as ArrayList<Alumno>
            println("INICIADO CAMBIADO")

        }

        botonInforme.setOnClickListener {
            val intentFaltas = Intent(applicationContext, ActivityInforme::class.java)
            intentFaltas.putParcelableArrayListExtra("ALUMNOS",alumnos)
            startActivity(intentFaltas)
        }
        botonFaltas.setOnClickListener {
            val intentInforme = Intent(applicationContext, RecyclerAlumnos::class.java)
            intentInforme.putParcelableArrayListExtra("ALUMNOS", alumnos)
            startActivity(intentInforme)
        }

    }

    companion object {

        fun cargaListaAlumnos(): ArrayList<Alumno> {
            var alumnos = arrayListOf<Alumno>()

            alumnos.add(Alumno("Jaime", "Toribio Lopez", "", false))
            alumnos.add(Alumno("Marcelo", "Santonja Andreu", "", false))
            alumnos.add(Alumno("Julia", "Murria Murria", "", false))

            return alumnos
        }
        val alumnos = cargaListaAlumnos()
    }
}