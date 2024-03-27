package ru.anydevprojects.educationcards.myDecks.presentation.models

import ru.anydevprojects.educationcards.domain.models.Deck

data class StateMyDecks(
    val isLoading: Boolean = false,
    val myDecks: List<Deck> = emptyList()
)
