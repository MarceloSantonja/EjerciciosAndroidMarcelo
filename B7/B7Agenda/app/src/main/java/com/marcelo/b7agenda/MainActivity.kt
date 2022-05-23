package com.marcelo.b7agenda

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.marcelo.b7agenda.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val pagerAdapter = FragmentPageAdapter(this)
        binding.ViewPager.adapter = pagerAdapter
    }

    override fun onBackPressed() {
        if ( binding.ViewPager.currentItem == 0)  super.onBackPressed()
        else  binding.ViewPager.currentItem = binding.ViewPager.currentItem - 1
    }

}