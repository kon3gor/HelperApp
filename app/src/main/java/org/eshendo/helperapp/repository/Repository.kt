package org.eshendo.helperapp.repository

import okhttp3.MultipartBody
import org.eshendo.helperapp.model.DescriptionDto
import org.eshendo.helperapp.model.TranslationDto
import org.eshendo.helperapp.network.ImageAnalyzeService
import org.eshendo.helperapp.network.TranslationService
import org.eshendo.helperapp.network.response.TranslationResponse
import retrofit2.Call
import retrofit2.http.Multipart
import javax.inject.Inject

class Repository @Inject constructor(
    private val imageAnalyzeService: ImageAnalyzeService,
    private val translationService: TranslationService
) {

    fun describeImage(part: MultipartBody.Part) : Call<DescriptionDto> {
        return imageAnalyzeService.describe(part)
    }

    fun translate(body: List<TranslationDto>) : Call<List<TranslationResponse>>{
        return translationService.translate(body)
    }
}