package com.marcelo.examen2perros

import Animal
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.marcelo.examen2perros.databinding.FragmentEditarAnimalBinding


class EditarAnimalFragment : Fragment() {
    private var _binding: FragmentEditarAnimalBinding? = null
    private val binding get() = _binding!!
    lateinit var bdAdaptador: SQLiteAdapter
    private val model: ItemViewModel by activityViewModels()
    lateinit var textNombre: EditText
    lateinit var textRaza: EditText
    lateinit var textEdad: EditText
    lateinit var textDescripcion: EditText
    lateinit var checkSeleccionado: CheckBox
    lateinit var animal: Animal
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditarAnimalBinding.inflate(layoutInflater)
        bdAdaptador = SQLiteAdapter(binding.root.context)

        asignaDatos()
        binding.fab.setOnClickListener {
            guardarDatos()
        }

        return binding.root

    }

    private fun guardarDatos() {
        animal.nombre = textNombre.text.toString()
        animal.raza = textRaza.text.toString()
        animal.edad = textEdad.text.toString().toInt()
        animal.descripcion = textDescripcion.text.toString()
        if (checkSeleccionado.isChecked )
            animal.seleccionado = 1
        else
            animal.seleccionado = 0
        bdAdaptador.updateAnimal(animal)
        Navigation.findNavController(binding.root).navigate(R.id.action_global_recyclerFragment)

    }

    private fun asignaDatos() {
        animal = bdAdaptador.getAnimalById(model.getIdActual.value!!)!!

        textNombre = binding.textInputName.editText!!
        textRaza = binding.textInputRaza.editText!!
        textEdad = binding.textInputLayoutEdad.editText!!
        textDescripcion = binding.textInputLayoutDescripcion.editText!!
        checkSeleccionado = binding.checkSeleccionado
        textNombre.setText(animal.nombre?:"")
        textRaza.setText(animal.raza?:"")
        textEdad.setText(animal.edad.toString())
        textDescripcion.setText(animal.descripcion?:"")
        if (animal.seleccionado == 1 )
            checkSeleccionado.setChecked(true)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}