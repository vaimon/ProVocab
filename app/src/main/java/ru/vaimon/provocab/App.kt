package ru.vaimon.provocab

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        setupRealm()
    }

    private fun setupRealm(){
        Realm.init(this)
        val realmConfiguration = RealmConfiguration.Builder()
            .name("ProVocab Dictionary")
            .deleteRealmIfMigrationNeeded()
            .build()
        Realm.setDefaultConfiguration(realmConfiguration)
    }

}