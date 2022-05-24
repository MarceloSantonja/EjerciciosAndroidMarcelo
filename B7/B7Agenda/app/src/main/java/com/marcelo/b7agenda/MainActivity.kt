package com.marcelo.b7agenda

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.marcelo.b7agenda.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val pagerAdapter = FragmentPageAdapter(this)
        val viewPager = binding.ViewPager
        viewPager.adapter = pagerAdapter
        val tabLayout = binding.tabs
        TabLayoutMediator(tabLayout,viewPager){ tab,position->
            when(position){
                0-> tab.text = "Nuevo Contacto"
                1-> tab.text = "Editar Contacto"
            }
        }.attach()




    }

    override fun onBackPressed() {
        if ( binding.ViewPager.currentItem == 0)  super.onBackPressed()
        else  binding.ViewPager.currentItem = binding.ViewPager.currentItem - 1
    }

}