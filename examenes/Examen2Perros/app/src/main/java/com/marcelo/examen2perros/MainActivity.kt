package com.marcelo.examen2perros

import Animal
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.navigation.NavigationView
import com.marcelo.examen2perros.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),
    NavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding: ActivityMainBinding
    lateinit var toggle: ActionBarDrawerToggle
    val model: ItemViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        crearMenu()
        cargaDatosSQLite()


    }

    private fun cargaDatosSQLite() {
        val bdAdaptador = SQLiteAdapter(binding.root.context)
        val listaAnimal = cargaDatos()
        val numeroFilasEnBD = bdAdaptador.leerDatosCodigo(null)!!.count
        if (numeroFilasEnBD == 0)
            bdAdaptador.insertarDatosInicio(listaAnimal)
    }

    private fun crearMenu() {
        val navigationView = binding.navigationView
        navigationView.setNavigationItemSelectedListener(this)
        val drawer_layout = binding.drawerLayout
        val toolbar = binding.topAppBar
        setSupportActionBar(toolbar)
        toggle = ActionBarDrawerToggle(
            this, drawer_layout,
            binding.topAppBar, R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.contenedor) as NavHostFragment
        val navController = navHostFragment.navController

        when (item.itemId) {
            R.id.nav_todos -> {
                navController.navigate(R.id.action_global_recyclerFragment)
                model.setTipoSeleccionado(null)
            }
            R.id.nav_Canarios -> {
                navController.navigate(R.id.action_global_recyclerFragment)
                model.setTipoSeleccionado(2)

            }
            R.id.nav_Gatos -> {
                navController.navigate(R.id.action_global_recyclerFragment)
                model.setTipoSeleccionado(1)

            }
            R.id.nav_perros -> {
                navController.navigate(R.id.action_global_recyclerFragment)
                model.setTipoSeleccionado(0)

            }
            R.id.nav_Subir -> {
                navController.navigate(R.id.action_global_dialogoPersonalizado)
            }
        }
        return true
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