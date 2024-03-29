package ru.anydevprojects.educationcards.cardEditor.presentation.models

data class StateCardEditor(
    val isLoading: Boolean = false,
    val front: String = "",
    val back: String = ""
)
