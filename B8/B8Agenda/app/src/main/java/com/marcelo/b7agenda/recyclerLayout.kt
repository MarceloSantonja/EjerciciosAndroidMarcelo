package com.marcelo.b7agenda

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.marcelo.b7agenda.databinding.FragmentRecyclerLayoutBinding
import java.util.zip.Inflater


class recyclerLayout : Fragment() {

    lateinit var  binding: FragmentRecyclerLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentRecyclerLayoutBinding.inflate(inflater,container,false)


        return binding.root

    }

}