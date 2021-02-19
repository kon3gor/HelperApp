package org.eshendo.helperapp.ui.dialog

import android.Manifest
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.core.app.ActivityCompat
import androidx.fragment.app.DialogFragment
import org.eshendo.helperapp.databinding.AppCannotWorkDialogBinding

class AppCannotWorkDialog : DialogFragment() {

    private var binding: AppCannotWorkDialogBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AppCannotWorkDialogBinding.inflate(inflater)
        dialog?.let {
            it.window?.let {w ->
            w.apply {
                setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                requestFeature(Window.FEATURE_NO_TITLE)
            }
        } }

        isCancelable = false

        attachListeners()

        return binding!!.root
    }

    private fun attachListeners(){
        binding!!.back.setOnClickListener {
            requireActivity().finish()
        }

        binding!!.give.setOnClickListener {
            ActivityCompat.requestPermissions(requireActivity(),
                arrayOf(
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.RECORD_AUDIO
                ),
                0)
            dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}