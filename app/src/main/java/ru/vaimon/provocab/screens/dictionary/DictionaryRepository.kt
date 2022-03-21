package ru.vaimon.provocab.screens.dictionary

import ru.vaimon.provocab.screens.home.MainPresenter
import ru.vaimon.provocab.services.RealmService

class DictionaryRepository(private val mPresenter: DictionaryPresenter) : DictionaryContract.Repository {

    override fun loadDictionary(){
        mPresenter.onDictionaryLoaded(RealmService.fetchDictionary())
    }
}