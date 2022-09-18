package com.company.getbus

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.company.getbus.databinding.FragmentSheduleFrag3hBinding



class SheduleFrag3h : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSheduleFrag3hBinding.inflate(inflater)
        return binding.root

    }



    companion object {
        @JvmStatic
        fun newInstance() = SheduleFrag3h()

    }



}