package org.eshendo.helperapp.ui.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import org.eshendo.helperapp.R
import org.eshendo.helperapp.databinding.WannaMakeOfferDialogBinding

class WannaMakeOfferDialog : DialogFragment() {

    private var binding: WannaMakeOfferDialogBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = WannaMakeOfferDialogBinding.inflate(inflater)

        attachListeners()

        dialog?.let { it.window?.let {w ->
            w.apply {
                setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                requestFeature(Window.FEATURE_NO_TITLE)
            }
        } }

        return binding!!.root
    }

    private fun attachListeners(){
        binding!!.back.setOnClickListener {
            dismiss()
        }

        binding!!.makeOffer.setOnClickListener {
            findNavController().navigate(R.id.action_wannaMakeOfferDialog_to_makeOfferFragment)
            dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}