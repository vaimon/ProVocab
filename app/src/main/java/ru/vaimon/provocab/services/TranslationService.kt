package ru.vaimon.provocab.services

import okhttp3.Response
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path

interface TranslationService {
    @GET("https://dictionary.cambridge.org/dictionary/english/{word}")
    fun getCambridgeWordDefinition(@Path("word") word: String): Call<ResponseBody>

    companion object {
        fun create(): TranslationService {
            return Retrofit.Builder().baseUrl("https://yandex.ru/").build().create(TranslationService::class.java)
        }
    }
}