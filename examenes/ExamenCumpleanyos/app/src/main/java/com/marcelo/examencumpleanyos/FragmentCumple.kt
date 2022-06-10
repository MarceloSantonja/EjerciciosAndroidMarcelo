package com.marcelo.examencumpleanyos

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar
import com.marcelo.examencumpleanyos.databinding.FragmentCumpleBinding


class FragmentCumple : Fragment() {

    private var _binding: FragmentCumpleBinding? = null
   private val binding get() = _binding!!
   lateinit var bdAdapter: BDAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCumpleBinding.inflate(inflater, container, false)
        bdAdapter = BDAdapter(binding.root.context)

        fechadatepicker(binding.root.context)
        binding.floatingActionButton.setOnClickListener {
            anyadirDatos()
        }
        return binding.root
    }


    private fun anyadirDatos() {
        val etNombre = binding.editTextTextCumpleanyero
        val etAnyos = binding.editTextCumple
        val etContacto = binding.editTextTexContacto
        val etTelefono = binding.editTextTelefono
        val etEMail = binding.editTextTextEmailAddress
        val tvFecha = binding.textViewFechaCumple
        if (etNombre.text.isNotBlank() &&
            etAnyos.text.isNotBlank() &&
            etContacto.text.isNotBlank() &&
            etTelefono.text.isNotBlank() &&
            etEMail.text.isNotBlank() &&
            !tvFecha.text.equals(resources.getString(R.string.fecha_cumple))
        ) {
         var cumpleanyos = Cumple(
               etNombre.text.toString(),
                      etAnyos.text.toString().toInt()  ,
                     etContacto.text.toString(),
                     etTelefono.text.toString().toInt() ,
                     etEMail.text.toString(),
                     tvFecha.text.toString()

         )
            bdAdapter.insertarDatos(cumpleanyos)



        } else {
            Snackbar.make(
                binding.root,
                "Lo siento no se pueden dejar los campos vacios",
                Snackbar.LENGTH_INDEFINITE
            ).setAction("Aceptar") {
                etNombre.text.clear()
                etAnyos.text.clear()
                etContacto.text.clear()
                etTelefono.text.clear()
                etEMail.text.clear()
                tvFecha.text = resources.getString(R.string.fecha_cumple)
            }.show()
        }
    }

    private fun fechadatepicker(contexto: Context) {
        val fechaTexto = binding.textViewFechaCumple


        fechaTexto.setOnClickListener {
            val datePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Fecha de nacimiento")
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .build()
            datePicker.addOnPositiveButtonClickListener {
                fechaTexto.setText(datePicker.headerText.toString())

            }
            datePicker.show(parentFragmentManager, "fecha Cumpleaños")

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}