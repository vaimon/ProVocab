package ru.vaimon.provocab.screens.home

import android.util.Log
import kotlinx.coroutines.*
import okhttp3.ResponseBody
import org.jsoup.Jsoup
import org.jsoup.nodes.TextNode
import retrofit2.Response
import ru.vaimon.provocab.models.CambridgeDefinition
import ru.vaimon.provocab.models.Example
import ru.vaimon.provocab.models.Translation
import ru.vaimon.provocab.services.TranslationService

class MainRepository(private val mPresenter: MainPresenter) : MainContract.Repository {
    private val translationService = TranslationService.create()

    private fun parseCambridgeDictionary(response: Response<ResponseBody>): Pair<String,List<CambridgeDefinition>> {
        val html = Jsoup.parse(response.body()?.string())
        val selected = html.select("div.def-block.ddef_block")
        val res = mutableListOf<CambridgeDefinition>()
        for (meaning in selected) {
            val explanation = meaning.select("div.def.ddef_d.db").first()?.text()
            val example = meaning.select("div.examp.dexamp").first()?.text() ?: ""
            explanation?.let { res.add(CambridgeDefinition(it, example)) }
        }
        html.select("span.sp.dsp").forEach {
            it.replaceWith(TextNode("|${it.text()}|"))
        }
        val transcription = html.select("span.pron.dpron").first().text()
        return transcription to res
    }

    private fun parseWordHuntDictionary(response: Response<ResponseBody>): Pair<List<String>, Set<Example>> {
        val html = Jsoup.parse(response.body()?.string())
        val resExamples = mutableSetOf<Example>()
        val resTranslations = mutableListOf<String>()
        // Sentences
        val selected = html.select("p.ex_t.human, p.ex_o:not([style])").map { item -> item.text() }
        for (i in selected.indices step 2) {
            resExamples.add(Example(selected[i], selected[i + 1]))
        }
        // Collocations
        html.select("div.block.phrases > br").append("|*|")
        html.select("div.block.phrases").text().split("|*|").filter {
            it.isNotEmpty()
        }.map { str ->
            val splitted = str.split("—")
            Example(splitted[0].trim(), splitted[1].trim())
        }.let { resExamples.addAll(it) }
        // Translations
        html.select("div.tr > br, div.hidden > br").append("|*|")
        html.select("i").forEach {
            it.replaceWith(TextNode("[${it.text()}]"))
        }
        html.select("div.tr > div.ex.hidden, div.hidden > div.ex.hidden, span.more, div.ex").remove()
        html.select("div.tr").text().split("|*|").filter {
            it.isNotEmpty()
        }.map { s ->
            s.trim().removePrefix("- ")
        }.let { resTranslations.addAll(it) }
        return resTranslations to resExamples
    }

    override fun translateWord(word: String) {
        val handler = CoroutineExceptionHandler { coroutineContext, throwable ->
            Log.e("PROVOCAB_COROUTINES", throwable.message ?: "Something strange happened")
        }
        CoroutineScope(Dispatchers.Main + SupervisorJob()).launch(handler) {
            val cambridgeResponseDeferred = async(Dispatchers.IO) {
                translationService.getCambridgeWordDefinition(word.replace(" ", "-"))
            }
            val wordHuntResponseDeferred = async(Dispatchers.IO) {
                translationService.getWordHuntTranslation(word.replace(" ", "%20"))
            }
            try {
                val cambridgeResponse = cambridgeResponseDeferred.await()
                val wordHuntResponse = wordHuntResponseDeferred.await()
                if (cambridgeResponse.isSuccessful && wordHuntResponse.isSuccessful) {
                    mPresenter.onWordSearchSuccess(
                        Translation(
                            word,
                            parseCambridgeDictionary(cambridgeResponse),
                            parseWordHuntDictionary(wordHuntResponse),
                        )
                    )
                } else {
                    mPresenter.onWordSearchFailure("Call failed. Cambridge answer: ${cambridgeResponse.code()}, Yandex answer: ${wordHuntResponse.code()}")
                }
            } catch (ex: Exception) {
                mPresenter.onWordSearchFailure("An error occurred: $ex")
                return@launch
            }

        }
    }


}