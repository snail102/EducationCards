package ru.anydevprojects.educationcards.statistics.decksStatistics.data

import ru.anydevprojects.educationcards.database.dao.DeckStatisticsDao
import ru.anydevprojects.educationcards.statistics.decksStatistics.domain.DecksStatisticsRepository
import ru.anydevprojects.educationcards.statistics.decksStatistics.domain.models.DeckStatistics

class DecksStatisticsRepositoryImpl(
    private val deckStatisticsDao: DeckStatisticsDao
) : DecksStatisticsRepository {

    override suspend fun getAllDecksStatistics(): List<DeckStatistics> {
        deckStatisticsDao.getAllDeckStatistics().map {
            it.
        }
    }
}
