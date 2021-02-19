package org.eshendo.helperapp.network

import okhttp3.MultipartBody
import org.eshendo.helperapp.model.DescriptionDto
import org.eshendo.helperapp.util.IA_KEY
import retrofit2.Call
import retrofit2.http.*

interface ImageAnalyzeService {

    @POST("vision/v3.1/analyze?visualFeatures=Description")
    @Multipart
    @Headers("Ocp-Apim-Subscription-Key: ${IA_KEY}")
    fun describe(@Part file: MultipartBody.Part) : Call<DescriptionDto>
}