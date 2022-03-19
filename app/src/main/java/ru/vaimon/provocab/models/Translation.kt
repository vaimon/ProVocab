package ru.vaimon.provocab.models

data class Translation(
    val word: String,
    val cambridgeDefinitions: List<CambridgeDefinition>,
    val translations: List<String>,
    val examples: Set<Example>
) {
    constructor(
        word: String,
        cambridgeDefinitions: List<CambridgeDefinition>,
        wordHunt: Pair<List<String>, Set<Example>>
    ) : this(word, cambridgeDefinitions, wordHunt.first, wordHunt.second)
}
