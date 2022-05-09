package com.marcelo.pruebapajaros

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class DialogoEliminar : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        super.onCreateDialog(savedInstanceState)
        val builder= MaterialAlertDialogBuilder(requireActivity())
        if(arguments != null) {
            val position: Int = requireArguments().getInt("POSICION")
            builder.setMessage("Deseas ELiminar el item?")
                .setTitle("AVISO")
                .setPositiveButton("OK",
                    DialogInterface.OnClickListener { dialogo, id ->
                        MainActivity.datos!!.removeAt(position)
                        FragmentLista.adapter!!.notifyItemRemoved(position)

                    })
        }
        return builder.create()
    }

}