package ru.anydevprojects.educationcards.statistics.decksStatistics.domain.models

import androidx.compose.runtime.Immutable

@Immutable
data class DeckStatistics(
    val id: String,
    val deckName: String,
    val numberOfStudies: Int,
    val lastStudyTimestamp: Long
)
