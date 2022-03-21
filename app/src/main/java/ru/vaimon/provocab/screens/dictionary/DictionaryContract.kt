package ru.vaimon.provocab.screens.dictionary

import io.realm.RealmResults
import ru.vaimon.provocab.base.BaseContract
import ru.vaimon.provocab.models.Translation

interface DictionaryContract {
    interface View : BaseContract.View {

        fun setupValues(newValues: RealmResults<Translation>)
    }

    interface Presenter : BaseContract.Presenter<View> {

        fun loadDictionary()
        fun onDictionaryLoaded(newValues: RealmResults<Translation>)
    }

    interface Repository {

        fun loadDictionary()
    }
}