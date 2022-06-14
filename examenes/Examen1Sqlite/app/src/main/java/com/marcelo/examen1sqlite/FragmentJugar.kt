package com.marcelo.examen1sqlite

import android.app.AlertDialog
import android.app.ProgressDialog.show
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.marcelo.examen1sqlite.databinding.FragmentJugarBinding
import com.marcelo.examen1sqlite.recyclerView.RecyclerAdapter


class FragmentJugar : Fragment() {

    private var _binding: FragmentJugarBinding? = null
    private val binding get() = _binding!!
    private val model: ItemViewModel by activityViewModels()
    lateinit var bdAdapter: BDAdapter
    lateinit var navController: NavController
    private var idActual: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        hashMapOf<Int, AuxPistasSolucion>()
        _binding = FragmentJugarBinding.inflate(inflater, container, false)
        bdAdapter = BDAdapter(binding.root.context)
        val cursor = bdAdapter.cursorDatos(null, "tipo=${model.getTipo.value!!}", null, null)
        val recyclerView = binding.recyclerList
        val adaptador = RecyclerAdapter(cursor!!) { id, imagen -> recuperarDatosImagen(id, imagen) }

        recyclerView.adapter = adaptador
        recyclerView.layoutManager = GridLayoutManager(binding.root.context, 2)
        navController = findNavController()

        binding.buttonVolver.setOnClickListener {
            navController.navigate(R.id.action_fragmentJugar_to_fragmentPresentacion)
        }
        binding.buttonPuntos.setOnClickListener {

            MaterialAlertDialogBuilder(binding.root.context)
                .setTitle("Puntuacion")
                .setMessage("TU PUNTUACION ES : ${model.getPuntuacion.value}")
                .setPositiveButton("Aceptar") { dialog, which ->
                }.show()
        }
        binding.buttonComprobar.setOnClickListener {
            comprobarRespuesta()
        }


        return binding.root
    }

    private fun comprobarRespuesta() {
        val textoUsuario = binding.editTextSolucion.text.toString().uppercase()
        var haAcertado: Boolean = false
        val partidaActual = model.getDatosPartida.value!!.get(idActual)
        if (partidaActual != null) {
            val soluciones = partidaActual.solucion.split("\\s".toRegex())
            for (solucion in soluciones) {
                println(solucion)
                if (solucion.equals(textoUsuario))
                    haAcertado = true
            }
        }
        if (haAcertado) {
            val pistaUsada = partidaActual!!.numeroMayorPistaUsada
            var puntuacionSuma = 0
            when (pistaUsada) {
                0 -> puntuacionSuma = 20
                1 -> puntuacionSuma = 15
                2 -> puntuacionSuma = 10
                3 -> puntuacionSuma = 5
            }
            var puntuacion = model.getPuntuacion.value
            puntuacion = puntuacion?.plus(puntuacionSuma)
            model.setPuntuacion(puntuacion!!)
            Toast.makeText(
                binding.root.context,
                "Has acertado.Genial!!! tus puntos son $puntuacion",
                Toast.LENGTH_LONG
            ).show()
        }else{
            Toast.makeText(binding.root.context, "Has fallado sigue intentando", Toast.LENGTH_LONG).show()

        }


    }

    fun recuperarDatosImagen(id: Int, imagen: ImageView) {
        registerForContextMenu(imagen)
        idActual = id
        val partida = model.getDatosPartida.value!!
        if (!partida.contains(id)) {
            val datoArray = bdAdapter.seleccionarDatosCodigo(null, "id=$id", null, null)
            val dato = datoArray[0]
            val auxPistasSolucion =
                AuxPistasSolucion(dato.pistauno, dato.pistados, dato.pistatres, dato.solucion)
            partida.put(id, auxPistasSolucion)
            model.setDatosPartida(partida)
        }
        println(idActual)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val menuInflater = MenuInflater(binding.root.context)
        menuInflater.inflate(R.menu.menu_contextual, menu)

    }

    override fun onContextItemSelected(item: MenuItem): Boolean {

        var pista = model.getDatosPartida.value!![idActual]!!
        when (item.itemId) {
            R.id.itemPrimeraPista -> {
                if (pista.numeroMayorPistaUsada < 1)
                    pista.numeroMayorPistaUsada = 1
                Snackbar.make(binding.root, pista.pistauno, Snackbar.LENGTH_INDEFINITE)
                    .setAction("OK") { }.show()
            }
            R.id.itemSegundaPista -> {
                if (pista.numeroMayorPistaUsada < 2)
                    pista.numeroMayorPistaUsada = 2
                Snackbar.make(binding.root, pista.pistados, Snackbar.LENGTH_INDEFINITE)
                    .setAction("OK") {}.show()
            }
            R.id.itemTerceraPista -> {
                if (pista.numeroMayorPistaUsada < 3)
                    pista.numeroMayorPistaUsada = 3
                Snackbar.make(binding.root, pista!!.pistatres, Snackbar.LENGTH_INDEFINITE)
                    .setAction("OK") {}.show()
            }
        }
        return super.onContextItemSelected(item)

    }


}