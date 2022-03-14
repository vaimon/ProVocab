package ru.vaimon.provocab.screens.home

import ru.vaimon.provocab.base.BasePresenter
import ru.vaimon.provocab.models.CambridgeDefinition

class MainPresenter : BasePresenter<MainContract.View>(), MainContract.Presenter {

    private val mRepository: MainContract.Repository by lazy { MainRepository(this) }

    override fun startWordSearch (word: String){
        mRepository.translateWord(word)

    }

    override fun onWordSearchSuccess(definition: List<CambridgeDefinition>) {
        mView?.showTranslation(definition)
    }

    override fun onWordSearchFailure(reason: String) {
        TODO("Not yet implemented")
    }
}