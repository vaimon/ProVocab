package ru.vaimon.provocab.screens.home

import android.util.Log
import kotlinx.coroutines.*
import okhttp3.ResponseBody
import org.jsoup.Jsoup
import retrofit2.Response
import ru.vaimon.provocab.models.CambridgeDefinition
import ru.vaimon.provocab.services.TranslationService

class MainRepository(val mPresenter: MainPresenter) : MainContract.Repository {
    val translationService = TranslationService.create()

    fun parseCambridgeDictionary(response: Response<ResponseBody>): List<CambridgeDefinition> {
        val html = Jsoup.parse(response.body()?.string())
        val selected = html.select("div.def-block.ddef_block")
        val res = mutableListOf<CambridgeDefinition>()
        for (meaning in selected) {
            val explanation = meaning.select("div.def.ddef_d.db").first().text()
            val example = meaning.select("div.examp.dexamp").first()?.text() ?: ""
            res.add(CambridgeDefinition(explanation, example))
        }
        return res
    }

    override fun translateWord(word: String) {
        val handler = CoroutineExceptionHandler { coroutineContext, throwable ->
            Log.e("PROVOCAB_COROUTINES", throwable.message ?: "Something strange")
        }
        CoroutineScope(Dispatchers.Main).launch(handler) {
            val cambridgeResponseDeferred = async(Dispatchers.IO) {
                translationService.getCambridgeWordDefinition(word)
            }
            try {
                val cambridgeResponse = cambridgeResponseDeferred.await()
                if (cambridgeResponse.isSuccessful) {
                    mPresenter.onWordSearchSuccess(parseCambridgeDictionary(cambridgeResponse))
                } else {
                    mPresenter.onWordSearchFailure("Cambridge answer: ${cambridgeResponse.code()}")
                }
            } catch (ex: Exception) {
                mPresenter.onWordSearchFailure("An error occured: $ex")
                return@launch
            }

        }
    }


}