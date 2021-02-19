package org.eshendo.helperapp.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.eshendo.helperapp.network.ImageAnalyzeService
import org.eshendo.helperapp.network.TranslationService
import org.eshendo.helperapp.util.URL
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private val retrofit = Retrofit.Builder()
        .baseUrl(URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideImageService(): ImageAnalyzeService{
        return retrofit.create(ImageAnalyzeService::class.java)
    }

    @Provides
    @Singleton
    fun provideTranslationService(): TranslationService{
        return retrofit.create(TranslationService::class.java)
    }
}