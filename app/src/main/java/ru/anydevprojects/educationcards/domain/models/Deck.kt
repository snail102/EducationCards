package ru.anydevprojects.educationcards.domain.models

data class Deck(
    val id: String,
    val name: String,
    val cards: List<Card>
)
