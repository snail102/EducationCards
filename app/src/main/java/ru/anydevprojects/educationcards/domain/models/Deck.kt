package ru.anydevprojects.educationcards.domain.models

data class Deck(
    val id: Long,
    val name: String,
    val cards: List<Card>
)
