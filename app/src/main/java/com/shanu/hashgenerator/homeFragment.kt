package com.shanu.hashgenerator

import android.os.Bundle
import android.view.*
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.shanu.hashgenerator.databinding.FragmentHomeBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class homeFragment : Fragment() {
    private var _binding:FragmentHomeBinding?=null
    private val binding get() = _binding!!
    private val homeViewModel:HomeViewModel by viewModels()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        setHasOptionsMenu(true)

        binding.generateButton.setOnClickListener {
            onGenerateClicked()
            navigateToSuccess(getHashData())
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

    private fun navigateToSuccess(hash:String){
        val directions = homeFragmentDirections.actionHomeFragmentToSuccessFragment(hash)
        findNavController().navigate(directions)
    }

    override fun onResume() {
        super.onResume()
        val hashAlgorithms = resources.getStringArray(R.array.hashAlgo)
        val arrayAdapter = ArrayAdapter(requireContext(),R.layout.drop_down,hashAlgorithms)
        binding.autoCompleteTextView.setAdapter(arrayAdapter)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.clearMenu){
            binding.plainText.text.clear()
            showSnackBar("Cleared")
            return true
        }
        return true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }

    private fun onGenerateClicked(){
        if(!binding.plainText.text.isEmpty()){
            lifecycleScope.launch{
                applyAnimation()
                navigateToSuccess(getHashData())
            }
        }else{
            showSnackBar("Field Empty")
        }

    }

    private fun showSnackBar(message:String){
        val snackBar = Snackbar.make(
                binding.rootLayout,
                message,
                Snackbar.LENGTH_SHORT
        )
        snackBar.setAction("Okay"){}
        snackBar.show()

    }

    private fun getHashData():String{
        val algorithm = binding.autoCompleteTextView.text.toString()
        val textToHash = binding.plainText.text.toString()
        return homeViewModel.getHash(textToHash,algorithm)

    }


}