package ru.anydevprojects.educationcards.statistics.cardsStatistics.data

import ru.anydevprojects.educationcards.database.dao.CardStatisticsDao
import ru.anydevprojects.educationcards.statistics.cardsStatistics.domain.CardsStatisticsRepository
import ru.anydevprojects.educationcards.statistics.cardsStatistics.domain.models.CardStatistics

class CardsStatisticsRepositoryImpl(
    private val cardsStatisticsDao: CardStatisticsDao
) : CardsStatisticsRepository {
    override suspend fun getAllCardsStatistics(): List<CardStatistics> {
        return TODO()
    }
}
