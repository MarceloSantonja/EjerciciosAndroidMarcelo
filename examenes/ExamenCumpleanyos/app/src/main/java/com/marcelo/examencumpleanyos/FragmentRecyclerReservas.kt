package com.marcelo.examencumpleanyos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.marcelo.examencumpleanyos.databinding.FragmentRecyclerReservasBinding
import com.marcelo.examencumpleanyos.recycler.Adaptador

class FragmentRecyclerReservas : Fragment() {

    private var _binding: FragmentRecyclerReservasBinding? = null
    private val binding get() = _binding!!
    lateinit var bdAdapter: BDAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRecyclerReservasBinding.inflate(layoutInflater, container, false)

        bdAdapter = BDAdapter(binding.root.context)

        var cursor = bdAdapter.cursordatos()
        val adaptador = Adaptador(cursor!!) { id -> onItemSelected(id) }
        val recyclerView = binding.recyclerList
        recyclerView.adapter = adaptador
        recyclerView.layoutManager =
            LinearLayoutManager(binding.root.context, LinearLayoutManager.VERTICAL, false)

        return binding.root

    }

    fun onItemSelected( id: Int) {
        Toast.makeText(binding.root.context, "$id", Toast.LENGTH_SHORT).show()
        bdAdapter.iniciar(id)
        var cumplesIniciados = bdAdapter.buscarCumplesIniciados()
        if (cumplesIniciados != null) {
            cumplesIniciados.forEach { cumple ->
                println( cumple.id.toString())
            }
        }else{
            println( "array nulo")
        }

    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}