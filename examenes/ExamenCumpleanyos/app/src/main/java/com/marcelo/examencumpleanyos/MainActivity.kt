package com.marcelo.examencumpleanyos

import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toolbar
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.marcelo.examencumpleanyos.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbarPrincipal)
        viewPagerCreacion()
    }


    fun viewPagerCreacion() {
        viewPager = binding.viewPager
        val pageAdapter = FragmentPageAdapter(this)
        viewPager.adapter = pageAdapter
        val tabLayout = binding.tabs
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Reservar Cumpleaños"
                1 -> tab.text = "Reservas"
                2 -> tab.text = "Etapas"
            }
        }.attach()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_overflow, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        var idImagen: Int = 0
        when (item.itemId) {
            R.id.itemGlovos -> idImagen = R.drawable.globos
            R.id.itemTarta -> idImagen = R.drawable.tarta
            R.id.itemFelicidades -> idImagen = R.drawable.felicidades
            else -> idImagen = R.drawable.globos
        }
        binding.viewPager.background = resources.getDrawable(idImagen, theme)
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if (viewPager.currentItem == 0) super.onBackPressed()
        else viewPager.currentItem = viewPager.currentItem - 1
    }
}