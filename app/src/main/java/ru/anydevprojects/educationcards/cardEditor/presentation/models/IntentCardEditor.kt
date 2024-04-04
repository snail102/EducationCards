package ru.anydevprojects.educationcards.cardEditor.presentation.models

sealed interface IntentCardEditor {
    data object OnSaveCardClick : IntentCardEditor
}
