package com.shanu.hashgenerator

import android.os.Bundle
import android.view.*
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.shanu.hashgenerator.databinding.FragmentHomeBinding


class homeFragment : Fragment() {
    private var _binding:FragmentHomeBinding?=null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        setHasOptionsMenu(true)
        val hashAlgo = resources.getStringArray(R.array.hashAlgo)
        val arrayAdapter = ArrayAdapter(requireContext(),R.layout.drop_down,hashAlgo)
        binding.autoCompleteTextView.setAdapter(arrayAdapter)
        binding.generateButton.setOnClickListener {
            applyAnimation()
        }
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.home_menu,menu)
    }
    fun applyAnimation(){
        binding.tvTitle.animate().alpha(0f).duration = 400L
        binding.generateButton.animate().alpha(0f).duration = 400L
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}