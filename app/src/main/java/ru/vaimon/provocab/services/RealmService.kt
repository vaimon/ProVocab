package ru.vaimon.provocab.services

import io.realm.Realm
import io.realm.RealmResults
import ru.vaimon.provocab.models.Translation

object RealmService {

    fun fetchDictionary() : RealmResults<Translation>{
        return Realm.getDefaultInstance().where(Translation::class.java).findAll()
    }

    fun putTranslation(translation: Translation){
        Realm.getDefaultInstance().executeTransactionAsync {
            it.copyToRealm(translation)
        }
    }
}