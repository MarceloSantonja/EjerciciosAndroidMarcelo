package com.marcelo.examen25112020

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import com.marcelo.examen25112020.databinding.ActivityInformeBinding

class ActivityInforme : AppCompatActivity() {
    lateinit var binding : ActivityInformeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInformeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val vistaTexto = binding.textViewInforme
        val alumnos = intent.getParcelableArrayListExtra<Parcelable>("ALUMNOS") as ArrayList<Alumno>
        val sbalumnos = StringBuilder("")

        for (alumno in alumnos)
            sbalumnos.appendLine(alumno.toString())
        
        vistaTexto.setText(sbalumnos.toString())
    }
}