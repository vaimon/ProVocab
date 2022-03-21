package ru.vaimon.provocab.models

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import org.bson.types.ObjectId

open class Translation(
    @PrimaryKey
    var word: String,
    var pronunciation: String,
    var cambridgeDefinitions: RealmList<CambridgeDefinition>,
    var translations: RealmList<String>,
    var examples: RealmList<Example>,
) : RealmObject() {

    constructor(
        word: String,
        cambridge: Pair<String, List<CambridgeDefinition>>,
        wordHunt: Pair<List<String>, Set<Example>>
    ) : this(word, cambridge.first, RealmList(), RealmList(), RealmList()) {
        cambridgeDefinitions.addAll(cambridge.second)
        translations.addAll(wordHunt.first)
        examples.addAll(wordHunt.second)
    }

    constructor() : this("", "", RealmList(), RealmList(), RealmList())
}
