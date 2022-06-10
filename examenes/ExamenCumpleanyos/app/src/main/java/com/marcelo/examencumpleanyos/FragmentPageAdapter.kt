package com.marcelo.examencumpleanyos

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class FragmentPageAdapter(fragment: FragmentActivity):FragmentStateAdapter(fragment) {



    override fun getItemCount()=3
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> FragmentCumple()
            1 -> FragmentRecyclerReservas()
            2 -> FragmentEtapas()
            else ->  FragmentCumple()
        }
    }

}