package ru.vaimon.provocab.views.word_review

import ru.vaimon.provocab.base.BaseContract
import ru.vaimon.provocab.models.Translation

interface WordReviewContract {
    interface View : BaseContract.View {

    }

    interface Presenter : BaseContract.Presenter<View> {
        fun saveWord(translation: Translation)
        fun checkWordPresence(word: String): Boolean
        fun deleteWord(translation: Translation)
    }

    interface Repository {

        fun saveWord(word: Translation)
        fun checkWordPresence(word: String): Boolean
        fun deleteWord(translation: Translation)
    }
}