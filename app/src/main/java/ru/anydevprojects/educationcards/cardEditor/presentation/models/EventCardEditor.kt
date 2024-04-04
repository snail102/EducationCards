package ru.anydevprojects.educationcards.cardEditor.presentation.models

sealed interface EventCardEditor {
    data object CloseScreen : EventCardEditor
}
