package ru.anydevprojects.educationcards.profile.data

import ru.anydevprojects.educationcards.database.dao.CardStatisticsDao
import ru.anydevprojects.educationcards.database.dao.DeckStatisticsDao
import ru.anydevprojects.educationcards.profile.domain.ProfileRepository

class ProfileRepositoryImpl(
    private val cardStatisticsDao: CardStatisticsDao,
    private val deckStatisticsDao: DeckStatisticsDao
) : ProfileRepository {

    override suspend fun isCardStatisticsAvailable(): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun isDeckStatisticsAvailable(): Boolean {
        TODO("Not yet implemented")
    }
}
