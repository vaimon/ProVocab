package ru.vaimon.provocab.services

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TranslationService {
    @GET("https://dictionary.cambridge.org/dictionary/english/{word}")
    suspend fun getCambridgeWordDefinition(@Path("word") word: String): Response<ResponseBody>

    @GET("https://wooordhunt.ru/word/{word}")
    suspend fun getWordHuntTranslation(@Path("word") word: String): Response<ResponseBody>

    companion object {
        fun create(): TranslationService {
            return Retrofit.Builder().baseUrl("https://yandex.ru/").build().create(TranslationService::class.java)
        }
    }
}