package ru.vaimon.provocab.models

data class Example(
    val englishExample: String,
    val translation: String,
) {
    val isCollocation: Boolean
        get() {
            return englishExample[0].isLowerCase()
        }

    override fun toString(): String {
        return "$englishExample ($translation)"
    }
}