package com.marcelo.ejercicioresueltobd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import layout.Adaptador

class MainActivity : AppCompatActivity() {
    lateinit var datos: ArrayList<Cliente>
    lateinit var recyclerView: RecyclerView
    lateinit var bdAdapter:BDAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bdAdapter = BDAdapter(this)
        datos = ArrayList<Cliente>()
        recyclerView = findViewById<RecyclerView>(R.id.recyclerList)
//        val adaptador = Adaptador(datos)
//        recyclerView.adapter = adaptador
//        recyclerView.layoutManager =
//            LinearLayoutManager(this, LinearLayoutManager.VERTICAL,
//                false)

        //       bdAdapter.insertarDatos()
        bdAdapter.updateContentValues("Xavi", "2222233", "Perez Rico", "dni=4")
        val cliente = Cliente("53432312J", "Marcelo", "Lopez Ruiz")
        bdAdapter.insertContentValues(cliente)
        bdAdapter.BorrandoContentValues()
        bdAdapter.UpdateConArgumentos()

        findViewById<Button>(R.id.buttonMostrar).setOnClickListener { view ->
            mostrar()


        }
        findViewById<Button>(R.id.buttonAdd).setOnClickListener {
            val dni = findViewById<EditText>(R.id.editTextDni).text.toString()
            val nombre = findViewById<EditText>(R.id.editTextNombre).text.toString()
            val apellidos = findViewById<EditText>(R.id.editTextApellidos).text.toString()
            val cliente = Cliente(dni, nombre, apellidos)
            bdAdapter.insertContentValues(cliente)

            mostrar()
        }


    }

    fun mostrar() {
        datos = bdAdapter.seleccionarDatosCodigo(
            arrayOf("dni", "nombre", "apellidos"),
            null,
            null,
            "apellidos"
        ) ?: ArrayList<Cliente>()
        cargarAdaptador()
    }

    fun cargarAdaptador() {

        val adaptador = Adaptador(datos)
        recyclerView.adapter = adaptador
        recyclerView.layoutManager =
            LinearLayoutManager(
                this, LinearLayoutManager.VERTICAL,
                false
            )

    }
}