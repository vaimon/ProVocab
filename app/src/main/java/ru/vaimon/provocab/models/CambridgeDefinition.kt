package ru.vaimon.provocab.models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import org.bson.types.ObjectId

open class CambridgeDefinition(
    var meaning: String,
    var example: String,
) : RealmObject() {
    override fun toString(): String {
        return "'$meaning' e.g.: $example"
    }

    constructor(): this("", "")
}
