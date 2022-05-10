package com.marcelo.pruebapajaros

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView


class FragmentDetalle : Fragment() {
    var mItem: Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) mItem = savedInstanceState?.getInt("POSICION")
        else {
            if (arguments != null) {
                mItem = arguments?.getInt("POSICION")
            }
            else mItem=0
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val vistaRaiz: View= inflater.inflate(R.layout.fragment_detalle,container,false)
        if (!MainActivity.datos!!.isEmpty()){
            val elemento = MainActivity.datos?.get(mItem!!)
            (vistaRaiz.findViewById<TextView>(R.id.textView_superior)).text = elemento?.nombre
            (vistaRaiz.findViewById<TextView>(R.id.textView_inferior)).text = elemento?.descripcion
            (vistaRaiz.findViewById<ImageView>(R.id.imageView_imagen)).setImageResource(elemento!!.idImagen)
        }
        val a = "hola"
        return vistaRaiz
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("POSICION",mItem!!)
    }

}