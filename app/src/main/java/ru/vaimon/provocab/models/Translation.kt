package ru.vaimon.provocab.models

data class Translation(
    val word: String,
    val transcription: String,
    val cambridgeDefinitions: List<CambridgeDefinition>,
    val translations: List<String>,
    val examples: Set<Example>
) {
    constructor(
        word: String,
        cambridge: Pair<String,List<CambridgeDefinition>>,
        wordHunt: Pair<List<String>, Set<Example>>
    ) : this(word, cambridge.first, cambridge.second, wordHunt.first, wordHunt.second)
}
