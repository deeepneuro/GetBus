package com.company.getbus

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.company.getbus.databinding.FragmentSheduleFrag1hBinding


class SheduleFrag1h : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSheduleFrag1hBinding.inflate(inflater)
        return binding.root

    }



    companion object {
        @JvmStatic
        fun newInstance() = SheduleFrag1h()

    }

}