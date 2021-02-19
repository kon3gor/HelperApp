package org.eshendo.helperapp.ui.fragments

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import dagger.hilt.android.AndroidEntryPoint
import org.eshendo.helperapp.databinding.SpeechToTextFragmentBinding
import org.eshendo.helperapp.viewmodel.STTViewModel

@AndroidEntryPoint
class SpeechToTextFragment : Fragment() {

    private var binding: SpeechToTextFragmentBinding? = null
    private val viewModel: STTViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SpeechToTextFragmentBinding.inflate(inflater)

        attachListeners()
        observe()

        enterTransition = TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.slide_right)
        exitTransition  =  TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.slide_left)

        return binding!!.root
    }

    private fun attachListeners(){
        binding!!.back.setOnClickListener {
            requireActivity().onBackPressed()
        }

        binding!!.start.setOnClickListener {
            startRecognition()
        }
    }

    private fun observe(){
        viewModel.recognizeResult.observe(viewLifecycleOwner){
            it?.let {
               setResult(it)
            }
        }
    }


    private fun startRecognition(){
        viewModel.recognize()
    }

    private fun setResult(text: String){
        requireActivity().runOnUiThread {
            binding!!.text.text = text
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}