package ru.anydevprojects.educationcards.studyCards.presentation.models

sealed interface IntentStudyCards {
    data object OnEditCurrentCardClick : IntentStudyCards
}
