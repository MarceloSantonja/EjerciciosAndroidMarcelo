package com.marcelo.examen2perros

import Animal
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.navigation.NavigationView
import com.marcelo.examen2perros.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),
    NavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding: ActivityMainBinding
    lateinit var drawer_layout: DrawerLayout
    lateinit var toggle: ActionBarDrawerToggle
    lateinit var toolbar: MaterialToolbar
    lateinit var navigationView: NavigationView
    lateinit var  bdAdaptador: BDAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navigationView = binding.navigationView
        navigationView.setNavigationItemSelectedListener(this)
        drawer_layout = binding.drawerLayout
        toolbar = binding.topAppBar
        setSupportActionBar(toolbar)
        toggle = ActionBarDrawerToggle(
            this, drawer_layout,
            binding.topAppBar, R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)

        bdAdaptador = BDAdapter(binding.root.context)
        val listaAnimal = cargaDatos()
        bdAdaptador.insertarDatosInicio(listaAnimal)






    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        TODO("Not yet implemented")
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        toggle.syncState()
    }

    override fun onConfigurationChanged(
        newConfig: android.content.
        res.Configuration
    ) {
        if (newConfig != null) {
            super.onConfigurationChanged(newConfig)
        }
        toggle.onConfigurationChanged(newConfig)
    }


    fun cargaDatos(): ArrayList<Animal> {
        var datos = ArrayList<Animal>()

        for (i in 1..20) {
            val uri = "@drawable/imagen$i"

            val imageResource = resources.getIdentifier(uri, null, packageName)
            val imagen = BitmapFactory.decodeResource(resources, imageResource)
            datos.add(Animal(imagen))
        }
        setTipoAnimal(datos)
        return datos
    }

    fun setTipoAnimal(animales: ArrayList<Animal>) {
        animales[0].tipo = 1;animales[2].tipo = 1;animales[6].tipo = 1;animales[9].tipo =
            1;animales[13].tipo = 1;animales[16].tipo = 1;
        animales[5].tipo = 2; animales[8].tipo = 2; animales[11].tipo = 2; animales[11].tipo =
            2; animales[17].tipo = 2;
    }

}