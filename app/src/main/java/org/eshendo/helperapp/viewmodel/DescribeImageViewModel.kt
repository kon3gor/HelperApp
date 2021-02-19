package org.eshendo.helperapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.eshendo.helperapp.model.DescriptionDto
import org.eshendo.helperapp.model.TranslationDto
import org.eshendo.helperapp.network.response.TranslationResponse
import org.eshendo.helperapp.repository.Repository
import org.eshendo.helperapp.util.RetrofitCallback
import java.io.File
import javax.inject.Inject

@HiltViewModel
class DescribeImageViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _describeResponse = MutableLiveData<String?>()
    val describeResponse: LiveData<String?> get() = _describeResponse

    private val _translationResponse = MutableLiveData<String?>()
    val translationResponse: LiveData<String?> get() = _translationResponse

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> get() = _error

    fun describeImage(file: File){
        val body = RequestBody.create(MediaType.parse("image/*"), file)
        val photo = MultipartBody.Part.createFormData("file", file.name, body)
        repository.describeImage(photo).enqueue(RetrofitCallback<DescriptionDto>({
            val description = it.description.captions[0].text
            _describeResponse.value = description
            _error.value = null
        }, {
            _error.value = it
            _describeResponse.value = null
        }))
    }

    fun translate(t: String){
        val body = listOf(TranslationDto(t))
        repository.translate(body).enqueue(RetrofitCallback<List<TranslationResponse>>({
            val translated = it[0].translations[0].text
            _translationResponse.value = translated
            _error.value = null
        }, {
            _error.value = it
            _translationResponse.value = null
        }))
    }
}