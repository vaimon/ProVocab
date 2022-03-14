package ru.vaimon.provocab.screens.home

import okhttp3.ResponseBody
import org.jsoup.Jsoup
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.vaimon.provocab.models.CambridgeDefinition
import ru.vaimon.provocab.services.TranslationService

class MainRepository(val mPresenter: MainPresenter) : MainContract.Repository {
    val translationService = TranslationService.create()

    fun parseCambridgeDictionary(response: Response<ResponseBody>): List<CambridgeDefinition> {
        val html = Jsoup.parse(response.body()?.string())
        val selected = html.select("div.def-block.ddef_block")
        val res = mutableListOf<CambridgeDefinition>()
        for (meaning in selected){
            val explanation = meaning.select("div.def.ddef_d.db").first().text()
            val example = meaning.select("div.examp.dexamp").first()?.text() ?: ""
            res.add(CambridgeDefinition(explanation,example))
        }
        return res
    }

    override fun translateWord(word: String) {
        translationService.getCambridgeWordDefinition(word)
            .enqueue(object : Callback<ResponseBody> {
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    if (response.isSuccessful) {
                        mPresenter.onWordSearchSuccess(parseCambridgeDictionary(response))
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
    }


}