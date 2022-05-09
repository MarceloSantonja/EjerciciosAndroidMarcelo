package com.marcelo.pruebapajaros

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FragmentLista : Fragment() {
    lateinit var listenerLong: View.OnLongClickListener
    lateinit var recyclerView: RecyclerView
    var valores: ArrayList<Datos>? = null
    var posicion: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val vistaRaiz: View = inflater.inflate(R.layout.fragment_recycler, container, false)
        recyclerView = vistaRaiz.findViewById(R.id.recycler)
        adapter = Adapter(MainActivity.datos!!)
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager =
            LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false)
        //faltan los metodos onclick

        adapter!!.miOnClick { vista ->
            val bundle = Bundle()
            bundle.putInt("POSICION", recyclerView.getChildAdapterPosition(vista))
            val navController = NavHostFragment.findNavController(this)
            if (navController.currentDestination?.id == R.id.fragmentLista) {
                if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                    navController.navigate(R.id.action_fragmentLista_to_fragmentDetalle, bundle)
                } else {
                    val fT = requireActivity().supportFragmentManager.beginTransaction()
                    fT.replace(R.id.detalle, FragmentDetalle::class.java, bundle).commit()
                }
            } else {
                Toast.makeText(context, "esto no tiene onCLick", Toast.LENGTH_SHORT).show()
            }
        }

        adapter!!.miOnLongClick {
            val navController = NavHostFragment.findNavController(this)
            val bundle = Bundle()
            val position = recyclerView.getChildAdapterPosition(it)
            if (navController.currentDestination?.id == R.id.fragmentLista) {
                bundle.putInt("POSICION", position)
                navController.navigate(R.id.action_fragmentLista_to_dialogoEliminar, bundle)


//                val navHostFragment =
//                    requireActivity().supportFragmentManager.findFragmentById(R.id.fragmentLista) as  NavHostFragment
//                val navControllerLista = navHostFragment.navController
//                if (navControllerLista == navController) {
//                    Toast.makeText( context?.applicationContext , "son iguales", Toast.LENGTH_SHORT).show()
//                }
            }

            true
        }






        return vistaRaiz
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            valores = MainActivity.datos
            listenerLong = context as View.OnLongClickListener
        } catch (e: ClassCastException) {
            println(e.localizedMessage)
        }
    }


    companion object {
        var adapter: Adapter? = null
    }

}