package ru.vaimon.provocab.models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import org.bson.types.ObjectId

open class Example(
    var englishExample: String,
    var translation: String,
) : RealmObject() {
    override fun toString(): String {
        return "$englishExample ($translation)"
    }

    constructor() : this("", "")
}