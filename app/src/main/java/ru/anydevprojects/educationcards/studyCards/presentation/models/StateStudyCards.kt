package ru.anydevprojects.educationcards.studyCards.presentation.models

data class StateStudyCards(
    val isLoading: Boolean = false,
    val showCardBack: Boolean = false,
    val front: String = "",
    val back: String = "",
    val currentNumberCard: Int = 0,
    val allCuntCards: Int = 0
)
