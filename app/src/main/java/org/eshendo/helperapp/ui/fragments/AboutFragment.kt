package org.eshendo.helperapp.ui.fragments

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.eshendo.helperapp.databinding.AboutFragmentBinding
import org.eshendo.helperapp.util.text

class AboutFragment : Fragment() {

    private var binding: AboutFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AboutFragmentBinding.inflate(inflater)

        binding!!.about.text = text
        binding!!.back.setOnClickListener {
            requireActivity().onBackPressed()
        }

        enterTransition = TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.slide_right)
        exitTransition  =  TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.slide_left)

        return binding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}