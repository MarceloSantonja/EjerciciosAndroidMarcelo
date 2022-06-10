package com.marcelo.b7agenda

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.marcelo.b7agenda.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    companion object {


        fun listaContactos(context: Context) {

            var listaContactos = arrayListOf<Contacto>(
                Contacto(
                    0,
                    "Adrian",
                    "Gonzalez Jordan",
                    "665200454",
                    "adrian@hotmail.com",
                    BitmapFactory.decodeResource(
                        context.resources,
                        R.drawable.user_customer_person_face_add,
                        null
                    )
                ),Contacto(
                    1,
                    "Alberto",
                    "Gonzalez Jordan",
                    "665200454",
                    "adrian@hotmail.com",
                    BitmapFactory.decodeResource(
                        context.resources,
                        R.drawable.user_customer_person_face_add,
                        null
                    )
                ),Contacto(
                    2,
                    "Alejandro",
                    "Gonzalez Jordan",
                    "665200454",
                    "adrian@hotmail.com",
                    BitmapFactory.decodeResource(
                        context.resources,
                        R.drawable.user_customer_person_face_add,
                        null
                    )
                ),Contacto(
                    3,
                    "Carlos",
                    "Gonzalez Jordan",
                    "665200454",
                    "adrian@hotmail.com",
                    BitmapFactory.decodeResource(
                        context.resources,
                        R.drawable.user_customer_person_face_add,
                        null
                    )
                ),Contacto(
                    4,
                    "David",
                    "Gonzalez Jordan",
                    "665200454",
                    "adrian@hotmail.com",
                    BitmapFactory.decodeResource(
                        context.resources,
                        R.drawable.user_customer_person_face_add,
                        null
                    ))
                )

        }
    }

}

