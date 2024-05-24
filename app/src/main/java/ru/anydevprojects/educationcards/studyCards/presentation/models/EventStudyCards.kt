package ru.anydevprojects.educationcards.studyCards.presentation.models

sealed interface EventStudyCards {
    data class OpenCardEditor(val cardId: Long) : EventStudyCards
    data object OpenFinishScreen : EventStudyCards
}
