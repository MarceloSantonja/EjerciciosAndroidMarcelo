package com.marcelo.examencumpleanyos

import android.app.Dialog
import android.os.Build.VERSION_CODES.M
import android.os.Bundle
import android.system.Os.accept
import android.system.Os.bind
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.marcelo.examencumpleanyos.databinding.FragmentEtapasBinding
import kotlinx.coroutines.NonCancellable.cancel
import java.util.*

class FragmentEtapas : Fragment() {
    private var _binding: FragmentEtapasBinding? = null
    private val binding get() = _binding!!

    lateinit var nombreEtapaSeleccionada: String
    lateinit var etapaActual: String
    lateinit var  bdAdapter : BDAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEtapasBinding.inflate(inflater, container, false)
        etapaActual = ""
        bdAdapter = BDAdapter(binding.root.context)

        binding.textViewTituloEtapas.setText("Nombre nuevo")


        registerForContextMenu(binding.textViewJuego)
        registerForContextMenu(binding.textViewMerienda)
        registerForContextMenu(binding.textViewTeatro)
        registerForContextMenu(binding.textViewRegalos)

        return binding.root
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
        var minutos= hoy.get(Calendar.MINUTE)

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