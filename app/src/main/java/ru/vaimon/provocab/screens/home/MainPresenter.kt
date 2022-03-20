package ru.vaimon.provocab.screens.home

import ru.vaimon.provocab.base.BasePresenter
import ru.vaimon.provocab.models.CambridgeDefinition
import ru.vaimon.provocab.models.Translation

class MainPresenter : BasePresenter<MainContract.View>(), MainContract.Presenter {

    private val mRepository: MainContract.Repository by lazy { MainRepository(this) }

    override fun startWordSearch (word: String){
        mRepository.translateWord(word)
        mView?.showLoadingState()
    }

    override fun onWordSearchSuccess(translation: Translation) {
        mView?.showTranslation(translation)
    }

    override fun onWordSearchFailure(reason: String) {
        mView?.showError(reason)
    }
}