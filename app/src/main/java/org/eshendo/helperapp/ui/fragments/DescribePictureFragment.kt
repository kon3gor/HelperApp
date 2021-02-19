package org.eshendo.helperapp.ui.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.microsoft.cognitiveservices.speech.SpeechConfig
import com.microsoft.cognitiveservices.speech.SpeechSynthesizer
import dagger.hilt.android.AndroidEntryPoint
import org.eshendo.helperapp.databinding.DescribePictureFragmentBinding
import org.eshendo.helperapp.util.COUNTRY
import org.eshendo.helperapp.util.TTS_KEY
import org.eshendo.helperapp.viewmodel.DescribeImageViewModel
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.jvm.Throws

@AndroidEntryPoint
class DescribePictureFragment : Fragment() {

    companion object{
        const val GALLERY = 1
        const val CAMERA = 2
    }

    private var binding: DescribePictureFragmentBinding? = null
    private val viewModel: DescribeImageViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DescribePictureFragmentBinding.inflate(inflater)

        enterTransition = TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.slide_right)
        exitTransition  =  TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.slide_left)

        attachListeners()
        observe()

        return binding!!.root
    }

    private fun observe(){
        viewModel.describeResponse.observe(viewLifecycleOwner){
            it?.let {
                translate(it)
            }
        }

        viewModel.translationResponse.observe(viewLifecycleOwner){
            it?.let {
                voice(it)
            }
        }

        viewModel.error.observe(viewLifecycleOwner){
            it?.let {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun translate(t: String){
        viewModel.translate(t)
    }

    private fun voice(t: String){
        val config = SpeechConfig.fromSubscription(TTS_KEY, COUNTRY)
        config.speechSynthesisVoiceName = "Microsoft Server Speech Text to Speech Voice (ru-RU, DariyaNeural)"
        config.speechSynthesisLanguage = "ru-RU"
        val synthesizer = SpeechSynthesizer(config)
        synthesizer.SpeakTextAsync(t)
    }

    private fun openGallery(){
        val getterIntent = Intent(Intent.ACTION_GET_CONTENT).apply {
            type = "image/*"
        }
        val pickIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)

        val combination = Intent.createChooser(getterIntent, "Выберите изображение").apply {
            putExtra(Intent.EXTRA_INITIAL_INTENTS, arrayListOf(pickIntent))
        }

        startActivityForResult(combination, GALLERY)
    }

    private var photo: File? = null

    @Throws(IOException::class)
    private fun createImageFile(): File {
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir: File? = requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            "JPEG_${timeStamp}_",
            ".jpg",
            storageDir
        )
    }

    private fun takePhoto(){
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(requireContext().packageManager)?.also {
                photo = try {
                    createImageFile()
                } catch (ex: IOException) {
                    null
                }
                photo?.also {
                    val photoURI: Uri = FileProvider.getUriForFile(
                        requireContext(),
                        "com.example.android.fileprovider",
                        it
                    )
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    startActivityForResult(takePictureIntent, CAMERA)
                }
            }
        }
    }

    private fun attachListeners(){
        binding!!.back.setOnClickListener {
            requireActivity().onBackPressed()
        }

        binding!!.gallery.setOnClickListener {
            openGallery()
        }

        binding!!.takePhoto.setOnClickListener {
            takePhoto()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == AppCompatActivity.RESULT_OK && requestCode == GALLERY){
            val inputStream = requireContext().contentResolver.openInputStream(data!!.data!!)
            val file = File(requireContext().filesDir, "image.png")
            if (!file.exists()) file.createNewFile()
            val out = FileOutputStream(file)
            out.write(inputStream!!.readBytes())
            out.flush()
            out.close()

            viewModel.describeImage(file)
        }else if (resultCode == AppCompatActivity.RESULT_OK && requestCode == CAMERA){
            viewModel.describeImage(photo!!)
        }
    }
}