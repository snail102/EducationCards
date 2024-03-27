package ru.anydevprojects.educationcards.deckViewer.presentation.models

import ru.anydevprojects.educationcards.domain.models.Card

data class StateDeckViewer(
    val isLoading: Boolean = false,
    val name: String = "",
    val cards: List<Card> = emptyList()
)
