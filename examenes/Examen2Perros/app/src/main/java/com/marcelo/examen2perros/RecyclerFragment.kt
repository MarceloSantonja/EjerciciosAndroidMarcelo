package com.marcelo.examen2perros

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.marcelo.examen2perros.databinding.FragmentRecyclerBinding


class RecyclerFragment : Fragment() {

    private var _binding:FragmentRecyclerBinding? = null
    val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRecyclerBinding.inflate(layoutInflater)







        return binding.root




    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}