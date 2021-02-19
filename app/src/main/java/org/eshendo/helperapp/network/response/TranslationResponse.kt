package org.eshendo.helperapp.network.response

import org.eshendo.helperapp.model.TranslationDto

data class TranslationResponse(
    val translations: List<TranslationDto>
)