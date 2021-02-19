package org.eshendo.helperapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.microsoft.cognitiveservices.speech.SourceLanguageConfig
import com.microsoft.cognitiveservices.speech.SpeechConfig
import com.microsoft.cognitiveservices.speech.SpeechRecognizer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.eshendo.helperapp.util.COUNTRY
import org.eshendo.helperapp.util.TTS_KEY

class STTViewModel : ViewModel(){

    private val _recognizeResult = MutableLiveData<String?>()
    val recognizeResult: LiveData<String?> get() = _recognizeResult

    fun recognize(){
        val config = SpeechConfig.fromSubscription(TTS_KEY, COUNTRY)
        val sourceLanguageConfig = SourceLanguageConfig.fromLanguage("ru-RU")
        val recognizer = SpeechRecognizer(config, sourceLanguageConfig)

        viewModelScope.launch {
            val res = recognizer.recognizeOnceAsync()
            _recognizeResult.value = res.get().text
        }
    }
}