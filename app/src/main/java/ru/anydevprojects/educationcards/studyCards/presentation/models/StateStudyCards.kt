package ru.anydevprojects.educationcards.studyCards.presentation.models

import androidx.compose.ui.text.AnnotatedString

data class StateStudyCards(
    val isLoading: Boolean = false,
    val showCardBack: Boolean = false,
    val front: AnnotatedString = AnnotatedString.Builder().toAnnotatedString(),
    val back: AnnotatedString = AnnotatedString.Builder().toAnnotatedString(),
    val currentNumberCard: Int = 0,
    val allCuntCards: Int = 0
)
