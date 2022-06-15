package com.marcelo.examen2perros

import android.database.Cursor
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.marcelo.examen2perros.RecyclerView.CursorRecyclerAdapter
import com.marcelo.examen2perros.RecyclerView.RecyclerAdapter
import com.marcelo.examen2perros.databinding.FragmentRecyclerBinding


class RecyclerFragment : Fragment() {

    private var _binding:FragmentRecyclerBinding? = null
    val binding get() = _binding!!
    lateinit var  bdAdaptador: SQLiteAdapter
    val model: ItemViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRecyclerBinding.inflate(layoutInflater)
        bdAdaptador = SQLiteAdapter(binding.root.context)


        crearRecycler()


        return binding.root
    }

    private fun crearRecycler() {
        val tipo:Int? = model.getTipoSeleccionado.value
        val cursor:Cursor
        if (tipo != null){

            cursor = bdAdaptador.leerDatosCodigo("tipo=${tipo!!}")!!

        }else{
            cursor = bdAdaptador.leerDatosCodigo(null)!!
        }
        val recyclerView = binding.recyclerList
        val adaptador = RecyclerAdapter(cursor){ id->
            model.setIdActual(id)
            val navegacion = Navigation.findNavController(binding.root).navigate(R.id.action_recyclerFragment_to_editarAnimalFragment)


        }
        recyclerView.adapter = adaptador
        recyclerView.layoutManager =GridLayoutManager(binding.root.context,2,GridLayout.VERTICAL,false)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}