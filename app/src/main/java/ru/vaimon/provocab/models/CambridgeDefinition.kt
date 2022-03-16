package ru.vaimon.provocab.models

data class CambridgeDefinition(val meaning: String, val example: String) {
    override fun toString(): String {
        return "'$meaning' e.g.: $example"
    }
}
