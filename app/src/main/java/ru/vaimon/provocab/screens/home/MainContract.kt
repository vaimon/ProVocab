package ru.vaimon.provocab.screens.home

import ru.vaimon.provocab.base.BaseContract
import ru.vaimon.provocab.models.CambridgeDefinition
import ru.vaimon.provocab.models.Translation

interface MainContract {
    interface View : BaseContract.View {
        fun showLoadingState()
        fun showTranslation(translation: Translation)
        fun showError(reason: String)
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun startWordSearch(word: String)
        fun onWordSearchSuccess(translation: Translation)
        fun onWordSearchFailure(reason: String)
        fun saveWord(word: Translation)
    }

    interface Repository {
        fun translateWord(word: String)
        fun saveWord(word: Translation)
    }
}