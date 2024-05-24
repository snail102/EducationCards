package ru.anydevprojects.educationcards.statistics.decksStatistics.domain.models

data class DeckStatistics(
    val id: Long,
    val deckName: String,
    val numberOfStudies: Int,
    val lastStudyTimestamp: Long
)
