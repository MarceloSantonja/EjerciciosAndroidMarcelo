package com.marcelo.ejmenuoverflow

import android.content.ClipData
import android.graphics.Color.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import androidx.core.view.forEach
import com.marcelo.ejmenuoverflow.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.buttton.setOnClickListener {
            showPopup(binding.buttton)
        }
        //ContextMenu
        registerForContextMenu(binding.button)
        registerForContextMenu(binding.text)
    }
    // PoPMenu
    private fun showPopup(view: View) {
        val popup = PopupMenu(this, view)
        popup.inflate(R.menu.menu_pop)
        popup.setOnMenuItemClickListener { item: MenuItem? ->
            when (item!!.itemId) {
                R.id.opMail -> {
                    Toast.makeText(this@MainActivity, item.title, Toast.LENGTH_SHORT)
                        .show()
                }
                R.id.opSms -> {
                    Toast.makeText(this@MainActivity, item.title, Toast.LENGTH_SHORT)
                        .show()
                }
            }
            true
        }
        popup.show()
    }



    // overflowMenu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.overflow_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        Toast.makeText(this,"Has pulsado ${item.toString()}" , Toast.LENGTH_SHORT).show()
        return true
    }
        //ContextMenu
    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        if (v!!.id == R.id.text) {
            menuInflater.inflate(R.menu.menu_contextual_textview, menu)
        }
        if (v!!.id == R.id.button) {
            menuInflater.inflate(R.menu.menu_contextual_button, menu)
        }
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.op12 -> binding.text.textSize = 12F
            R.id.op16 -> binding.text.textSize = 16F
            R.id.op20 -> binding.text.textSize = 20F
            R.id.op24 -> binding.text.textSize = 24F
            R.id.opAzul -> binding.button.setBackgroundColor(BLUE)
            R.id.opRojo -> binding.button.setBackgroundColor(RED)
            R.id.opVerde -> binding.button.setBackgroundColor(GREEN)
        }
        return super.onContextItemSelected(item)
    }



}