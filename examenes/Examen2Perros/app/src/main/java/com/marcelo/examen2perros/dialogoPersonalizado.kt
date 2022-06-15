package com.marcelo.examen2perros

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.marcelo.examen2perros.databinding.FragmentDialogoPersonalizadoBinding
import com.marcelo.examen2perros.databinding.FragmentRecyclerBinding


class dialogoPersonalizado : DialogFragment() {

    private var _binding: FragmentDialogoPersonalizadoBinding? = null
    val binding get() = _binding!!
    lateinit var bdAdaptador: SQLiteAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDialogoPersonalizadoBinding.inflate(layoutInflater)
        bdAdaptador = SQLiteAdapter(binding.root.context)

        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val dialogo = AlertDialog.Builder(requireContext())
            .setView(R.layout.fragment_dialogo_personalizado)
            .create()

        return dialogo
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}