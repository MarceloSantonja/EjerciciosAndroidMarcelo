package com.marcelo.examencumpleanyos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
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
        //TODO dudas asignar datos al recyclerview
        val cursor = bdAdapter.cursordatos()
        val adaptador = Adaptador(cursor!!) { id -> onItemSelected(id) }
        val recyclerView = binding.recyclerList
        recyclerView.adapter = adaptador
        recyclerView.layoutManager =
            LinearLayoutManager(binding.root.context, LinearLayoutManager.VERTICAL, false)
        return binding.root

    }

    fun onItemSelected( id: Int) {
        val cumplesIniciados = bdAdapter.buscarCumplesIniciados()

        if (cumplesIniciados.isNullOrEmpty() ){
            bdAdapter.valorIniciado(id,1)
            Snackbar.make(binding.root,"Cumple con id $id iniciado",Snackbar.LENGTH_LONG).show()
        }
        else if(cumplesIniciados[0].id == id){
           MaterialAlertDialogBuilder(binding.root.context)
               .setTitle("Aviso Cumpleaños Activo")
               .setMessage("Este cumpleaños ya esta activo, desea Cancelarlo?")
               .setPositiveButton("Aceptar"){ dialogInterface, i ->
                   bdAdapter.valorIniciado(id,0)
               }.setNegativeButton("Cancelar"){dialogInterface, i ->
               }.show()
        }else{
            Snackbar.make(binding.root," otro Cumple esta iniciado",Snackbar.LENGTH_LONG).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}