package ru.anydevprojects.educationcards.statistics.cardsStatistics.domain

import ru.anydevprojects.educationcards.statistics.cardsStatistics.domain.models.CardStatistics

interface CardsStatisticsRepository {

    suspend fun getAllCardsStatistics(): List<CardStatistics>
}
