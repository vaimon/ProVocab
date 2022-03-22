package ru.vaimon.provocab.views.word_review

import io.realm.RealmResults
import ru.vaimon.provocab.models.Translation
import ru.vaimon.provocab.services.RealmService

class WordReviewRepository(private val mPresenter: WordReviewPresenter) : WordReviewContract.Repository {
    val dataSnapshot: RealmResults<Translation> = RealmService.fetchDictionary()

    override fun saveWord(word: Translation){
        RealmService.putTranslation(word)
    }

    override fun checkWordPresence(word: String): Boolean{
        return dataSnapshot.any { it.word == word }
    }

    override fun deleteWord(translation: Translation){
        RealmService.deleteTranslation(translation)
    }
}