package org.eshendo.helperapp.ui.fragments

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.microsoft.cognitiveservices.speech.SpeechConfig
import com.microsoft.cognitiveservices.speech.SpeechSynthesizer
import org.eshendo.helperapp.databinding.TextToSpeechFragmentBinding
import org.eshendo.helperapp.util.COUNTRY
import org.eshendo.helperapp.util.TTS_KEY
import org.eshendo.helperapp.viewmodel.TTSViewMode

class TextToSpeechFragment : Fragment() {

    private var binding: TextToSpeechFragmentBinding? = null
    private val viewModel: TTSViewMode by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = TextToSpeechFragmentBinding.inflate(inflater)

        attachListeners()

        enterTransition = TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.slide_right)
        exitTransition  =  TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.slide_left)

        return binding!!.root
    }

    private fun voiceOverText(text: String){
        viewModel.voiceOver(text)
    }

    private fun attachListeners(){
        binding!!.back.setOnClickListener {
            requireActivity().onBackPressed()
        }

        binding!!.start.setOnClickListener {
            voiceOverText(binding!!.input.text.toString())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}