package com.shanu.hashgenerator

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.shanu.hashgenerator.databinding.FragmentSuccessBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class successFragment : Fragment() {
    private var _binding:FragmentSuccessBinding?=null
    private val args:successFragmentArgs by navArgs()
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSuccessBinding.inflate(inflater, container, false)

        binding.hashTextView.text = args.hashedValue
        binding.copyButton.setOnClickListener {
            onCopyClicked()
        }
        return binding.root
    }

    private fun onCopyClicked() {
        lifecycleScope.launch{
            copyToClickboard(args.hashedValue)
            applyAnimation()
        }
    }

    private fun copyToClickboard(text: String) {
        val clipboardManager = requireActivity().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clipData = ClipData.newPlainText("Encrypted text",text)
        clipboardManager.setPrimaryClip(clipData)
    }

    private suspend fun applyAnimation(){
        binding.include.messageBackground.animate().translationY(80f).duration = 200L
        binding.include.copyMessageText.animate().translationY(80f).duration = 200L
        delay(2000L)
        binding.include.messageBackground.animate().translationY(-80f).duration = 300L
        binding.include.copyMessageText.animate().translationY(-80f).duration = 300L
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}