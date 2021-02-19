package org.eshendo.helperapp.ui.fragments

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.eshendo.helperapp.R
import org.eshendo.helperapp.databinding.MainFragmentBinding

class MainFragment : Fragment() {

    private var binding: MainFragmentBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val fP = ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE ) != PackageManager.PERMISSION_GRANTED
        val sP = ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE ) != PackageManager.PERMISSION_GRANTED
        val tP = ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.RECORD_AUDIO ) != PackageManager.PERMISSION_GRANTED
        if (fP || sP || tP){
            ActivityCompat.requestPermissions(requireActivity(),
                arrayOf(
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.RECORD_AUDIO
                ),
                0)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MainFragmentBinding.inflate(inflater)

        attachListeners()

        return binding!!.root
    }

    private fun attachListeners(){
        binding!!.stt.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_speechToTextFragment)
        }

        binding!!.tts.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_textToSpeechFragment)
        }

        binding!!.di.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_describePictureFragment)
        }

        binding!!.ftt.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_wannaMakeOfferDialog)
        }

        binding!!.makeOffer.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_makeOfferFragment)
        }

        binding!!.about.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_aboutFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}