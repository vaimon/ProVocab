package ru.vaimon.provocab.views.word_review

import ru.vaimon.provocab.base.BasePresenter
import ru.vaimon.provocab.models.Translation

class WordReviewPresenter : WordReviewContract.Presenter,  BasePresenter<WordReviewContract.View>() {
    private val mRepository: WordReviewContract.Repository by lazy { WordReviewRepository(this) }

    override fun saveWord(translation: Translation){
        mRepository.saveWord(translation)
    }
}