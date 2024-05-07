package ru.anydevprojects.educationcards.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.anydevprojects.educationcards.database.models.DeckAllStatistics
import ru.anydevprojects.educationcards.statistics.decksStatistics.domain.models.DeckStatistics

@Dao
interface DeckStatisticsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(deckStatistics: DeckStatistics)

    @Query("SELECT * FROM deckstatisticsentity")
    suspend fun getAllDeckStatistics(): List<DeckAllStatistics>

    @Query("SELECT * FROM deckstatisticsentity WHERE deck_id=:deckId")
    suspend fun getDeckStatisticsById(deckId: String): DeckStatistics

    @Query("DELETE FROM deckstatisticsentity WHERE deck_id IN (:deckId)")
    suspend fun deleteByDeckId(deckId: String)
}
