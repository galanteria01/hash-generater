package com.shanu.hashgenerator

import android.os.Bundle
import android.view.*
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.shanu.hashgenerator.databinding.FragmentHomeBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class homeFragment : Fragment() {
    private var _binding:FragmentHomeBinding?=null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        setHasOptionsMenu(true)
        val hashAlgor = resources.getStringArray(R.array.hashAlgo)
        val arrayAdapter = ArrayAdapter(requireContext(),R.layout.drop_down,hashAlgor)
        binding.autoCompleteTextView.setAdapter(arrayAdapter)
        binding.generateButton.setOnClickListener {
            lifecycleScope.launch{
                applyAnimation()
                navigateToSuccess()
            }
        }
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.home_menu,menu)
    }
    private suspend fun applyAnimation(){
        binding.generateButton.isClickable = false
        binding.tvTitle.animate().alpha(0f).duration = 400L
        binding.generateButton.animate().alpha(0f).duration = 400L
        binding.textInputLayout.animate()
                .alpha(0f)
                .translationXBy(1200f)
                .duration = 400L
        binding.plainText.animate()
                .alpha(0f)
                .translationXBy(-1200f)
                .duration = 400L
        delay(300L)

        binding.successBackground.animate().alpha(1f).duration = 600L
        binding.successBackground.animate().rotationBy(720f).duration = 600L
        binding.successBackground.animate().scaleXBy(900f).duration = 800L
        binding.successBackground.animate().scaleYBy(900f).duration = 800L

        delay(300L)
        binding.successImageView.animate().alpha(1f).duration = 1000L
        delay(1500L)
    }

    private fun navigateToSuccess(){
        findNavController().navigate(R.id.action_homeFragment_to_successFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }


}