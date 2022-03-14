package ru.vaimon.provocab.models

data class Translation(
    val word: String,
    val cambridgeDefinitions: List<CambridgeDefinition>,
    val translations: List<String>,
    val examples: List<Example>
)
