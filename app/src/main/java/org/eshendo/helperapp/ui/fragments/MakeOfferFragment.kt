package org.eshendo.helperapp.ui.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.eshendo.helperapp.databinding.MakeOfferFragmentBinding

class MakeOfferFragment : Fragment() {

    private var binding: MakeOfferFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MakeOfferFragmentBinding.inflate(inflater)

        attachListeners()

        enterTransition = TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.slide_right)
        exitTransition  =  TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.slide_left)

        return binding!!.root
    }

    private fun attachListeners(){
        binding!!.back.setOnClickListener {
            requireActivity().onBackPressed()
        }

        binding!!.start.setOnClickListener {
            val t = binding!!.input.text.toString()
            sendEmail(t)
        }
    }

    private fun sendEmail(text: String){

        val mailto = "mailto:kon3gor@gmail.com" +
                "?subject=" + Uri.encode("Предложение функционала") +
                "&body=" + Uri.encode(text);

        val i = Intent(Intent.ACTION_SENDTO).apply {
            type = "message/rfc822"
            data = Uri.parse(mailto)
        }
        startActivity(Intent.createChooser(i, "Отправьте письмо"))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}