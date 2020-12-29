package com.shanu.hashgenerator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.shanu.hashgenerator.databinding.FragmentSuccessBinding


class successFragment : Fragment() {
    private var _binding:FragmentSuccessBinding?=null
    private val args:successFragmentArgs by navArgs()
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSuccessBinding.inflate(inflater, container, false)
        return binding.root

    }


}