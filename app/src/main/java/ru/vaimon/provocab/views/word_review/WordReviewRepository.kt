package ru.vaimon.provocab.views.word_review

import ru.vaimon.provocab.models.Translation
import ru.vaimon.provocab.services.RealmService

class WordReviewRepository(private val mPresenter: WordReviewPresenter) : WordReviewContract.Repository {

    override fun saveWord(word: Translation){
        RealmService.putTranslation(word)
    }
}