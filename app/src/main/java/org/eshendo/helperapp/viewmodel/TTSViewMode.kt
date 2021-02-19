package org.eshendo.helperapp.viewmodel

import androidx.lifecycle.ViewModel
import com.microsoft.cognitiveservices.speech.SpeechConfig
import com.microsoft.cognitiveservices.speech.SpeechSynthesizer
import org.eshendo.helperapp.util.COUNTRY
import org.eshendo.helperapp.util.TTS_KEY

class TTSViewMode : ViewModel(){

    fun voiceOver(text: String){
        val config = SpeechConfig.fromSubscription(TTS_KEY, COUNTRY)

        config.speechSynthesisVoiceName = "Microsoft Server Speech Text to Speech Voice (ru-RU, DariyaNeural)"
        config.speechSynthesisLanguage = "ru-RU"

        val synthesizer = SpeechSynthesizer(config)
        synthesizer.SpeakTextAsync(text)
    }
}