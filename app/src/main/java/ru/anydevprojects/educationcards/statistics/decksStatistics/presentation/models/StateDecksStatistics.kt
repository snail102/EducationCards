package ru.anydevprojects.educationcards.statistics.decksStatistics.presentation.models

import androidx.compose.runtime.Immutable
import ru.anydevprojects.educationcards.statistics.decksStatistics.domain.models.DeckStatistics

@Immutable
data class StateDecksStatistics(
    val isLoading: Boolean = false,
    val decksStatistics: List<DeckStatistics>
)
