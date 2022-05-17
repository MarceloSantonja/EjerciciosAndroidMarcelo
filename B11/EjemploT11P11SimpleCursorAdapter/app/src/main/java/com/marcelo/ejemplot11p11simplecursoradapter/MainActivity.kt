package com.marcelo.ejemplot11p11simplecursoradapter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Spinner
import androidx.cursoradapter.widget.SimpleCursorAdapter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val from = arrayOf("nombre", "cate")
        val to = intArrayOf( R.id.ciclo, R.id.cate)
        setContentView(R.layout.activity_main)
        val dbAdapter=DBAdapter(this)
        dbAdapter.insertarDatosCodigo()
        val desplegable = findViewById<Spinner>(R.id.spinner)
        val cursor=dbAdapter.leerDatos()
        val mAdapter = SimpleCursorAdapter(this, R.layout.spinner_layout,
            cursor, from, to, 0x0)

        desplegable.setAdapter(mAdapter)

    }
}