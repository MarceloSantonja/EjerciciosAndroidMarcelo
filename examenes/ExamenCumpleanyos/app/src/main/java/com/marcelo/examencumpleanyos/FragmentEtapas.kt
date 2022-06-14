package com.marcelo.examencumpleanyos

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.marcelo.examencumpleanyos.databinding.FragmentEtapasBinding
import java.util.*

class FragmentEtapas : Fragment() {
    private var _binding: FragmentEtapasBinding? = null
    private val binding get() = _binding!!

    lateinit var nombreEtapaSeleccionada: String
    lateinit var etapaActual: String
    lateinit var bdAdapter: BDAdapter
    private var cumpleActivo: Cumple? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEtapasBinding.inflate(inflater, container, false)
        etapaActual = ""
        bdAdapter = BDAdapter(binding.root.context)
        //TODO duda asignar cumpleañero en la vista
        asignarCupleanyero()

        registerForContextMenu(binding.textViewJuego)
        registerForContextMenu(binding.textViewMerienda)
        registerForContextMenu(binding.textViewTeatro)
        registerForContextMenu(binding.textViewRegalos)
        binding.fabEliminarCumple.setOnClickListener {
            if (cumpleActivo != null){
                bdAdapter.borrar(cumpleActivo!!.id)
                Toast.makeText(binding.root.context,"cumple de ${cumpleActivo!!.nombre} borrado de la BD",Toast.LENGTH_LONG)
            }
        }
        return binding.root
    }

    private fun asignarCupleanyero() {
        var cumplesIniciados = bdAdapter.buscarCumplesIniciados()
        var nombreCumpleanyero = "Nombre cumpleanyero"
        if (cumplesIniciados != null) {
            cumpleActivo = cumplesIniciados[0]
            nombreCumpleanyero = cumpleActivo!!.nombre
        }
        binding.textViewTituloEtapas.text = "Etapas de$nombreCumpleanyero"
    }

    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val menuInflater = MenuInflater(binding.root.context)
        menuInflater.inflate(R.menu.menu_contextual, menu)

        when (v.id) {
            binding.textViewJuego.id -> nombreEtapaSeleccionada = "Juego"
            binding.textViewMerienda.id -> nombreEtapaSeleccionada = "Merienda"
            binding.textViewRegalos.id -> nombreEtapaSeleccionada = "Regalos"
            binding.textViewTeatro.id -> nombreEtapaSeleccionada = "Teatro"
        }
        menu.setHeaderTitle(nombreEtapaSeleccionada)


    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        var hoy = Calendar.getInstance()
        var hora = hoy.get(Calendar.HOUR)
        var minutos = hoy.get(Calendar.MINUTE)

        var texto = "$nombreEtapaSeleccionada\n" +
                "Comenzo a : $hora:$minutos"
        when (item.itemId) {
            R.id.iniciarIntervalo -> {

                when (etapaActual) {
                    "" -> {
                        Snackbar.make(
                            binding.root,
                            "intervalo iniciado $nombreEtapaSeleccionada",
                            Snackbar.LENGTH_LONG
                        ).show()


                        MaterialAlertDialogBuilder(binding.root.context)
                            .setTitle("Recordatorio etapa")
                            .setMessage(texto)
                            .setPositiveButton("ACABADA") { dialog, which ->
                                etapaActual = nombreEtapaSeleccionada
                            }
                            .show()

                    }
                    nombreEtapaSeleccionada ->
                        Snackbar.make(
                            binding.root,
                            "la etapa $nombreEtapaSeleccionada ya esta iniciada",
                            Snackbar.LENGTH_LONG
                        ).show()
                    else ->
                        Snackbar.make(
                            binding.root,
                            "no se puede iniciar la etapa $nombreEtapaSeleccionada, la etapa $etapaActual esta iniciada",
                            Snackbar.LENGTH_LONG
                        ).show()


                }
            }
            R.id.acabarIntervalo -> {
                when (etapaActual) {
                    nombreEtapaSeleccionada -> {
                        Snackbar.make(
                            binding.root,
                            "etapa finalizada $nombreEtapaSeleccionada",
                            Snackbar.LENGTH_LONG
                        ).show()
                        etapaActual = ""
                    }
                    "" -> Snackbar.make(
                        binding.root,
                        "no hay ninguna etapa iniciada",
                        Snackbar.LENGTH_LONG
                    ).show()
                    else -> Snackbar.make(
                        binding.root,
                        "esta etapa no ha sido iniciada",
                        Snackbar.LENGTH_LONG
                    ).show()
                }
            }
        }
        return super.onContextItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}