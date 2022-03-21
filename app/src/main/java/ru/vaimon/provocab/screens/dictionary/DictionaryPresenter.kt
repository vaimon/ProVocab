package ru.vaimon.provocab.screens.dictionary

import io.realm.RealmResults
import ru.vaimon.provocab.base.BasePresenter
import ru.vaimon.provocab.models.Translation
import ru.vaimon.provocab.screens.home.MainContract
import ru.vaimon.provocab.screens.home.MainRepository

class DictionaryPresenter : DictionaryContract.Presenter,  BasePresenter<DictionaryContract.View>() {
    private val mRepository: DictionaryContract.Repository by lazy { DictionaryRepository(this) }

    override fun loadDictionary(){

    }

    override fun onDictionaryLoaded(newValues: RealmResults<Translation>){
        mView?.setupValues(newValues)
    }
}