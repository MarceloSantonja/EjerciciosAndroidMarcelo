package com.marcelo.b7agenda

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.marcelo.b7agenda.databinding.LayoutAddNewContactBinding

class FragmentNewContacto : Fragment(), View.OnClickListener {
    val RESPUESTA_PERMISOS_1 = 111 //solicita dos permisos (CAMERA)
    val RESPUESTA_PERMISOS_2 = 222 //solicita permiso (READ_EXTERNAL_STORAGE)
    private var _binding: LayoutAddNewContactBinding? = null
    private val binding get() = _binding!!
    private val model: ItemViewModel by activityViewModels()
    private lateinit var resultadoCamara: ActivityResultLauncher<Intent>
    private lateinit var resultadoGaleria: ActivityResultLauncher<Intent>
    private var limpiar = false
    lateinit  var registerPermisosTomarFoto:ActivityResultLauncher<String>
    lateinit  var registerPermisosStorage:ActivityResultLauncher<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerPermisosTomarFoto= registerForActivityResult(ActivityResultContracts.RequestPermission())
        {
            if(it ==true) tomarFoto()
        }
        registerPermisosStorage= registerForActivityResult(ActivityResultContracts.RequestPermission())
        {
            if(it ==true) tomarGaleria()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = LayoutAddNewContactBinding.inflate(inflater, container, false)
        binding.imagen.setOnClickListener {
            registerPermisosTomarFoto.launch(Manifest.permission.CAMERA)
        }
        binding.imagen.setOnLongClickListener {
            registerPermisosStorage.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
            true
        }
        creaContratos()
        binding.fab.setOnClickListener(this)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        if (limpiar) limpiarSalida()
    }

    override fun onClick(view: View) {
        if (view.getId() === R.id.fab) {
            if (chequearEntrada())
                guardarContacto(crearContacto())
            limpiar = true
        }
    }

    private fun limpiarSalida() {
        binding.nombreText.setText("")
        binding.apellidosText.setText("")
        binding.phoneText.setText("")
        binding.mailText.setText("")
        binding.imagen.setImageBitmap(
            BitmapFactory.decodeResource(
                getResources(),
                R.drawable.user_customer_person_face_add
            )
        )
        binding.checkFamilia.setChecked(false)
        binding.checkAmigos.setChecked(false)
        binding.checkTrabajo.setChecked(false)
    }

    private fun crearContacto(): Contacto {
        val contacto = Contacto()
        contacto.nombre = binding.nombreText.text.toString()
        contacto.apellido = binding.apellidosText.text.toString()
        contacto.email = binding.mailText.text.toString()
        contacto.telefono = binding.phoneText.text.toString()
        val bmdrawable =  binding.imagen.drawable?.let{it as BitmapDrawable }
        contacto.foto = bmdrawable?.bitmap
        contacto.familiar = binding.checkFamilia.isChecked()
        contacto.amigos = binding.checkAmigos.isChecked()
        contacto.trabajo = binding.checkTrabajo.isChecked()
        return contacto
    }

    private fun guardarContacto(contacto: Contacto) = contacto?.let { it ->
        model.setItem(it)
    }

    private fun chequearEntrada(): Boolean {
        if (!binding.nombreText.text.toString().isEmpty() && !binding.phoneText.text.toString()
                .isEmpty()
        ) {
            if (binding.checkAmigos.isChecked() || binding.checkFamilia.isChecked() || binding.checkTrabajo.isChecked()) return true
            else Snackbar.make(
                binding.root, "Como mínimo el contacto debe pertenecer a un grupo",
                BaseTransientBottomBar.LENGTH_LONG
            ).show()
        } else Snackbar.make(
            binding.root,
            "Como mínimo el contacto debe tener nombre y telefono",
            BaseTransientBottomBar.LENGTH_LONG
        ).show()
        return false
    }

    //region FotosGaleria
    fun tomarFoto() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        resultadoCamara.launch(cameraIntent)
    }

    fun tomarGaleria() {
        val cameraIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        resultadoGaleria.launch(cameraIntent)
    }

    fun creaContratos() {
        resultadoCamara =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    binding.imagen.setImageBitmap(result.data?.extras?.get("data") as Bitmap)
                    limpiar = false
                }
            }
        resultadoGaleria =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    binding.imagen.setImageURI(result.data?.data)
                    limpiar = false
                }
            }
    }
}
