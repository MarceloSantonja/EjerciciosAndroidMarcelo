package com.marcelo.examen1sqlite

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build.VERSION_CODES.N
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.WindowInsetsAnimationController
import androidx.activity.viewModels
import androidx.navigation.*
import androidx.navigation.fragment.NavHostFragment
import com.marcelo.examen1sqlite.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var bdAdaptador: BDAdapter
    lateinit var apiAdapter: ApirestAdapter
    private val model: ItemViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        apiAdapter = ApirestAdapter()
        bdAdaptador = BDAdapter(this)
        cargarDatosBD()





    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.overflow_tipos, menu)

        return  true
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        var textoTitulo = "La eleccion ha sido "
        var valor = 0
        when (item.itemId) {
            R.id.paisajes -> {
                textoTitulo += " paisajes maravillosos"
                valor = 0
            }
            R.id.animales ->{
                textoTitulo += " animales curiosos"
                valor = 1
            }
            R.id.pinturas ->{
                textoTitulo += " pinturas de la historia"
                valor = 2
            }
            else -> super.onOptionsItemSelected(item)
        }

        model.setTitulo(textoTitulo)
        model.setTipo(valor)
        Navigation.findNavController(this,R.id.nav_host_fragment).navigate(R.id.fragmentPresentacion)

        return true
    }

    private fun cargarDatosBD() {
        var datos: ArrayList<DatosSql>
        val listaDatos = bdAdaptador.seleccionarDatosCodigo(null, null, null, null)
        if (listaDatos.isEmpty()) {
            CoroutineScope(Dispatchers.Main).launch {
                datos = apiAdapter.getDatos().await()
                 bdAdaptador.anyadirDatos(datos)
            }
        }
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