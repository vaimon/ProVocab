package ru.vaimon.provocab.screens.home

import ru.vaimon.provocab.base.BaseContract
import ru.vaimon.provocab.models.CambridgeDefinition

interface MainContract {
    interface View : BaseContract.View {
        fun showLoadingState()
        fun showTranslation(cambridgeDefinitions: List<CambridgeDefinition>)
        fun showError(reason: String)
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun startWordSearch(word: String)
        fun onWordSearchSuccess(definition: List<CambridgeDefinition>)
        fun onWordSearchFailure(reason: String)
    }

    interface Repository {
        fun translateWord(word: String)
    }
}