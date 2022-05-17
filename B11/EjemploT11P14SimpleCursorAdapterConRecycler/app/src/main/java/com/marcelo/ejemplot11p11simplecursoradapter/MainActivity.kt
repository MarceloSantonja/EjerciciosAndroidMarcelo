package com.marcelo.ejemplot11p11simplecursoradapter

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.widget.Spinner
import androidx.cursoradapter.widget.SimpleCursorAdapter
import java.io.ByteArrayOutputStream

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val from = arrayOf("nombre", "cate")
        val to = intArrayOf(R.id.ciclo, R.id.cate)
        setContentView(R.layout.activity_main)
        val dbAdapter=DBAdapter(this)
        dbAdapter.insertarDatosCodigo()
        val desplegable = findViewById<Spinner>(R.id.spinner)
        val cursor=dbAdapter.leerDatos()
        val mAdapter = SimpleCursorAdapter(this, R.layout.spinner_layout,
            cursor, from, to, 0x0)


        desplegable.setAdapter(mAdapter)

    }

    companion object
    {
        fun convertirStringBitmap(imagen: String?): Bitmap {
            val decodedString: ByteArray = Base64.decode(imagen, Base64.DEFAULT)
            return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
        }
        fun ConvertirImagenString(bitmap: Bitmap): String {
            val stream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, stream)
            val byte_arr: ByteArray = stream.toByteArray()
            return Base64.encodeToString(byte_arr, Base64.DEFAULT)
        }
    }
}