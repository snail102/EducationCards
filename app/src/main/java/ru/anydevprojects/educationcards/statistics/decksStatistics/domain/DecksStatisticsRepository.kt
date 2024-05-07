package ru.anydevprojects.educationcards.statistics.decksStatistics.domain

import ru.anydevprojects.educationcards.statistics.decksStatistics.domain.models.DeckStatistics

interface DecksStatisticsRepository {

    suspend fun getAllDecksStatistics(): List<DeckStatistics>
}
