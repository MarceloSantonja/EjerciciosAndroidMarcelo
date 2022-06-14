package com.marcelo.examen1sqlite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.marcelo.examen1sqlite.databinding.FragmentPresentacionBinding

class FragmentPresentacion : Fragment() {

    private var _binding :FragmentPresentacionBinding ? = null
    private val binding get() = _binding!!
    private val model:ItemViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPresentacionBinding.inflate(inflater, container, false)
        val navController = NavHostFragment.findNavController(this)
        model.getTitulo.observe(requireActivity()){ cadena->
            binding.textViewSeleccion.text= cadena

        }
        binding.buttonComenzar.setOnClickListener{
            navController.navigate(R.id.action_fragmentPresentacion_to_fragmentJugar)
            model.setPuntuacion(0)
            model.setDatosPartida(HashMap<Int,AuxPistasSolucion>())
        }

        return binding.root
    }

//    override fun onDestroyView() {
//        super.onDestroy()
//        _binding = null
//    }

}