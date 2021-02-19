package org.eshendo.helperapp.network

import org.eshendo.helperapp.model.TranslationDto
import org.eshendo.helperapp.util.T_KEY
import org.eshendo.helperapp.network.response.TranslationResponse
import retrofit2.Call
import retrofit2.http.*

interface TranslationService {
    @POST("translator/text/v3.0/translate?to=ru")
    @Headers("Ocp-Apim-Subscription-Key: $T_KEY")
    fun translate(@Body body: List<TranslationDto>) : Call<List<TranslationResponse>>
}