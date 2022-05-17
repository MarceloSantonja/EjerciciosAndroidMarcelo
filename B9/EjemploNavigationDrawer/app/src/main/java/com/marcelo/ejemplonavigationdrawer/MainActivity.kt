package com.marcelo.ejemplonavigationdrawer

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.navigation.NavigationView
import com.marcelo.ejemplonavigationdrawer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var drawer_layout: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var toolbar: MaterialToolbar
    private lateinit var navigationView:NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navigationView= binding.navigationView
        drawer_layout = binding.drawerLayout
        toolbar=binding.topAppBar

        navigationView.setNavigationItemSelectedListener(this)
        setSupportActionBar(toolbar)
        toggle = ActionBarDrawerToggle(this,drawer_layout,R.string.navivigation_open,R.string.navivigation_close)
        drawer_layout.addDrawerListener(toggle)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    override fun onPostCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onPostCreate(savedInstanceState, persistentState)
        toggle.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        if (newConfig !=null){
            super.onConfigurationChanged(newConfig)
        }
        toggle.onConfigurationChanged(newConfig)

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        var s=""
        when (id) {
            R.id.nav_camera -> s="CAMARA"
            R.id.nav_gallery-> s="GALLERY"
            R.id.nav_manage-> s="TOOLS"
            R.id.nav_send-> s="SEND"
            R.id.nav_share-> s="SHARE"
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        Toast.makeText(applicationContext,"Pulsaste la opción "+s,
            Toast.LENGTH_SHORT).show()
        return true
    }


}