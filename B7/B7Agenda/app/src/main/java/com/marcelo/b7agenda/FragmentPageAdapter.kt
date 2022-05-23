package com.marcelo.b7agenda

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class FragmentPageAdapter(fragment: FragmentActivity): FragmentStateAdapter(fragment) {
    override fun getItemCount()=2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> FragmentNewContacto()
            1 -> FragmentEditarContacto()
            else ->FragmentNewContacto()
        }
    }
}